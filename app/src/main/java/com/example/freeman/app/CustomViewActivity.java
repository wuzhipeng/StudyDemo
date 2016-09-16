package com.example.freeman.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.freeman.custom_view.HorizontalScrollView;

public class CustomViewActivity extends AppCompatActivity {
    HorizontalScrollView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        mView = (HorizontalScrollView) findViewById(R.id.scrollView);
    }
}
