package edu.design.creational.factory;

/**
 * Created by gwd on 9/2/2016.
 * This is create a instance for factory design pattern.
 * A simple class type to build a object
 * @param <T> The target type
 */
public class ClassGenericFactory<T extends  InterfaceAdancePlugin & InterfaceBasePlugin>{
    /**
     * @praram className type name
     * @return  result type
     *
     */
    @SuppressWarnings("unchecked")
    public T newInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if((className==null)||(className.isEmpty()))
            throw  new IllegalArgumentException("className");

        return (T)(Class.forName(className).newInstance());
    }

    public boolean horse(T clazz){
       // String[] methodNames=clazz.getClass().getAnnotation(WorkAnnotation.class).methodNames();
       // List<Method> targetMehods=ReflectionHelper.getMethodByNames()
        //TODO
        return true;
    }

    /**
     *
     * @param a a special param example
     * @return
     */
    public boolean monkey(ClassGenericFactory<? extends InterfaceAdancePlugin> a){
        //TODO

        return true;
    }

    /**
     * This is a relative monkey method.
     * @param a
     * @return
     */
    public boolean mouse(Genericity<? super BaseEntity> a){
        //TODO

        return true;
    }
}


