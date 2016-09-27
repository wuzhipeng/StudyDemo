package com.example.freeman.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView = null;
    private MyAdapter mAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo);
        mListView = (ListView)findViewById(R.id.list_view);
        mAdapter = new MyAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pair pair = (Pair) mAdapter.getItem(position);
        Intent i = new Intent(this, (Class)pair.second);
        startActivity(i);
    }

    private class MyAdapter extends BaseAdapter{
        List<Pair<String, Class<? extends Activity>>> mList = new ArrayList<>();
        Context context = null;

        public MyAdapter(Context c) {
            context = c;
            mList.add(new Pair("死锁demo", DeadLockDemoActivity.class));
            mList.add(new Pair("同步demo", SynchronizeDemoActivity.class));
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = new TextView(context);
            }
            Pair pair = mList.get(position);
            ((TextView)convertView).setText((String)pair.first);
            return convertView;
        }
    }
}
