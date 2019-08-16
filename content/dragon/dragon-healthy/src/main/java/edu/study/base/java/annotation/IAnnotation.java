package edu.study.base.java.annotation;

/**
 * Created by gwd on 9/2/2016.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 编译时注解
 * Source:注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃. 这意味着：Annotation仅存在于编译器处理期间，编译器处理完之后，该Annotation就没用了
 * Class:注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期.
 * RUNTIME:注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在,保存到class对象中，可以通过反射来获取
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface IAnnotation {
    String getName();
}

/**
 * @Documented：标记注解，用于描述其它类型的注解应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。
 * @Inherited：标记注解，允许子类继承父类的注解
 * @Retention：指Annotation被保留的时间长短，标明注解的生命周期，3种RetentionPolicy取值含义上面以说明
 * @Target：标明注解的修饰目标，共有
 */

/**
 * ElementType.CONSTRUCTOR     构造器声明
 * ElementType.FIELD           字段声明
 * ElementType.LOCAL_VARIABLE  局部变量声明
 * ElementType.METHOD          方法声明
 * ElementType.PACKAGE         包声明
 * ElementType.PARAMETER       参数声明
 * ElementType.TYPE            类、接口或枚举声明
 * ElementType.ANNOTATION_TYPE 注解类型声明
 */