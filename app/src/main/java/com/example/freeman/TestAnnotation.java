package com.example.freeman;

/**
 * 自定义注解
 * Created by freeman on 2016/7/26.
 */
public @interface TestAnnotation {
    boolean isShow() default true;

    String message() default "请稍后，加载中";
}