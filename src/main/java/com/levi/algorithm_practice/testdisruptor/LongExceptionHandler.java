package com.levi.algorithm_practice.testdisruptor;

import com.lmax.disruptor.ExceptionHandler;

public class LongExceptionHandler implements ExceptionHandler<LongEvent> {

    @Override
    public void handleEventException(Throwable throwable, long l, LongEvent longEvent) {
        System.out.println(throwable +"llllllllllllllnuibsdh");
    }

    @Override
    public void handleOnStartException(Throwable throwable) {

    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {

    }
}
