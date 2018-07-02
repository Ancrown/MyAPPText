package com.example.administrator.myapptextttttttt.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.beans.Card;
import com.example.administrator.myapptextttttttt.observer.DisplayElement;
import com.example.administrator.myapptextttttttt.observer.Observer;
import com.example.administrator.myapptextttttttt.views.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcodecore on 2016/12/6.
 */

public class MusicAdapter extends BaseAdapter implements Observer, DisplayElement {

    private List<Card> cards = new ArrayList<>();


    public MusicAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Card getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MusicAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_music, null);
            holder = new MusicAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MusicAdapter.ViewHolder) convertView.getTag();
        }

        cards.get(position).registerObserver(this);

        holder.tv_title.setText(cards.get(position).title);
        holder.tv_subTitle.setText(cards.get(position).info);
        holder.mImageView.setImageResource(cards.get(position).imageSrc);

        if (cards.get(position).type == 1) {
            holder.tv_title.setText(cards.get(position).title + " 1111111");
        }

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onItem(cards.get(position));
                }
            }
        });
        return convertView;
    }

    @Override
    public void update() {
        Log.e("eeeee", "    update " + cards.size());
        notifyDataSetChanged();
    }

    @Override
    public void display() {
        Log.e("eeeee", "    display " + cards.size());
    }

    class ViewHolder {
        final CircleImageView mImageView;
        final TextView tv_title;
        final TextView tv_subTitle;

        ViewHolder(View view) {
            mImageView = (CircleImageView) view.findViewById(R.id.avatar);
            tv_title = (TextView) view.findViewById(R.id.tv_song);
            tv_subTitle = (TextView) view.findViewById(R.id.tv_singer);
        }
    }

    public void refreshCard() {
        cards.clear();
        cards.add(new Card("RefreshRecyclerActivity", "上拉+加载更多", R.drawable.avatar0));
        cards.add(new Card("TabLayoutActivity", "TabLayout", R.drawable.avatar0));
        cards.add(new Card("BroadcastActivity", "图片轮播", R.drawable.avatar0));
        cards.add(new Card("BroadcastNewActivity", "图片轮播View", R.drawable.avatar0));
        cards.add(new Card("FontRollingActivity", "文字轮播", R.drawable.avatar0));
        cards.add(new Card("NetworkRequestActivity", "网络请求", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        notifyDataSetChanged();
    }

    public void loadMoreCard() {
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        cards.add(new Card("WWWWWWWWWW", "WWWW", R.drawable.avatar0));
        notifyDataSetChanged();
    }

    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick {
        void onItem(Card card);
    }
}