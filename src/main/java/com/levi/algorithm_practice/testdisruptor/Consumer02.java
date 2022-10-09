package com.levi.algorithm_practice.testdisruptor;


import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer02 implements WorkHandler<LongEvent> {
    private AtomicInteger count2 = new AtomicInteger();
    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" ฯ๛ทัมห: " + longEvent.getValue1() +"---"+(count2.incrementAndGet()));
    }

}
