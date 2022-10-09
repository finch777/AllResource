package com.levi.algorithm_practice.bindcore;

import java.util.ArrayList;
import java.util.List;

public class BindCoreTest {

    private final List<Integer> CpuIds = new ArrayList<>();

    private void init(){
        CpuIds.add(2);
    }

    public static void main(String[] args) {
        new Thread(new ProcessThread()).start();
    }



}
