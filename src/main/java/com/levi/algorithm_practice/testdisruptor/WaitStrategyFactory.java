package com.levi.algorithm_practice.testdisruptor;

import com.lmax.disruptor.*;

import java.util.concurrent.TimeUnit;

public class WaitStrategyFactory {

    private WaitStrategyFactory(){
    }
    private static class WaitStrategyFactoryHolder{
        private static final WaitStrategyFactory WAIT_STRATEGY_FACTORY = new WaitStrategyFactory();
    }
    private static final WaitStrategyFactory getInstance(){
        return WaitStrategyFactoryHolder.WAIT_STRATEGY_FACTORY;
    }

    public Object newInstance(String waitStrategy){

        switch(waitStrategy){
            case "BlockingWaitStrategy":
                return new BlockingWaitStrategy();

            case "YieldingWaitStrategy":
                return new YieldingWaitStrategy();

            case "LiteBlockingWaitStrategy":
                return new LiteBlockingWaitStrategy();

            case "BusySpinWaitStrategy":
                return new BusySpinWaitStrategy();

            case "SleepingWaitStrategy":
                return new SleepingWaitStrategy();

            case "PhasedBackoffWaitStrategy":
                return new PhasedBackoffWaitStrategy(1,1, TimeUnit.MILLISECONDS,new BusySpinWaitStrategy());

            case "TimeoutBlockingWaitStrategy":
                return new TimeoutBlockingWaitStrategy(1, TimeUnit.MILLISECONDS);

            case "LiteTimeoutBlockingWaitStrategy":
                return new LiteTimeoutBlockingWaitStrategy(1, TimeUnit.MILLISECONDS);

        }
        return null;
    }

}






































