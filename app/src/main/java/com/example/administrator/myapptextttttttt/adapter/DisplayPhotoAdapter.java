package com.example.administrator.myapptextttttttt.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.base.BaseRecyclerAdapter;
import com.example.administrator.myapptextttttttt.adapter.base.CommonHolder;
import com.example.administrator.myapptextttttttt.utils.glideutils.GlideUtils;

import butterknife.BindView;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/23
 * 描述:
 */

public class DisplayPhotoAdapter extends BaseRecyclerAdapter<String> {


    @Override
    public CommonHolder<String> setViewHolder(ViewGroup parent,int viewType) {
        return new Hoder(parent.getContext(), parent, R.layout.item_img);
    }

    class Hoder extends CommonHolder<String> {
        //  private Context context;
        @BindView(R.id.item_img)
        ImageView itemImg;

        public Hoder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
            //   this.context = context
        }

        @Override
        public void bindData(final String s, final int layoutRes) {
            GlideUtils.LoadImage(getContext(), s, itemImg);
            itemImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickItem != null) {
                        onClickItem.onItem(s, layoutRes);
                    }
                }
            });
        }
    }

    /**
     * 点击item
     */
    public RecyclerviewAdapter.OnClickItem onClickItem;


    public void setOnClickItem(RecyclerviewAdapter.OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onItem(String t, int i);
    }
}
