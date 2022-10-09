package com.levi.allresource.testdisruptor.perf;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ���������
 <typeparam name="TObject"></typeparam>
 */
public class ObjectPool<TObject extends PoolObjectBase> implements IObjectPool {
	private Class<?> clazzOfT;

	/**
	 * �����ʵ�ʴ洢���������
	 */
	private volatile TObject[] Objects = null;

	/**
	 *  ��ǰ��Ŷ�����α�
	 */
	private AtomicInteger PutPosition = new AtomicInteger();

	/**
	 * ��ǰ��ȡ������α�
	 */
	private AtomicInteger GetPosition = new AtomicInteger();

	/**
	 ��ǰ����
	 */
	private int Capacity;
	public final int getCapacity() {
		return Capacity;
	}
	private void setCapacity(int value) {
		Capacity = value;
	}

	/**
	 �������
	 */
	private int MaxCapacity;
	public final int getMaxCapacity() {
		return MaxCapacity;
	}
	private void setMaxCapacity(int value) {
		MaxCapacity = value;
	}

	/**
	 * ��ǰʵ������
	 */
	private int realCapacity = 0;
	public final int getRealCapacity() {
		return realCapacity;
	}

	/**
	 * ����ض��󲻹���ʱ���Ƿ�֧����ʱ�����µĶ���
	 */
	private boolean suppoertNewObject = false;
	public void setSuppoertNewObject(boolean suppoertNewObject) {
		this.suppoertNewObject = suppoertNewObject;
	}

	/**
	 * ���캯��
	 * @param classOfT ����ض��������
	 * @param initCapacity ��ʼ����
	 * @param maxCapacity �������
	 * @param suppoertNewObject �ڶ���ض����þ�֮���Ƿ�֧�ִ����µĶ���
	 */
	public ObjectPool(Class<?> classOfT, int initCapacity, int maxCapacity, boolean suppoertNewObject) {
		this.setCapacity(initCapacity);
		this.setMaxCapacity(maxCapacity);
		this.setSuppoertNewObject(suppoertNewObject);

		this.clazzOfT = classOfT;

		//�����Ĭ�ϻᰴ�����������ǰ�������еĶ��󣬱������й����д���Ӱ������
		this.realCapacity = this.getMaxCapacity();

		//�����Windows������������Ϊ��DEBUG������������Ҫ���������Ķ��󣬰�����Сֵ���������ܹ�����������󼴿�
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
			this.realCapacity = this.getCapacity();
		}

		//����ʵ�ʴ洢���ж���ض���Ļ�������
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

		//��ʼ���û��ζ��д�����ݵ�ƫ��λ��
		this.GetPosition.set(0);
		this.PutPosition.set(0);
	}

	/**
	 �Ӷ����������һ������
	 @return
	 */
	public final TObject GetOne() {
		TObject result = null;
		do {
			int getpos = this.GetPosition.get();
			if (this.Objects[getpos] == null && getpos == this.PutPosition.get()) {
				//�����ǰû�п��õĶ��������������ʱ�½�һ�����������Ϳ��Բ��ü����ȴ���
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

			//������һ�����ܵĻ�ȡ�����λ�ã����δ���
			int newpos = getpos < this.realCapacity - 1 ? getpos + 1 : 0;

			//ʹ��ԭ�Ӳ���CAS�����޸Ļ�ȡ�����λ�ã�����ɹ���˵����ǰλ��������ռ�ɹ�
			if (this.GetPosition.compareAndSet(getpos, newpos)) {
				//��Ϊ�黹������߳̿��ܻ�δ������������飬������Ҫ���ĵȴ�һ���
				do {
					result = this.Objects[getpos];
				} while (result == null);

				//�������ݺ����鵱ǰλ����գ�������������������߳�Ҳ�����������
				this.Objects[getpos] = null;
				result.setInPool(false);
				break;
			}
		} while (true);

		return result;
	}

	/**
	 ���ԴӶ����������һ������
	 @return
	 */
	public final TObject tryGetOne() {
		TObject result = null;

		//���ж�һ�µ�ǰ���Ի�ȡ���ݵ�λ���Ƿ��ж������û����ֱ�ӷ���
		int getpos = this.GetPosition.get();
		if (this.Objects[getpos] == null)
			return null;

		//�����ǰ�ж�����ԡ���������ʹ��CAS���Ƴ��Ի�ȡ��ǰ����
		int newgetpos = getpos < this.realCapacity - 1 ? getpos + 1 : 0;

		//������Գɹ�����˵�������������������
		if (this.GetPosition.compareAndSet(getpos, newgetpos)) {
			//��ȡ��ǰ����
			result = this.Objects[getpos];

			//��յ�ǰλ�õĶ��󣬷�ֹ�������߳����ȡ
			this.Objects[getpos] = null;

			result.setInPool(false);
		}
		return result;
	}

	/**
	 �������зŻ�һ������
	 */
	public final void PutOne(TObject obj) {
		//���������Ч�������Ѿ��ڶ�����ˣ���ֱ�ӷ���
		if (obj == null || obj.getInPool())
			return;

		//��ǵ�ǰ�����Ѿ������أ������ظ�������
		obj.setInPool(true);

		do {
			//�����ǰ����ش�������״̬��û�п����λ�ÿ��Դ�ŵ�ǰ������ֱ�ӷ��أ��൱�ڽ��������صĶ����ӵ��ˣ�
			//�����֧����ʱ�������������£�ʵ�ʿ��ܴӶ�����л�ȡ���ڶ������������Ķ������������黹������ܳ����������
			int putpos = this.PutPosition.get();
			if (this.Objects[putpos] != null && putpos == this.GetPosition.get())
				return;

			//����д��λ�ã�������ռ������λ��
			int newputpos = putpos < this.realCapacity - 1 ? putpos + 1 : 0;
			if (this.PutPosition.compareAndSet(putpos, newputpos)) {

				//�����ռ�ɹ�������Ҫ�ж���ǰλ���Ƿ�Ϊ�գ���Ϊ��ȡ�߳̿��ܻ�δ��ʱ������ȡ��
				while (this.Objects[putpos] != null) { }

				//����ǰ���λ�ÿ���֮�󣬴�ŵ�ǰ����
				this.Objects[putpos] = obj;

				//��Ų�����ɣ��˳�ѭ��������
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