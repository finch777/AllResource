package com.levi.allresource.testdisruptor;

import com.levi.allresource.testdisruptor.perf.TestQueue;

import java.util.ArrayList;
import java.util.List;

public class ProducerThread implements Runnable {
    private List<LongEvent> list = new ArrayList<>();
    @Override
    public void run() {
        for (int i = 0; i < 10; i ++){
            LongEvent longEvent = new LongEvent();
            longEvent.setUuid(""+i);
//            list.add(longEvent);
            TestQueue.getInstance().add(longEvent);
        }
    }
    /*
    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;
        return count;
}
    * */
}




























