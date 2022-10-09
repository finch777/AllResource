package com.levi.allresource.designpattern.observer;

/**
 * @author Levi Wang
 * @create 2022-05-06 20:53
 */

public class HexaObserver extends Observer{
    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}
