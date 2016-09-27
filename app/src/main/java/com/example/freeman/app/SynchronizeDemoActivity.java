package com.example.freeman.app;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SynchronizeDemoActivity extends AppCompatActivity {
    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deadlocklayout);
        mTextView = (TextView)findViewById(R.id.text);
        new Thread(ticketRunnable, "乘客1").start();
        new Thread(ticketRunnable, "乘客2").start();
        new Thread(ticketRunnable, "乘客3").start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(mTextView.getText() + (String)msg.obj);
        }
    };

    private Runnable ticketRunnable = new Runnable() {
        private int tickets = 30;
        @Override
        public void run() {
            synchronized (this) {
            while(tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tickets--;
                    Message message = Message.obtain();
                    message.obj = Thread.currentThread().getName() + "抢到票 " + tickets + "\n";
                    mHandler.sendMessage(message);
                }
            }
        }
    };
}
