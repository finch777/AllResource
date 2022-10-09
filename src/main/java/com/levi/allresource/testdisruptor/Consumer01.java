package com.levi.allresource.testdisruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class Consumer01 implements WorkHandler<LongEvent> {
    private AtomicInteger count1 = new AtomicInteger();
    @Override
    public void onEvent(LongEvent longEvent) {
        System.out.println(longEvent.getUuid());
    }
}
