package edu.design.creational.factory;

/**
 * Created by gwd on 9/2/2016.
 */
public class BaseEntity {

    /**
     * Here is a super container.
     * @param t
     * @param <T>
     * @return
     */
    public <T> T snake(T t){
        return  t;
    }

    /**
     * like above snake method, it could also do the same things
     * @param param
     * @param <T>
     * @return
     */
    public <T extends String> Genericity<T>  snakeGenericity(T param){
        Genericity<T> temp=new Genericity<>();
        //TODO
        return temp;
    }

}
