package org.gjgr.book.corejava.v2ch10.sourceAnnotations;
import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Property
{
   String editor() default ""; 
}
