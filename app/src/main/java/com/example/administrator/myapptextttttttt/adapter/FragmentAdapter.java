package com.example.administrator.myapptextttttttt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fList;                         //fragment列表

    private List<String> tList;                              //tab名的列表

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, List<Fragment> fList, List<String> tList) {
        super(fm);
        this.fList = fList;
        this.tList = tList;
    }

    public List<Fragment> getfList() {
        return fList;
    }

    public void setfList(List<Fragment> fList) {
        this.fList = fList;
    }

    public List<String> gettList() {
        return tList;
    }

    public void settList(List<String> tList) {
        this.tList = tList;
    }

    @Override

    public Fragment getItem(int i) {

        return fList.get(i);

    }


    @Override

    public int getCount() {

        return fList.size();

    }


    /**
     * 此方法是给tablayout中的tab赋值的，就是显示名称
     *
     * @param position
     * @return
     */

    @Override

    public CharSequence getPageTitle(int position) {
        return tList.get(position % tList.size());
    }

}
