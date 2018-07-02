package com.example.administrator.myapptextttttttt.base;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.twinklingrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/8
 * 描述:
 */

public abstract class BaseRecyclerActivity extends BaseActivity {


    @BindView(R.id.recycler)
    public RecyclerView recyclerView;

    @BindView(R.id.refreshlayout)
    public TwinklingRefreshLayout refreshLayout;

    public LinearLayoutManager lmr;

    public GridLayoutManager mgr;


    public void initRecyCler() {
        //是否允许越界时显示刷新控件（setOverScrollTopShow,setOverScrollBottomShow统一设置方法）
        refreshLayout.setOverScrollRefreshShow(false);
        //支持切换到像SwipeRefreshLayout一样的悬浮刷新模式了。
        refreshLayout.setFloatRefresh(true);

        //listview 效果
        lmr = new LinearLayoutManager(this);
        //设置为垂直布局，这也是默认的
        lmr.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(lmr);

    }

    //设置 RecyClerView 的样式 横竖 表格
    public void setLayoutMa(int type, int page) {
        switch (type) {
            //水平
            case 0:
                lmr.setOrientation(OrientationHelper.VERTICAL);
                recyclerView.setLayoutManager(lmr);
                break;
            case 1:
                lmr.setOrientation(OrientationHelper.HORIZONTAL);
                recyclerView.setLayoutManager(lmr);
                break;
            case 2:
                mgr = new GridLayoutManager(this, page);
                recyclerView.setLayoutManager(mgr);
                break;
        }
    }


    //根据手势 结束下拉上拉
    public void endRefresh(String gesture) {
        switch (gesture) {
            case "":
                break;
            case "Refresh":
                refreshLayout.finishRefreshing();
                break;
            case "Loadmore":
                refreshLayout.finishLoadmore();
                break;
        }
    }

    @Override
    protected void initView() {
        initRecyCler();

    }

    @Override
    protected int getLayout() {
        return R.layout.base_refresh_recycler;
    }

}
