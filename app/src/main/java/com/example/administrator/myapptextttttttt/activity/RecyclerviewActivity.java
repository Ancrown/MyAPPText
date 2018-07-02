package com.example.administrator.myapptextttttttt.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.RecyclerviewAdapter;
import com.example.administrator.myapptextttttttt.adapter.base.CommonHolder;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.base.BaseRecyclerActivity;
import com.example.administrator.myapptextttttttt.beans.RecyclerViewBean;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.RefreshListenerAdapter;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.utils.VegaLayoutManager;
import com.github.florent37.fiftyshadesof.FiftyShadesOf;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人:Administrator
 * 创建时间:2018/5/16
 * 描述:
 */
public class RecyclerviewActivity extends BaseRecyclerActivity implements ActionBarClickListener {

//    @BindView(R.id.aty_recycler_view)
//    RecyclerView recyclerView;

    //    @BindView(R.id.aty_recycler_view_add)
//    TextView add;
//    @BindView(R.id.aty_recycler_view_delete)
//    TextView delete;
//    @BindView(R.id.aty_recycler_view_type)
//    TextView type;
    private boolean typeText;

    private RecyclerviewAdapter adapter;
    private List<RecyclerViewBean> list;


    private int i;

    @Override
    protected void initData() {
        for (int i = 0; i < 100; i++) {
            list.add(new RecyclerViewBean(("~~~~~~~~~" + i + "  " + i), "" + i % 2, i % 2));
        }
        adapter.setDataList(list);
    }

    @Override
    protected void initListener() {
        adapter.setOnClickItem(new RecyclerviewAdapter.OnClickItem() {
            @Override
            public void onItem(String o, int i) {
                {
                    Log.e("eeeeee", o + "  " + i);

                    final FiftyShadesOf fiftyShadesOf = FiftyShadesOf.with(RecyclerviewActivity.this)
                            .on(recyclerView)
                            .start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fiftyShadesOf.stop();
                        }
                    }, 2000);


                }
            }
        });
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        endRefresh("Refresh");
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        endRefresh("Loadmore");
                    }
                }, 1000);
            }
        });

    }

    @Override
    protected void initView() {

        super.initView();

        getTitleView().setData("RecyclerView", 0, 0, "跳到", 0, null, this);
        adapter = new RecyclerviewAdapter();
        View exHeader = View.inflate(this, R.layout.head_view, null);

        list = new ArrayList<>();


        //  recyclerView.setLayoutManager(new VegaLayoutManager());

        //设置Adapter
        recyclerView.setAdapter(adapter);
        //添加头部和多种布局不能一起  多种布局可以实现添加头部
        //adapter.setHeadHolder(exHeader);

        //设置分隔线
        //    recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

//    @Override
//    protected int getLayout() {
//        return R.layout.aty_recycler;
//    }
//
//    @OnClick({R.id.aty_recycler_view_add, R.id.aty_recycler_view_delete, R.id.aty_recycler_view_type})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.aty_recycler_view_add:
//                // list.add();
//                adapter.addItem(new RecyclerViewBean(("添加 " + (++i)), "" + i, 0));
//
//                break;
//            case R.id.aty_recycler_view_delete:
//                --i;
//                Log.e("eeeeee", "一个多少个：" + (adapter.getItemCount() - 1));
//                adapter.deletItem(adapter.getItemList().get(adapter.getItemCount() - 1));
//                break;
//            case R.id.aty_recycler_view_type:
//
//                if (typeText) {
//                    //设置布局管理器
//                    setLayoutMa(0, 0);
//                    typeText = false;
//                    type.setText("当前显示样式：ListView");
//
//                } else {
//                    //设置布局管理器
//                    setLayoutMa(0, 5);
//                    typeText = true;
//                    type.setText("当前显示样式：GridView");
//
//                }
//                break;
//
//        }
//    }

    @Override
    public void onLeftClick() {

//        adapter.smoothMoveToPosition(recyclerView, 15);
//
        if (typeText) {
            //设置布局管理器
            setLayoutMa(1, 0);
            typeText = false;

        } else {
            //设置布局管理器
            setLayoutMa(2, 5);
            typeText = true;
        }
    }

    @Override
    public void onRightClick() {

    }
}
