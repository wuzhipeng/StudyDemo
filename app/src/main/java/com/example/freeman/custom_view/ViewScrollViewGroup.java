package com.example.freeman.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ViewScrollViewGroup extends LinearLayout {
    private boolean viewGroupFlag = true;
    private boolean mScrollLayout = false;
    private Scroller mViewGroupScroller = null;
    private int mLastX = 0;

    private OnScrollListener mListener;

    public ViewScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewGroupScroller = new Scroller(context, new DecelerateInterpolator());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewGroupScroller.computeScrollOffset()) {
            int currX = mViewGroupScroller.getCurrX();
            if(!mScrollLayout) {
                scrollTo(currX, 0);
            } else {
                offsetLeftAndRight(mLastX - currX);
                mLastX = currX;
            }
            invalidate();
        } else {
            if(mListener != null) mListener.onViewGroupScrollEnd();
        }
    }

    public void setOnScrollListener(OnScrollListener l) {
        mListener = l;
    }

    public void startViewGroupScroll(boolean scrollLayout) {
        mScrollLayout = scrollLayout;
        if (viewGroupFlag) {
            mViewGroupScroller.startScroll(mViewGroupScroller.getCurrX(), 0, -400, 0, 300);
            viewGroupFlag = false;
        } else {
            mViewGroupScroller.startScroll(mViewGroupScroller.getCurrX(), 0, 400, 0, 300);
            viewGroupFlag = true;
        }
        postInvalidate();    // 需要主动调用刷新View，从而触发view的不断绘制
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float currX = 0;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currX = event.getX();
                mViewGroupScroller.forceFinished(true); // 强制结束动画，参数为true，表示停止在动画强制结束时的位置上；false则表示动画继续进行直到结束
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                Log.d("Scroller-CurrX:", "" + mViewGroupScroller.getCurrX());
                Log.d("duration", "" + mViewGroupScroller.getDuration());
                Log.d("finalX", "" + mViewGroupScroller.getFinalX());
                Log.d("timePassed", "" + mViewGroupScroller.timePassed());
                mViewGroupScroller.startScroll(mViewGroupScroller.getCurrX(), 0, (mViewGroupScroller.getFinalX() - mViewGroupScroller.getCurrX()),
                        0, (mViewGroupScroller.getDuration() - mViewGroupScroller.timePassed()));
                postInvalidate();
                break;
        }
        return true;
    }

    public interface OnScrollListener {
        void onViewGroupScrollEnd();
    }
}