package com.levi.algorithm_practice.testdisruptor.perf;

import com.lmax.disruptor.ExceptionHandler;

public class LogExceptionHandler implements ExceptionHandler {
    private static CommPerfLogWriter commPerfLogWriter = CommPerfLogWriter.getInstance();
    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        commPerfLogWriter.log(event.toString());
    }
    @Override
    public void handleOnStartException(Throwable ex) {
        ex.printStackTrace();
    }
    @Override
    public void handleOnShutdownException(Throwable ex) {
        ex.printStackTrace();;
    }
}
