package com.levi.algorithm_practice.designpattern.observer;

/**
 * @author Levi Wang
 * @create 2022-05-06 20:53
 */

public class OctalObserver extends Observer{
    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
