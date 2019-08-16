package edu.design.structural.adapter;

/**
 * Created by gwd on 9/4/2016.
 */
public class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee){
        this.adaptee=adaptee;
    }

    @Override
    public void request() {
        adaptee.someRequest();
    }
}
