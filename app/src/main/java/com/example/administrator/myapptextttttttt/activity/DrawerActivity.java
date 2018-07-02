package com.example.administrator.myapptextttttttt.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.RecyclerviewAdapter;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.beans.RecyclerViewBean;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.RefreshListenerAdapter;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.example.administrator.myapptextttttttt.views.MySlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/25
 * 描述:
 */

public class DrawerActivity extends BaseActivity implements ActionBarClickListener {


    @BindView(R.id.drawer_recycler)
    RecyclerView drawerRecycler;

    @BindView(R.id.drawer_tfl)
    TwinklingRefreshLayout drawerTfl;

    RecyclerviewAdapter adapter;

    @BindView(R.id.aty_drawer_menu_text)
    TextView atyDrawerMenuText;

    @BindView(R.id.slidingmenu)
    MySlidingMenu mSlidingMenu;

    @BindView(R.id.menu)
    ViewGroup mMenu;

    @BindView(R.id.content)
    ViewGroup mContent;

    private List<RecyclerViewBean> list;

    private CustomLinearLayoutManager cllm = new CustomLinearLayoutManager(this);
    private LinearLayoutManager llm = new LinearLayoutManager(this);

    @Override
    protected void initData() {
        for (int i = 0; i < 50; i++) {
            list.add(new RecyclerViewBean(("~~~~~~~~~" + i + "  " + i), "" + i, 0));
        }
        adapter.setDataList(list);
    }

    @Override
    protected void initListener() {
        drawerTfl.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "下拉");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
                //结束下拉
                // refreshLayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                Log.e("eeeee", "ListView" + "上拉");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);
                //结束加载更多
            }

        });
    }

    @Override
    protected void initView() {
        //初始actionBar
        getTitleView().setData("抽屉菜单", 0, R.drawable.dian_dian_dian, "", R.drawable.map, "", this);
        drawerTfl.setFloatRefresh(true);
        adapter = new RecyclerviewAdapter();
        list = new ArrayList<>();

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        cllm.setOrientation(LinearLayoutManager.VERTICAL);
        cllm.setScrollEnabled(false);
        drawerRecycler.setLayoutManager(llm);
        drawerRecycler.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_drawer;
    }


    @Override
    public void onLeftClick() {
        mSlidingMenu.toggleMenu();
        //    drawerRecycler.setLayoutManager(mSlidingMenu.isOpen() ? cllm : llm);
        Log.e("eeeeee", "抽屉：" + mSlidingMenu.isOpen() + "  TTT");
        // cllm.setScrollEnabled(mSlidingMenu.isOpen() ? false : true);
    }

    @Override
    public void onRightClick() {

    }


    public class CustomLinearLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
            return isScrollEnabled && super.canScrollVertically();
        }
    }

}
