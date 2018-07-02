package com.example.administrator.myapptextttttttt.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import com.example.administrator.myapptextttttttt.base.BaseApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * APP工具类
 * Created by zzq on 2016/12/17.
 */
public class AppUtils {
    private static BaseApplication application;
    private static Context mContext;

    static {
        application = BaseApplication.getInstance();
        mContext = BaseApplication.appContext;
    }


    public static Context getAppContext() {
        return mContext;
    }

    public static AssetManager getAssets() {
        return mContext.getAssets();
    }

    public static float getDimension(int id) {
        return getResource().getDimension(id);
    }

    public static Resources getResource() {
        return mContext.getResources();
    }

    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(int resId) {
        return mContext.getResources().getDrawable(resId);
    }

    @SuppressWarnings("deprecation")
    public static int getColor(int resId) {
        return mContext.getResources().getColor(resId);
    }

    public static String getString(@StringRes int resId) {
        return mContext.getResources().getString(resId);
    }

    public static String[] getStringArray(@ArrayRes int resId) {
        return mContext.getResources().getStringArray(resId);
    }


}
