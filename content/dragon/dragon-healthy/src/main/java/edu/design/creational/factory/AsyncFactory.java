package edu.design.creational.factory;

import java.util.concurrent.ExecutionException;

/**
 * Created by gwd on 9/4/2016.
 * This we would be know AsyncFactory extends Factory Interface
 */
public interface AsyncFactory extends Factory {
    /**
     * To async new instance by assigning a work
     */
    public <T> T assyncNewInstance(final Class<?> abstractClass) throws InstantiationException,IllegalArgumentException,InterruptedException,ExecutionException;

}
