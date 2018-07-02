package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.FragmentAdapter;
import com.example.administrator.myapptextttttttt.fragment.TabLayoutOneFragment;
import com.example.administrator.myapptextttttttt.fragment.TabLayoutThreeFragment;
import com.example.administrator.myapptextttttttt.fragment.TabLayoutTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private List<Fragment> fList;
    private List<String> tList;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_tab);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        initViewPager();
    }

    public void initViewPager() {

        fList = new ArrayList<>();
        // 装填
        fList.add(new TabLayoutOneFragment());
        fList.add(new TabLayoutThreeFragment());
        fList.add(new TabLayoutTwoFragment());

        tList = new ArrayList<>();
        tList.add("党建要闻");
        tList.add("不忘初心");
        tList.add("项目为王");

        // 给ViewPager设置适配器
        adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setfList(fList);
        adapter.settList(tList);
        mViewPager.setAdapter(adapter);

        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
