package edu.study.annotation;

/**
 * Created by gwd on 9/2/2016.
 */

/**
 * @Retention(RetentionPolicy.Runtime)
 * @Target(ElementType.TYPE)
 *
 */
public @interface WorkAnnotation {

    int dragon() default 0;

}
