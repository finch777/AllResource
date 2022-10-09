package com.levi.allresource.testdisruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer03 implements WorkHandler<LongEvent> {
    private AtomicInteger count3 = new AtomicInteger();
    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" ������: " + longEvent.getValue1()+"---"+(count3.incrementAndGet()));
    }
}
