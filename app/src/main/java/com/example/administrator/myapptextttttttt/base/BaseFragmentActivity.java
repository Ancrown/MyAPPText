package com.example.administrator.myapptextttttttt.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseApplication;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.gradualchange.TranslucentActionBar;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 碎片活动基础类
 *
 * @创建时间 ：2016/11/21
 * @项目名称 ：Circle
 * @类型名称 : BaseFragmentActivity
 * @文件类型 : BaseFragmentActivity.java
 * @创始人 : 刘晓伟
 * @包名 ：com.ioi.distribution.base
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private boolean isRightSlipExit = true;


    /**
     * 是否禁止横屏，默认禁止
     */
    private boolean isHorizontalScreen = true;
    /**
     * 弹出的软键盘是否影响布局，默认不影响
     */
    private boolean isAffectTheLayout = true;
    public static final String TAG = "BaseActivity";

    private BaseApplication application;

    public void setIsAffectTheLayout(boolean isAffectTheLayout) {
        this.isAffectTheLayout = isAffectTheLayout;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    /**
     * 菜单管理栈
     */
    private Stack<Activity> activityStack;

    public BaseFragmentActivity() {
        //获取应用实例
        application = BaseApplication.getInstance();
        //获取菜单管理栈
        activityStack = application.getActivityStack();
    }

    protected FrameLayout frameLayoutBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();

        resources.updateConfiguration(config, dm);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        isRightSlipExit = isRightSlipExit();
        frameLayoutBase = new FrameLayout(this);
        setContentView(frameLayoutBase);
        frameLayoutBase.addView(LayoutInflater.from(this).inflate(getLayout(), null));

        //初始化注解
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();

        //禁止横屏
        if (isHorizontalScreen)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //弹出的软键盘不影响布局
        if (isAffectTheLayout)
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //添加Activity到堆栈
        addActivity(this);

    }


    private TranslucentActionBar actionBar;

    //显示标题
    public void showTitle() {
        actionBar = (TranslucentActionBar) findViewById(R.id.actionbar);
        //显示
        actionBar.setNeedTranslucent(true, true);
        //设置状态栏高度
        actionBar.setStatusBarHeight(ToolUtils.getStatusBarHeight(this));
    }

    public TranslucentActionBar getTitleView() {
        showTitle();
        return actionBar;
    }





    /**
     * 是否启动右滑退出
     *
     * @return
     */
    public boolean isRightSlipExit() {
        Log.e("sssssssss", "p");
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //从栈中移除Activity
        activityStack.remove(this);
    }

    public void onBack(View v) {
        finish();
    }

    /**
     * 获取应用实例
     *
     * @return 应用实例
     */
    public BaseApplication getBaseApplication() {
        return application;
    }

    /******************** activity管理 ********************/

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack.size() > 0) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        }

    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {

        if (activityStack.size() > 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
    }

    /**
     * 结束activity到指定位置
     *
     * @param clazz 指定的activity
     */
    public void finishActivityTo(Class<?> clazz) {
        while (!activityStack.empty()) {
            Activity activity = activityStack.peek();
            if (activity.getClass().equals(clazz)) {
                break;
            } else {
                activity.finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    // Press the back button in mobile phone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(0, R.anim.base_slide_right_out);
//        Log.e("sssssssssssss","onBackPressed=");
//        if(dialog!=null){
//            Log.e("sssssssssssss","onBackPressed="+dialog.getDialog().isShowing());
//        }
    }

    /**
     * 跳转页面，startActivity启动
     *
     * @param aty
     * @param cls
     */
    public void startActivity(Activity aty, Class cls) {
        Intent intent = new Intent(aty, cls);
        aty.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
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
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
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
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
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
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    /**
     * 弹出信息short
     *
     * @param msg 弹出具体信息
     */
    public void setShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出信息long
     *
     * @param msg 弹出具体信息
     */
    public void setLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    /**
     * 初始化数据
     *
     * @return
     */
    protected abstract void initData();

    /**
     * 初始化监听
     *
     * @return
     */
    protected abstract void initListener();

    /**
     * 初始化控件，集合等
     *
     * @return
     */
    protected abstract void initView();

    /**
     * 添加布局文件
     *
     * @return
     */
    protected abstract int getLayout();

}
