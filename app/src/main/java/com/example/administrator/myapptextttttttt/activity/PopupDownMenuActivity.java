package com.example.administrator.myapptextttttttt.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.beans.PopItem;
import com.example.administrator.myapptextttttttt.views.PopupDownMenu;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.myapptextttttttt.utils.Data.*;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/26
 * 描述:
 */

public class PopupDownMenuActivity extends AppCompatActivity implements View.OnClickListener {

    //三个按钮和整体布局
    private TextView tv_demo;
    private TextView tv_demo2;
    private TextView tv_demo3;

    //存放列表数据的List
    private List<PopItem> itemList = new ArrayList<>();
    private List<PopItem> itemList2 = new ArrayList<>();
    private List<PopItem> itemList3 = new ArrayList<>();

    //黑色背景布局
    private View darkView;
    private Animation animIn;
    private Animation animOut;

    //PopupDownMenu的定义
    private PopupDownMenu p1;
    private PopupDownMenu p2;
    private PopupDownMenu p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_popup_down);

        initData();
        initView();
        initPop();
    }

    private void initView() {
        //三个按钮的初始化
        tv_demo = (TextView) findViewById(R.id.tv_demo);
        tv_demo2 = (TextView) findViewById(R.id.tv_demo2);
        tv_demo3 = (TextView) findViewById(R.id.tv_demo3);
        tv_demo.setOnClickListener(this);
        tv_demo2.setOnClickListener(this);
        tv_demo3.setOnClickListener(this);

        //黑色背景的初始化
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);
        darkView = findViewById(R.id.main_darkview);
        darkView.startAnimation(animIn);
        darkView.setVisibility(View.GONE);

    }

    private void initPop() {

        View view;
        ListView firstListView;
        ListView secondListView;
        ListView thirdListView;

        //三个popupWindow的背景
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_filter_down);

        //初始化PopupWindow中的ListView
        view = LayoutInflater.from(this).inflate(R.layout.popup_one_layout, null);
        firstListView = (ListView) view.findViewById(R.id.pop_listview);

        p1 = new PopupDownMenu(this, itemList, WindowManager.LayoutParams.MATCH_PARENT, 375, view, drawable, firstListView);
        p1.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                //背景颜色变回正常
                darkView.startAnimation(animOut);
                darkView.setVisibility(View.GONE);

                //处理数据方式要防止取得的数据是不正常的数据
                if (p1.results[3] != null) {
                    if (p1.results[0] != null) {
                        tv_demo.setText(p1.results[0]);
                    }
                    Toast.makeText(PopupDownMenuActivity.this, p1.results[0], Toast.LENGTH_SHORT).show();
                }
                tv_demo.setSelected(false);
            }
        });

        //初始化PopupWindow中的两个ListView
        view = LayoutInflater.from(this).inflate(R.layout.popup_double_layout, null);
        firstListView = (ListView) view.findViewById(R.id.pop_listview_left);
        secondListView = (ListView) view.findViewById(R.id.pop_listview_right);

        p2 = new PopupDownMenu(this, itemList2, WindowManager.LayoutParams.MATCH_PARENT, 375, view, drawable, firstListView, secondListView);
        p2.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                //背景颜色变回正常
                darkView.startAnimation(animOut);
                darkView.setVisibility(View.GONE);

                //处理数据方式要防止取得的数据是不正常的数据
                if (p2.results[3] != null) {
                    if (p2.results[0] != null) {
                        if (p2.results[1] != null) {
                            tv_demo2.setText(p2.results[1]);
                        } else {
                            tv_demo2.setText(p2.results[0]);
                        }
                    }
                    Toast.makeText(PopupDownMenuActivity.this, p2.results[0] + "+" + p2.results[1], Toast.LENGTH_SHORT).show();
                }
                tv_demo2.setSelected(false);
            }
        });

        //初始化PopupWindow中的三个ListView
        view = LayoutInflater.from(this).inflate(R.layout.popup_triple_layout, null);
        firstListView = (ListView) view.findViewById(R.id.pop_listview_left);
        secondListView = (ListView) view.findViewById(R.id.pop_listview_center);
        thirdListView = (ListView) view.findViewById(R.id.pop_listview_right);

        p3 = new PopupDownMenu(this, itemList3, WindowManager.LayoutParams.MATCH_PARENT, 375, view, drawable, firstListView, secondListView, thirdListView);
        p3.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                //背景颜色变回正常
                darkView.startAnimation(animOut);
                darkView.setVisibility(View.GONE);

                //处理数据方式要防止取得的数据是不正常的数据
                if (p3.results[3] != null) {
                    if (p3.results[0] != null) {
                        if (p3.results[1] != null) {
                            if (p3.results[2] != null) {
                                tv_demo3.setText(p3.results[2]);
                            } else {
                                tv_demo3.setText(p3.results[1]);
                            }
                        } else {
                            tv_demo3.setText(p3.results[0]);
                        }
                    }
                    Toast.makeText(PopupDownMenuActivity.this, p3.results[0] + "+" + p3.results[1] + "+" + p3.results[2], Toast.LENGTH_SHORT).show();
                }
                tv_demo3.setSelected(false);
            }
        });
    }

    @Override
    public void onClick(View v) {

        //背景颜色变暗
        darkView.startAnimation(animIn);
        darkView.setVisibility(View.VISIBLE);

        switch (v.getId()) {

            case R.id.tv_demo:
                //避免另外两个pop窗口还存在的情况
                if (p2.popupWindow.isShowing()) {
                    p2.popupWindow.dismiss();
                }
                if (p3.popupWindow.isShowing()) {
                    p3.popupWindow.dismiss();
                }
                //利用popFlag来实现连续点击同一按钮，点了一次再点外面布局的pop窗弹出效果
                if (!p1.popupWindow.isShowing()) {
                    v.setSelected(true);
                    //创建后立即呈现
                    p1.popupWindow.showAsDropDown(v, 0, 5);
                }
                //实现第二次点击收回的效果
                else {
                    p1.popupWindow.dismiss();
                    //背景颜色变回正常
                    darkView.startAnimation(animOut);
                    darkView.setVisibility(View.GONE);
                }
                break;

            case R.id.tv_demo2:
                //避免另外两个pop窗口还存在的情况
                if (p1.popupWindow.isShowing()) {
                    p1.popupWindow.dismiss();
                }
                if (p3.popupWindow.isShowing()) {
                    p3.popupWindow.dismiss();
                }
                //利用popFlag来实现连续点击同一按钮，点了一次再点外面布局的pop窗弹出效果
                if (!p2.popupWindow.isShowing()) {
                    v.setSelected(true);
                    //创建后立即呈现
                    p2.popupWindow.showAsDropDown(v, 0, 5);
                }
                //实现第二次点击收回的效果
                else {
                    p2.popupWindow.dismiss();
                    //背景颜色变回正常
                    darkView.startAnimation(animOut);
                    darkView.setVisibility(View.GONE);
                }
                break;

            case R.id.tv_demo3:
                //避免另外两个pop窗口还存在的情况
                if (p1.popupWindow.isShowing()) {
                    p1.popupWindow.dismiss();
                }
                if (p2.popupWindow.isShowing()) {
                    p2.popupWindow.dismiss();
                }
                //利用popFlag来实现连续点击同一按钮，点了一次再点外面布局的pop窗弹出效果
                if (!p3.popupWindow.isShowing()) {
                    v.setSelected(true);
                    p3.popupWindow.showAsDropDown(v, 0, 5);
                }
                //实现第二次点击收回的效果
                else {
                    p3.popupWindow.dismiss();
                    //背景颜色变回正常
                    darkView.startAnimation(animOut);
                    darkView.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            //点击按钮以外的布局时要把可能有的PopupWindow清除
            case MotionEvent.ACTION_UP:
                if (p1.popupWindow.isShowing()) {
                    p1.popupWindow.dismiss();
                }
                if (p2.popupWindow.isShowing()) {
                    p2.popupWindow.dismiss();
                }
                if (p3.popupWindow.isShowing()) {
                    p3.popupWindow.dismiss();
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void initData() {

        //初始化数据
        initFirstData(itemList);
        initSecondData(itemList2);
        initThirdData(itemList3);

    }

}
