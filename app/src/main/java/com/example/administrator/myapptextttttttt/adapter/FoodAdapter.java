package com.example.administrator.myapptextttttttt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.base.BaseRecyclerAdapter;
import com.example.administrator.myapptextttttttt.adapter.base.CommonHolder;
import com.example.administrator.myapptextttttttt.beans.Food;
import com.example.administrator.myapptextttttttt.views.CircleImageView;

import butterknife.BindView;


/**
 * Created by lcodecore on 2016/12/6.
 */

public class FoodAdapter extends BaseRecyclerAdapter<Food> {
    @Override
    public CommonHolder<Food> setViewHolder(ViewGroup parent, int viewtype) {
        return new CardHolder(parent.getContext(), parent);
    }

    class CardHolder extends CommonHolder<Food> {

        @BindView(R.id.avatar)
        CircleImageView avatar;

        @BindView(R.id.tv_food)
        TextView tv_food;

        @BindView(R.id.tv_info)
        TextView tv_info;

        @BindView(R.id.iv_food)
        ImageView iv_food;

        public CardHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_food);
        }

        @Override
        public void bindData(final Food food, int t) {
            avatar.setImageResource(food.avatar_id);
            iv_food.setImageResource(food.imageSrc);
            tv_food.setText(food.title);
            tv_info.setText(food.info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.e("eeeee", food.title);
                }
            });
        }
    }
}
