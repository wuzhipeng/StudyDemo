package com.example.freeman.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeman.Annotation.AnnotationClass;
import com.example.freeman.Annotation.TestAnnotation;
import com.example.freeman.Annotation.ViewAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewAnnotation(R.id.annotation_text)
    private TextView mText;
    @ViewAnnotation(R.id.annotation_btn)
    private Button mBtn;

    private AnnotationClass person = new AnnotationClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        try {
            injectView();
            if (mText != null) {
                mText.setText("成功使用注解初始化View");
            }

            if (mBtn != null) {
                mBtn.setOnClickListener(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPerson(AnnotationClass obj) throws Exception {
        Class cls = obj.getClass();
        Method method = cls.getDeclaredMethod("setPerson", String.class, String.class, int.class);
        if (method != null) {
            TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
            String name = annotation.name();
            String sex = annotation.sex();
            int age = annotation.age();
            method.setAccessible(true);
            method.invoke(obj, name, sex, age);
        }
    }

    private void injectView() throws Exception {
        Class cls = this.getClass();
        Field[] fields = cls.getDeclaredFields();
        if (fields != null) {
            for (Field f : fields) {
                if (f.isAnnotationPresent(ViewAnnotation.class)) {
                    ViewAnnotation viewAnnotation = f.getAnnotation(ViewAnnotation.class);
                    int value = viewAnnotation.value();
                    if (value > 0) {
                        f.setAccessible(true);
                        f.set(this, findViewById(value));
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.annotation_btn:
                try {
                    setPerson(person);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, person.getPerson(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
