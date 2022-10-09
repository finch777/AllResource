package com.levi.allresource.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Levi Wang
 * @create 2022-05-06 20:50
 */

public class Subject {
    private List<Observer> observers
            = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
