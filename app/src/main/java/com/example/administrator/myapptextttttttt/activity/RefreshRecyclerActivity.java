package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.FoodAdapter;
import com.example.administrator.myapptextttttttt.beans.Food;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.RefreshListenerAdapter;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.TwinklingRefreshLayout;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.footer.LoadingView;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.header.SinaRefreshView;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.header.bezierlayout.BezierLayout;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 内嵌recyclerView的下拉上拉加载
 */

public class RefreshRecyclerActivity extends Activity {

    private FoodAdapter foodAdapter;
    private List<Food> foods = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_refresh_recycler);

        setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        foodAdapter = new FoodAdapter();
        rv.setAdapter(foodAdapter);

        final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
        // 头部样式
        //ProgressLayout headerView = new ProgressLayout(this);
        // BezierLayout headerView = new BezierLayout(this);
        SinaRefreshView headerView = new SinaRefreshView(this);
        // headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        //设置头部样式
        //   refreshLayout.setHeaderView(headerView);

        //底部样式
        LoadingView loadingView = new LoadingView(this);
        //设置底部样式
        //     refreshLayout.setBottomView(loadingView);


        refreshLayout.setOverScrollRefreshShow(false);


        refreshCard();
        foodAdapter.setDataList(foods);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCard();
                        foodAdapter.setDataList(foods);
                        refreshLayout.finishRefreshing();
                    }
                }, 5000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCard();
                        foodAdapter.addItems(foods);
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });

    }


    void refreshCard() {
        foods.add(new Food("Preparing Salmon Steak Close Up", "BY VIKTOR HANACEK", R.drawable.food1, R.drawable.avatar0));
        foods.add(new Food("Fresh & Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food2, R.drawable.avatar0));
        foods.add(new Food("Enjoying a Tasty Burger", "BY VIKTOR HANACEK", R.drawable.food3, R.drawable.avatar0));
        foods.add(new Food("Fresh Strawberries and Blackberries in Little Bowl", "BY VIKTOR HANACEK", R.drawable.food4, R.drawable.avatar0));
        foods.add(new Food("Baked Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food5, R.drawable.avatar0));
    }

}
