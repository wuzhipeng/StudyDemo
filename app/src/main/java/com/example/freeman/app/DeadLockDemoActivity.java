package com.example.freeman.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Belmont on 2016/9/23.
 */

public class DeadLockDemoActivity extends Activity {

    private final Object mLock1 = new Object();
    private final Object mLock2 = new Object();
    private int i = 0;
    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deadlocklayout);
        mTextView = (TextView)findViewById(R.id.text);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock2();
            }
        });
        t1.start();
        t2.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(mTextView.getText() + (String)msg.obj);
        }
    };

    private void lock1() {
        while(i < 100) {
            synchronized (mLock1) {
                i++;

                Message message = Message.obtain();
                message.obj = "lock1，等待lock2\n";
                mHandler.sendMessage(message);
                try {
                    Thread.sleep(30);
                    lock2();

                }catch (Exception e) {

                }
            }
        }
    }

    private void lock2() {
        while(i < 100) {
            synchronized (mLock2) {
                i++;
                Message message = Message.obtain();
                message.obj = "lock2，等待lock1\n";
                mHandler.sendMessage(message);
                try {
                    Thread.sleep(30);
                    lock1();

                } catch (Exception e) {

                }
            }
        }
    }
}
