package com.levi.algorithm_practice.algorithm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class CountPrimes {

    private static int capacity = 4000;
    private static int initialCapacity = (int) ((float) capacity / 0.75F + 1.0F);
    private static HashMap<Integer,Integer> map = new HashMap(initialCapacity);

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        Class<? extends HashMap> aClass = map.getClass();

//        Field threshold = aClass.getDeclaredField("threshold");
//
//        threshold.setAccessible(true);

        Method capacity = aClass.getDeclaredMethod("capacity");

        capacity.setAccessible(true);

        System.out.println(capacity.invoke(map));

        System.out.println(countPrimes(100));
    }
    private static int countPrimes(int n) {
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

}

