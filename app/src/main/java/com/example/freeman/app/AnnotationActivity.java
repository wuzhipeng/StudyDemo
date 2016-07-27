package com.example.freeman.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freeman.ViewAnnotation;

import java.lang.reflect.Field;

public class AnnotationActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewAnnotation(R.id.annotation_text)
    private TextView mText;
    @ViewAnnotation(R.id.annotation_btn)
    private Button mBtn;

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
                Toast.makeText(this, "通过注解拿到了Button", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
