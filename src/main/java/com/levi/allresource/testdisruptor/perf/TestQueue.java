package com.levi.allresource.testdisruptor.perf;


import com.levi.allresource.testdisruptor.Consumer01;
import com.levi.allresource.testdisruptor.LongEvent;
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

    private int ringBufferSize = 4; // RingBuffer ��С�������� 2 �� N �η���

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
        //������һ���¼����
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
