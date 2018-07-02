package com.example.administrator.myapptextttttttt.activity;

import android.util.Log;
import android.view.View;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.gradualchange.TranslucentActionBar;
import com.example.administrator.myapptextttttttt.views.gradualchange.TranslucentScrollView;

import butterknife.BindView;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/19
 * 描述: 渐变标题
 */

public class GradualChangeActivity extends BaseActivity implements ActionBarClickListener, TranslucentScrollView.TranslucentChangedListener {
    //外边的scrollview
    @BindView(R.id.pullzoom_scrollview)
    TranslucentScrollView translucentScrollView;
    //标题
    @BindView(R.id.actionbar)
    TranslucentActionBar actionBar;
    //头部拉伸view
    @BindView(R.id.lay_header)
    View zoomView;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

        //初始actionBar
        getTitleView().setData("刘小伟TTT", R.color.black, R.drawable.back, "", R.drawable.barrage_fill, "", this);


        //设置透明度变化监听
        translucentScrollView.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        translucentScrollView.setTransView(actionBar);
        //设置ActionBar键渐变颜色
        translucentScrollView.setTransColor(getResources().getColor(R.color.red));

        //关联伸缩的视图
        translucentScrollView.setPullZoomView(zoomView);

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_gradual_hange;
    }

    @Override
    public void onLeftClick() {
        Log.e("eeeee", "点击左边！！");

    }

    @Override
    public void onRightClick() {
        Log.e("eeeee", "点击右边！！");
    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        actionBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
        actionBar.layLeft.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
        actionBar.layRight.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }
}
