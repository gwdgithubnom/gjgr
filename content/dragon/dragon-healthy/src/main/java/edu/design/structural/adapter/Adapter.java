package edu.design.structural.adapter;

/**
 * Created by gwd on 9/4/2016.
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.someRequest();
    }
}
