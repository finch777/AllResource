package com.levi.algorithm_practice.testdisruptor.perf;

import com.finch.testdisruptor.Consumer01;
import com.finch.testdisruptor.LongEvent;
import com.finch.testdisruptor.LongEventFactory;
import com.finch.testdisruptor.LongExceptionHandler;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestQueue {

    private TestQueue(){ init();}
    private static class TestQueueHolder{
        private static final TestQueue INSTANCE = new TestQueue();
    }
    public static final TestQueue getInstance(){
        return TestQueueHolder.INSTANCE;
    }



    private static RingBuffer<LongEvent> ringBuffer;
    private WaitStrategy waitStrategy = new YieldingWaitStrategy();

    ThreadFactory threadFactory = Executors.defaultThreadFactory();

//    EventFactory<LongEvent> eventFactory = new LongEventFactory();

//    ExceptionHandler<LongEvent> exceptionHandler = new LongExceptionHandler();

    private int ringBufferSize = 4; // RingBuffer 大小，必须是 2 的 N 次方；

    Disruptor<LongEvent> disruptor;


    private void init(){

        disruptor = new Disruptor<>(() -> new LongEvent(),
                ringBufferSize, threadFactory, ProducerType.MULTI, waitStrategy);
        disruptor.handleEventsWithWorkerPool(createConsumers());
        disruptor.setDefaultExceptionHandler(new LogExceptionHandler());
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();

    }

    private WorkHandler[] createConsumers(){
        WorkHandler[] consumers = new Consumer01[2];
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer01();
        }
        return consumers;
    }

    public void add(LongEvent sourceEvent){
        //请求下一个事件序号
        long sequence = ringBuffer.next();

        try {
            LongEvent destEvent = ringBuffer.get(sequence);
            copyEvent(sourceEvent, destEvent);
        } finally {
            ringBuffer.publish(sequence);
        }

    }

    private void copyEvent(LongEvent sourceEvent, LongEvent destEvent){
        destEvent.setUuid(sourceEvent.getUuid());
        destEvent.setValue1(sourceEvent.getValue1());
        destEvent.setValue2(sourceEvent.getValue2());
    }


}
