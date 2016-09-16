package com.example.freeman.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button reflectBtn;
    Button annotationBtn;
    Button customViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reflectBtn = (Button) findViewById(R.id.reflect_btn);
        annotationBtn = (Button) findViewById(R.id.annotation_btn);
        customViewBtn = (Button) findViewById(R.id.custom_view_btn);
        reflectBtn.setOnClickListener(this);
        annotationBtn.setOnClickListener(this);
        customViewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.reflect_btn:
                intent = new Intent(this, ReflectActivity.class);
                startActivity(intent);
                break;
            case R.id.annotation_btn:
                intent = new Intent(this, AnnotationActivity.class);
                startActivity(intent);
                break;
            case R.id.custom_view_btn:
                intent = new Intent(this, CustomViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
