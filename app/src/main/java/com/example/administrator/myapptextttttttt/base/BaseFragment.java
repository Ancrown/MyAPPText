package com.example.administrator.myapptextttttttt.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.gradualchange.TranslucentActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * 作者 ：刘晓伟
 * 工程名 ：ForManWorker
 * 包名 ：com.ioi.library.base
 * 描述 ：Mark
 * 创建时间 ：2016/4/5
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = "BaseFragment";

    protected FrameLayout rootView;
    Unbinder mUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        if (rootView == null) {
        rootView = new FrameLayout(getContext());

//        rootView = inflater.inflate(getLayoutId(), null);
        rootView.addView(inflater.inflate(getLayoutId(), null));

        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mUnbinder = ButterKnife.bind(this, rootView);

        initView();
//        }
        refreshData();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (rootView != null) {
//            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
//            viewGroup.removeAllViews();
//        }

        ViewParent parent = rootView.getParent();
        //所有的控件 都有父控件  父控件一般情况下 就是ViewGoup
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(rootView);
        }
        if (null != mUnbinder)
            mUnbinder.unbind();
    }


    private TranslucentActionBar actionBar;

    //显示标题
    public void showTitle() {
        actionBar = (TranslucentActionBar) rootView.findViewById(R.id.actionbar);
        //显示
        actionBar.setNeedTranslucent(true, true);
        //设置状态栏高度
        actionBar.setStatusBarHeight(ToolUtils.getStatusBarHeight(getActivity()));
    }

    public TranslucentActionBar getTitleView() {
        showTitle();
        return actionBar;
    }





    /**
     * 资源id
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     *
     * @return
     */
    public abstract void initView();

    /**
     * 刷新数据
     */
    public abstract void refreshData();

    /**
     * 跳转页面，startActivity启动
     *
     * @param aty
     * @param cls
     */
    public void startActivity(Activity aty, Class cls) {
        Intent intent = new Intent(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * 跳转页面，startActivity启动传值
     *
     * @param aty
     * @param cls
     * @param b
     */
    public void startActivity(Activity aty, Class cls, Bundle b) {
        Intent intent = new Intent(aty, cls);
        intent.putExtra("bundle", b);
        aty.startActivity(intent);
    }

    /**
     * 跳转页面，startActivityForResult
     *
     * @param aty
     * @param cls
     */
    public void startActivityForResult(Activity aty, Class cls) {
        Intent intent = new Intent(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * 跳转页面，startActivityForResult启动传值
     *
     * @param aty
     * @param cls
     * @param b
     */
    public void startActivityForResult(Activity aty, Class cls, Bundle b) {
        Intent intent = new Intent(aty, cls);
        intent.putExtra("bundle", b);
        aty.startActivity(intent);
    }

    /**
     * 跳转页面，fragment启动startActivityForResult
     *
     * @param frg
     * @param aty
     * @param cls
     */
    public void startActivityForResult(Fragment frg, Activity aty, Class cls) {
        Intent intent = new Intent(aty, cls);
        frg.startActivity(intent);
    }

    /**
     * 跳转页面，fragment启动startActivityForResult启动传值
     *
     * @param aty
     * @param cls
     * @param b
     */
    public void startActivityForResult(Fragment frg, Activity aty, Class cls, Bundle b) {
        Intent intent = new Intent(aty, cls);
        intent.putExtra("bundle", b);
        frg.startActivity(intent);
    }

    /**
     * 弹出信息short
     *
     * @param msg 弹出具体信息
     */
    public void setShortToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出信息long
     *
     * @param msg 弹出具体信息
     */
    public void setLongToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}