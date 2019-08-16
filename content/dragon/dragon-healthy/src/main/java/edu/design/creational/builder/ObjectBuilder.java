package edu.design.creational.builder;

/**
 * Created by gwd on 9/4/2016.
 * to accomplish the builder design mode java source, like this:
 * java.lang.StringBuilder #append()
 * java.nio.ByteBuffer #put()
 * java.nio.CharBuffer #put()
 */
public abstract  class ObjectBuilder {
    public static final String staticAttribute="Attribute Information";
    protected Object object;
    public Object getObject(){
        return object;
    }

    public void initialize(){
        object=new Object();
    }

    public abstract boolean addMenthod();
}
