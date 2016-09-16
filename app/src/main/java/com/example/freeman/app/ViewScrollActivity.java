package com.example.freeman.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.freeman.custom_view.ViewScrollViewGroup;

public class ViewScrollActivity extends AppCompatActivity implements View.OnClickListener, ViewScrollViewGroup.OnScrollListener {

    private Button mScrollContentBtn = null;
    private Button mScrollLayoutBtn = null;
    private ViewScrollViewGroup viewScrollViewGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scroll);
        mScrollContentBtn = (Button) findViewById(R.id.scroll_content_btn);
        mScrollLayoutBtn = (Button) findViewById(R.id.scroll_layout_btn);
        viewScrollViewGroup = (ViewScrollViewGroup) findViewById(R.id.mViewgroup);
        viewScrollViewGroup.setOnScrollListener(this);
        mScrollContentBtn.setOnClickListener(this);
        mScrollLayoutBtn.setOnClickListener(this);
    }

    @Override
    public void onViewGroupScrollEnd() {
        mScrollContentBtn.setEnabled(true);
        mScrollLayoutBtn.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scroll_content_btn:
                viewScrollViewGroup.startViewGroupScroll(false);        // 开始滚动，滚动的是ViewGroup的内容
                mScrollContentBtn.setEnabled(false);
                mScrollLayoutBtn.setEnabled(false);
                break;
            case R.id.scroll_layout_btn:
                viewScrollViewGroup.startViewGroupScroll(true);        // 开始滚动，滚动的是ViewGroup的整体布局
                mScrollLayoutBtn.setEnabled(false);
                mScrollContentBtn.setEnabled(false);
                break;
        }
    }
}
