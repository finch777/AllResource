package com.levi.algorithm_practice.testdisruptor;

import com.lmax.disruptor.RingBuffer;

public class Producer {

    private final RingBuffer<LongEvent> ringBuffer;

    public Producer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(int i){
        long sequence = ringBuffer.next();//请求下一个事件序号
        System.out.println("当前数据所在队列中的位置："+sequence);

        try {
            LongEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象
//                long data = getEventData();//获取要通过事件传递的业务数据
            long data = i;
            event.setValue1(System.nanoTime());
            event.setValue2(System.nanoTime());
        } finally{
            ringBuffer.publish(sequence);//发布事件
            System.out.println(Thread.currentThread().getName()+"：生产并放入队列；第："+i+"条数据");
        }
    }


}
