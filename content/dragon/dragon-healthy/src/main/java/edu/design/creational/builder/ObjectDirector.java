package edu.design.creational.builder;

/**
 * Created by gwd on 9/4/2016.
 */
public class ObjectDirector {
    private ObjectBuilder builder;
    public void setBuilder(ObjectBuilder builder){
        this.builder=builder;
    }
    /** this is director to do work, to direct something**/
    public void construct(){
        //to do work
        builder.initialize();
        builder.addMenthod();
    }
    public Object getObject(){
        return builder.getObject();
    }
}
