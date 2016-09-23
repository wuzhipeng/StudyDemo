package com.example.freeman.Annotation;

/**
 * Created by freeman on 2016/7/28.
 */
public class AnnotationClass {
    private String name;
    private String sex;
    private int age;

    public String getPerson() {
        return name + ", " + sex + ", " + age + "岁";
    }

    /**
     * 通过注解来设置值
     * @param name
     * @param sex
     * @param age
     */
    @TestAnnotation(name = "Tracy", sex = "女", age = 22)
    public void setPerson(String name, String sex, int age) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
