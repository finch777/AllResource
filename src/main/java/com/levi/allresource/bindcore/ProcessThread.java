package com.levi.allresource.bindcore;

import net.openhft.affinity.AffinityLock;

import java.util.ArrayList;
import java.util.List;

public class ProcessThread implements Runnable {

    public ProcessThread(){
        init();
    }

    private final List<Integer> CpuIds = new ArrayList<>();

    private void init(){
        CpuIds.add(2);
        CpuIds.add(1);
    }

    @Override
    public void run() {

        for (int cpuId : CpuIds){
            try (AffinityLock lock = AffinityLock.acquireLock(cpuId)) {

                while (true){

                }
            }
        }

    }
}
