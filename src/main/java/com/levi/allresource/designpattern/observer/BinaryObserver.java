package com.levi.allresource.designpattern.observer;

/**
 * @author Levi Wang
 * @create 2022-05-06 20:52
 */

public class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
