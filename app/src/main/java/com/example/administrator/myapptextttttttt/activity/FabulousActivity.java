package com.example.administrator.myapptextttttttt.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.views.like.LikeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/30
 * 描述: 点赞
 */

public class FabulousActivity extends BaseActivity {
    @BindView(R.id.lv)
    LikeView lv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("比心", 0, 0, null, 0, null, null);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_fabulous;
    }


    @OnClick(R.id.lv)
    public void onViewClicked() {
        Log.i("LikeView", "onClick"+lv.isChecked());
    }
}
