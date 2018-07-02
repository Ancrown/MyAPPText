package com.example.administrator.myapptextttttttt.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.base.BaseRecyclerAdapter;
import com.example.administrator.myapptextttttttt.adapter.base.CommonHolder;
import com.example.administrator.myapptextttttttt.beans.RecyclerViewBean;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;

import butterknife.BindView;

/**
 * 创建人:Administrator
 * 创建时间:2018/5/16
 * 描述:
 */
public class RecyclerviewAdapter extends BaseRecyclerAdapter<RecyclerViewBean> {


    @Override
    public CommonHolder<RecyclerViewBean> setViewHolder(ViewGroup parent, int viewType) {

        Log.e("eeeee111", " setViewHolder：" + viewType);
        if (viewType == 1) {
            return new CardHolder1(parent.getContext(), parent, R.layout.item_recycler);
        } else {
            return new CardHolder(parent.getContext(), parent, R.layout.item_recycler);
        }
    }


    @Override
    public int getItemViewType(int position) {

        if (getItemList().get(position).getType() == 1) {

            return 1;
        } else {
            return BaseRecyclerAdapter.TYPE_CONTENT;
        }
    }

    class CardHolder extends CommonHolder<RecyclerViewBean> {
        private Context context;

        @BindView(R.id.item_recycler_text)
        TextView itemRecyclerText;

        public CardHolder(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
            this.context = context;
        }


        @Override
        public void bindData(final RecyclerViewBean s, final int i) {
            itemRecyclerText.setText(s.getText());
            itemRecyclerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToolUtils.showToast(context, "点击: " + s);
                    if (onClickItem != null) {
                        onClickItem.onItem(s.getText(), i);
                    }
                }
            });
        }
    }

    class CardHolder1 extends CommonHolder<RecyclerViewBean> {
        private Context context;

        @BindView(R.id.item_recycler_text)
        TextView itemRecyclerText;

        public CardHolder1(Context context, ViewGroup root, int layoutRes) {
            super(context, root, layoutRes);
            this.context = context;
        }


        @Override
        public void bindData(final RecyclerViewBean s, final int i) {
            itemRecyclerText.setText(s.getText() + "  TTTTTTTTTTTTTTTTTTT");
            itemRecyclerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToolUtils.showToast(context, "点击: " + s);
                    if (onClickItem != null) {
                        onClickItem.onItem(s.getText(), i);
                    }
                }
            });
        }
    }

    /**
     * 点击item
     */
    public OnClickItem onClickItem;


    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onItem(String t, int i);
    }

}
