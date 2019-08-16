package edu.muc.gwd.item;

import java.lang.reflect.Type;

/**
 * Created by gwd on 9/10/2016.
 */
public class Element<T> implements ElementNumeralization<Object> {

    protected T t;

    public Element(T t) {
        this.t = t;
        this.clazz = t.getClass();
        this.setType(t.getClass().getGenericSuperclass());
    }

    public Element() {
    }

    private Type type;

    private Class<?> clazz;

    public boolean setElement(T t) {
        this.t = t;
        this.clazz = t.getClass();
        this.setType(t.getClass().getGenericSuperclass());
        return true;
    }

    public T getElement() {
        return t;
    }

    public int plastic(ElementNumeralization<T> elementNumeralization) {
        return elementNumeralization.numberalization(t);
    }

    public Class<?> getClassType() {
        return clazz;
    }

    @Override
    public int numberalization(Object o) {

        return 0;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
