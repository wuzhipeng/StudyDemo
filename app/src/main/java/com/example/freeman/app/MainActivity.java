package com.example.freeman.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.freeman.model.ReflectClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    TextView tv1;
    TextView tv2;
    TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);
        tv1 = (TextView) findViewById(R.id.text1);
        tv2 = (TextView) findViewById(R.id.text2);
        tv3 = (TextView) findViewById(R.id.text3);
        StringBuilder sb = new StringBuilder();

        try {
            getClass(sb);
            tv.setText(sb.toString());
            sb.delete(0, sb.length());

            getDeclaredMethods(sb);
            tv1.setText(sb.toString());
            sb.delete(0, sb.length());

            getDeclaredFields(sb);
            tv2.setText(sb.toString());
            sb.delete(0, sb.length());

            setFieldsValue(sb);
            tv3.setText(sb.toString());
        } catch (Exception e) {
            Log.e("反射", e.getMessage());
        }
    }

    private void setFieldsValue(StringBuilder sb) throws Exception {
        ReflectClass obj = new ReflectClass();
        Class cls = obj.getClass();

        Field f = cls.getDeclaredField("intValue");
        setFieldValue(obj, f, 10);
        sb.append("intValue的值：").append(getFieldValue(obj, f)).append("\n");

        Method m = cls.getDeclaredMethod("setDoubleValue", double.class);
        setFieldValue(obj, m, 20.02345);
        m = cls.getDeclaredMethod("getDoubleValue");
        sb.append("doubleValue的值：").append(getFieldValue(obj, m)).append("\n");

        f = cls.getDeclaredField("mString");
        setFieldValue(obj, f, "Freeman");
        sb.append("mString的值：").append(getFieldValue(obj, f)).append("\n");

    }

    /**
     * 通过反射类的成员变量设置值
     *
     * @param obj
     * @param f
     * @param value
     * @throws Exception
     */
    private void setFieldValue(ReflectClass obj, Field f, Object value) throws Exception {
        f.setAccessible(true);
        f.set(obj, value);
    }

    /**
     * 通过反射类的方法设置成员变量的值
     *
     * @param obj
     * @param method
     * @param value
     * @throws Exception
     */
    private void setFieldValue(ReflectClass obj, Method method, Object... value) throws Exception {
        method.setAccessible(true);
        method.invoke(obj, value);
    }

    /**
     * 通过反射类的方法获取成员变量的值
     *
     * @param obj
     * @param method
     * @return
     * @throws Exception
     */
    private Object getFieldValue(ReflectClass obj, Method method) throws Exception {
        method.setAccessible(true);
        return method.invoke(obj);
    }

    /**
     * 通过反射直接读取成员变量的值
     *
     * @param obj
     * @param f
     * @return
     * @throws Exception
     */
    private Object getFieldValue(ReflectClass obj, Field f) throws Exception {
        return f.get(obj);
    }

    /**
     * 获取类对象
     *
     * @param sb
     * @throws Exception
     */
    private void getClass(StringBuilder sb) throws Exception {
        Class cls1 = ReflectClass.class;
        Class cls2 = Class.forName("com.example.freeman.model.ReflectClass");

        ReflectClass obj = new ReflectClass();
        Class cls3 = obj.getClass();

        sb.append("通过三种方式获取ReflectClass的类对象：\n")
                .append("方式1：ReflectClass.class   \n")
                .append(cls1)
                .append("\n\n方式2：Class.forName(\"com.example.freeman.model.ReflectClass\")   \n")
                .append(cls2)
                .append("\n\n方式3：ReflectClass obj.getClass()   \n")
                .append(cls3);
    }

    /**
     * 获取类的全部方法
     *
     * @param sb
     * @throws Exception
     */
    private void getDeclaredMethods(StringBuilder sb) throws Exception {
        Class cls1 = ReflectClass.class;
        sb.append("\n\n").append("获取ReflectClass类的全部方法");
        Method[] methods = cls1.getDeclaredMethods();
        for (Method method : methods) {
            sb.append(Modifier.toString(method.getModifiers())).append(" ")
                    .append(method.getReturnType()).append(" ")
                    .append(method.getName()).append("(");
            Class[] params = method.getParameterTypes();
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    Class paramCls = params[i];
                    sb.append(paramCls.getSimpleName());
                    if (i < params.length - 1) sb.append(", ");
                }
            }
            sb.append(")\n\n");
        }
    }

    /**
     * 获取类的全部成员变量
     *
     * @param sb
     * @throws Exception
     */
    private void getDeclaredFields(StringBuilder sb) throws Exception {
        Class cls1 = ReflectClass.class;
        sb.append("\n\n").append("获取ReflectClass类的成员变量\n");
        Field[] fields = cls1.getDeclaredFields();
        for (Field f : fields) {
            sb.append(Modifier.toString(f.getModifiers())).append(" ");
            sb.append(f.getType().getSimpleName()).append(" ");
            sb.append(f.getName()).append(";");
            sb.append("\n\n");
        }
    }

}
