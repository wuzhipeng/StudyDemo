package com.example.freeman.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.freeman.app.R;

/**
 * Created by freeman on 2016/10/10.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView = null;

    public ViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
    }

}
