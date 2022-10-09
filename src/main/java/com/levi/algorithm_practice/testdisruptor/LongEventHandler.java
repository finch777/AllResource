package com.levi.algorithm_practice.testdisruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getName()+"ฯ๛ทัมห: " + longEvent.getValue1());
    }
}
