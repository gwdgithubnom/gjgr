package edu.design.creational.singleton;

/**
 * Created by gwd on 9/4/2016.
 */
public class Singleton {

    private static Singleton singleton;

    /**
     * when this is multi-thread, we may consider use "synchronized".
     * @return
     */
    public static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }
}
