package com.example.freeman.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.freeman.widget.recyclerview.Adapter;
import com.example.freeman.widget.recyclerview.WrapAdapter;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager1;
    RecyclerView.LayoutManager layoutManager2;
    RecyclerView recyclerView;
    WrapAdapter mWrapAdapter;

    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager1 = new GridLayoutManager(this, 2);
        layoutManager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager1);
        Adapter adapter = new Adapter(this, recyclerView);
        mWrapAdapter = new WrapAdapter(adapter);
        View header1 = createHeaderFooterView(recyclerView, Color.parseColor("#ff3ff1"), "this is header 1");
        View header2 = createHeaderFooterView(recyclerView, Color.parseColor("#f3ffa1"), "this is header 2");
        View header3 = createHeaderFooterView(recyclerView, Color.parseColor("#3fffa1"), "this is header 3");

        View footer = createHeaderFooterView(recyclerView, Color.parseColor("#3333d3"), "this is footer");
        mWrapAdapter.addHeaderView(header1);
        mWrapAdapter.addHeaderView(header2);
        mWrapAdapter.addHeaderView(header3);
        mWrapAdapter.addFooterView(footer);
        recyclerView.setAdapter(mWrapAdapter);
    }

    private View createHeaderFooterView(ViewGroup viewRoot, int color, String text) {
        // inflate方法最后的一个参数false表示，viewRoot只用来为R.layout.item_layout的rootView设置正确的LayoutParams。
        View header = LayoutInflater.from(this).inflate(R.layout.item_layout, viewRoot, false);
        TextView textView = (TextView) header.findViewById(R.id.text);
        textView.setBackgroundColor(color);
        textView.setText(text);
        ViewGroup.LayoutParams lp = textView.getLayoutParams();
        lp.height = 300;
        textView.setLayoutParams(lp);
        return header;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                recyclerView.setLayoutManager(layoutManager);
                break;
            case R.id.btn2:
                recyclerView.setLayoutManager(layoutManager1);
                break;
            case R.id.btn3:
                recyclerView.setLayoutManager(layoutManager2);
                break;
        }
    }
}
