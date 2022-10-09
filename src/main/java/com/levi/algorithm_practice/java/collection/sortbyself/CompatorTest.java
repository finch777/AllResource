package com.levi.algorithm_practice.java.collection.sortbyself;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Levi Wang
 * @create 2022-10-09 14:58
 */

public class CompatorTest {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(Arrays.asList(1, 2, 3, 4, 5));

        Collections.sort(list, (o1, o2) -> o2 - o1);

        System.out.println(list);
    }

}
