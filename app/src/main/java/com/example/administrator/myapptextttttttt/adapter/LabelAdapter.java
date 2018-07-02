package com.example.administrator.myapptextttttttt.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class LabelAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private int type;

    public LabelAdapter(List<String> list, Context context, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = 8;
            convertView = new TextView(context);
            holder.textView = (TextView) convertView;
            holder.textView.setPadding(7, 5, 7, 5);
            holder.textView.setLayoutParams(lp);
            if (type == 1) {
                holder.textView.setBackground(context.getResources().getDrawable(R.drawable.semicircle_blue));
            } else
                holder.textView.setBackgroundColor(Color.parseColor("#4d7ecb"));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        return convertView;
    }

    class Holder {
        private TextView textView;
    }
}
