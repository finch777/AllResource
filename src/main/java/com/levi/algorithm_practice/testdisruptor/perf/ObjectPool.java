package com.levi.algorithm_practice.testdisruptor.perf;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无锁对象池
 <typeparam name="TObject"></typeparam>
 */
public class ObjectPool<TObject extends PoolObjectBase> implements IObjectPool {
	private Class<?> clazzOfT;

	/**
	 * 对象池实际存储对象的数组
	 */
	private volatile TObject[] Objects = null;

	/**
	 *  当前存放对象的游标
	 */
	private AtomicInteger PutPosition = new AtomicInteger();

	/**
	 * 当前获取对象的游标
	 */
	private AtomicInteger GetPosition = new AtomicInteger();

	/**
	 当前容量
	 */
	private int Capacity;
	public final int getCapacity() {
		return Capacity;
	}
	private void setCapacity(int value) {
		Capacity = value;
	}

	/**
	 最大容量
	 */
	private int MaxCapacity;
	public final int getMaxCapacity() {
		return MaxCapacity;
	}
	private void setMaxCapacity(int value) {
		MaxCapacity = value;
	}

	/**
	 * 当前实际容量
	 */
	private int realCapacity = 0;
	public final int getRealCapacity() {
		return realCapacity;
	}

	/**
	 * 对象池对象不够用时，是否支持临时创建新的对象
	 */
	private boolean suppoertNewObject = false;
	public void setSuppoertNewObject(boolean suppoertNewObject) {
		this.suppoertNewObject = suppoertNewObject;
	}

	/**
	 * 构造函数
	 * @param classOfT 对象池对象的类型
	 * @param initCapacity 初始容量
	 * @param maxCapacity 最大容量
	 * @param suppoertNewObject 在对象池对象用尽之后是否支持创新新的对象
	 */
	public ObjectPool(Class<?> classOfT, int initCapacity, int maxCapacity, boolean suppoertNewObject) {
		this.setCapacity(initCapacity);
		this.setMaxCapacity(maxCapacity);
		this.setSuppoertNewObject(suppoertNewObject);

		this.clazzOfT = classOfT;

		//对象池默认会按照最大数量提前创建所有的对象，避免运行过程中创建影响性能
		this.realCapacity = this.getMaxCapacity();

		//如果是Windows环境（可以认为是DEBUG环境），不需要创建大量的对象，按照最小值创建对象，能够满足调试需求即可
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
			this.realCapacity = this.getCapacity();
		}

		//创建实际存储所有对象池对象的环形数组
		this.Objects = (TObject[]) Array.newInstance(this.clazzOfT, this.realCapacity);
		for (int i = 0; i < this.realCapacity; ++i) {
			TObject obj = null;
			try {
				obj = (TObject)this.clazzOfT.newInstance();
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
			if(obj == null)
				break;
			obj.setPool(this);
			obj.setInPool(true);
			this.Objects[i] = obj;
		}

		//初始设置环形队列存放数据的偏移位置
		this.GetPosition.set(0);
		this.PutPosition.set(0);
	}

	/**
	 从对象池中拿走一个对象
	 @return
	 */
	public final TObject GetOne() {
		TObject result = null;
		do {
			int getpos = this.GetPosition.get();
			if (this.Objects[getpos] == null && getpos == this.PutPosition.get()) {
				//如果当前没有可用的对象，如果允许则临时新建一个对象，这样就可以不用继续等待了
				if (suppoertNewObject) {
					try {
						result = (TObject) this.clazzOfT.newInstance();
						result.setPool(this);
						result.setInPool(false);
						break;
					} catch (InstantiationException e) {
					} catch (IllegalAccessException e) {
					}
				}
				continue;
			}

			//计算下一个可能的获取对象的位置（环形处理）
			int newpos = getpos < this.realCapacity - 1 ? getpos + 1 : 0;

			//使用原子操作CAS尝试修改获取对象的位置，如果成功则说明当前位置数据抢占成功
			if (this.GetPosition.compareAndSet(getpos, newpos)) {
				//因为归还对象的线程可能还未将对象放入数组，所以需要耐心等待一会儿
				do {
					result = this.Objects[getpos];
				} while (result == null);

				//拿走数据后将数组当前位置清空，避免程序错误导致另外的线程也拿走这个对象
				this.Objects[getpos] = null;
				result.setInPool(false);
				break;
			}
		} while (true);

		return result;
	}

	/**
	 尝试从对象池中拿走一个对象
	 @return
	 */
	public final TObject tryGetOne() {
		TObject result = null;

		//简单判断一下当前可以获取数据的位置是否有对象，如果没有则直接返回
		int getpos = this.GetPosition.get();
		if (this.Objects[getpos] == null)
			return null;

		//如果当前有对象可以“抢”，则使用CAS机制尝试获取当前对象
		int newgetpos = getpos < this.realCapacity - 1 ? getpos + 1 : 0;

		//如果尝试成功，则说明“抢”到了这个对象
		if (this.GetPosition.compareAndSet(getpos, newgetpos)) {
			//获取当前对象
			result = this.Objects[getpos];

			//清空当前位置的对象，防止被其他线程误获取
			this.Objects[getpos] = null;

			result.setInPool(false);
		}
		return result;
	}

	/**
	 向对象池中放回一个对象
	 */
	public final void PutOne(TObject obj) {
		//如果对象无效，或者已经在对象池了，则直接返回
		if (obj == null || obj.getInPool())
			return;

		//标记当前对象已经入对象池，避免重复入对象池
		obj.setInPool(true);

		do {
			//如果当前对象池处于满载状态，没有空余的位置可以存放当前对象，则直接返回（相当于将待入对象池的对象扔掉了）
			//对象池支持临时创建对象的情况下，实际可能从对象池中获取大于对象池最大数量的对象，如果都用完归还，则可能出现这种情况
			int putpos = this.PutPosition.get();
			if (this.Objects[putpos] != null && putpos == this.GetPosition.get())
				return;

			//如果有存放位置，则尝试抢占这个存放位置
			int newputpos = putpos < this.realCapacity - 1 ? putpos + 1 : 0;
			if (this.PutPosition.compareAndSet(putpos, newputpos)) {

				//如果抢占成功，还需要判读当前位置是否为空，因为获取线程可能还未即时将对象取走
				while (this.Objects[putpos] != null) { }

				//待当前存放位置空闲之后，存放当前对象
				this.Objects[putpos] = obj;

				//存放操作完成，退出循环，返回
				break;
			}
		} while (true);
	}

	/**
	 @param obj
	 */
	public final void PutOneObject(Object obj) {
		this.PutOne((TObject)obj);
	}
}