package edu.design.creational.factory;

/**
 * Created by gwd on 9/3/2016.
 */
public  final  class StaticFactory {

    public static <T> T newInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if(clazz==null||clazz.equals(""))
            throw new IllegalArgumentException();
        T fragment=clazz.newInstance();
        //Bundle args = new Bundle();

        //StaticFactory fragment = new StaticFactory();
        //fragment.setArguments(args);
        return fragment;
    }
}
