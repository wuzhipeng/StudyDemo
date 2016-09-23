package com.example.freeman.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * Created by freeman on 2016/7/26.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewAnnotation {
    /**
     * 只有一个方法时命名为value，default后面是默认值
     * @return
     */
    int value() default 0;
}