package com.example.freeman.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.freeman.model.ReflectClass;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);

        StringBuilder sb = new StringBuilder();

        try {
            Class cls1 = ReflectClass.class;
            Class cls2 = Class.forName("com.example.freeman.model.ReflectClass");

            ReflectClass obj = new ReflectClass();
            Class cls3 = obj.getClass();

            sb.append("通过三种方式获取ReflectClass的类对象：\n")
                    .append("方式1：ReflectClass.class   ")
                    .append(cls1)
                    .append("方式2：Class.forName(\"com.example.freeman.model.ReflectClass\")   ")
                    .append(cls2)
                    .append("方式3：ReflectClass obj.getClass()   ")
                    .append(cls3);

            tv.setText(sb.toString());
        } catch (Exception e) {

        }
    }
}
