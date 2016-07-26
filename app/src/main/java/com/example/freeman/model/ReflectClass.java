package com.example.freeman.model;

/**
 * Created by freeman on 2016/7/26.
 */
public class ReflectClass {
    private int intValue;
    private double doubleValue;

    public String mString;

    public void setString(String s) {
        mString = s;
    }

    private void setIntValue(int i) {
        intValue = i;
    }

    private void setDoubleValue(double d) {
        doubleValue = d;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }
}
