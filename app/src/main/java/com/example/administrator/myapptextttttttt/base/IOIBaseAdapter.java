package com.example.administrator.myapptextttttttt.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Author  ：
 * Email   :
 * Function:
 */
public abstract class IOIBaseAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected Context context;

    public IOIBaseAdapter(Context context) {
        this.context = context;
    }

    public IOIBaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    public void setRefreshList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
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
}
