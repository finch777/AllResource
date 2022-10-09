package com.levi.algorithm_practice.designpattern.observer;

/**
 * @author Levi Wang
 * @create 2022-05-06 20:51
 */

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
