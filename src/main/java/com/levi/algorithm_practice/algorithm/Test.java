package com.levi.algorithm_practice.algorithm;

public class Test {
    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        for (int i = 0; i < 20; i++) {
            skipList.insert(new SLNode(i, i*i));
        }
        skipList.printList();
    }
}
