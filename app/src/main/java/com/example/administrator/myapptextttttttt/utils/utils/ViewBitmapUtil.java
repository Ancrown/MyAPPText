package com.example.administrator.myapptextttttttt.utils.utils;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;


/**
 * Created by duy01 on 2016/3/16.
 */
public class ViewBitmapUtil {


    private static int width = 0;
    private static int height = 0;

//    public static Bitmap bitmap = null;
//
//    private static Bitmap cacheBitmap = null;


    /**
     * bitmap 释放缓存 ，感觉不太好使
     */
//    public static void recycleBitmap(){
//        if(bitmap != null){
//            bitmap.recycle() ;  //回收图片所占的内存
//            System.gc();  //提醒系统及时回收
//            bitmap = null;
//        }
//
//        if(cacheBitmap != null){
//            cacheBitmap.recycle() ;  //回收图片所占的内存
//            System.gc();  //提醒系统及时回收
//            cacheBitmap = null;
//        }
//
//    }

    /**
     * 把View绘制到Bitmap上
     * @param comBitmap 需要绘制的View
     * @return 返回Bitmap对象
     * add by csj 13-11-6
     */
    public static Drawable getViewBitmap(final View comBitmap,int width,int height) {


        Bitmap bitmap = null;

        Bitmap cacheBitmap = null;

        Drawable drawable = null;
        if (comBitmap != null) {
            comBitmap.clearFocus();
            comBitmap.setPressed(false);

            boolean willNotCache = comBitmap.willNotCacheDrawing();
            comBitmap.setWillNotCacheDrawing(false);

            // Reset the drawing cache background color to fully transparent
            // for the duration of this operation
            int color = comBitmap.getDrawingCacheBackgroundColor();
            comBitmap.setDrawingCacheBackgroundColor(0);
            float alpha = comBitmap.getAlpha();
            comBitmap.setAlpha(1.0f);

            if (color != 0) {
                comBitmap.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            comBitmap.measure(widthSpec, heightSpec);
            comBitmap.layout(0, 0, width, height);

            comBitmap.buildDrawingCache();
            cacheBitmap = comBitmap.getDrawingCache();
            if (cacheBitmap == null) {
//                Log.e("view.ProcessImageToBlur", "failed getViewBitmap(" + comBitmap + ")",
//                        new RuntimeException());
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            // Restore the view
            comBitmap.setAlpha(alpha);
            comBitmap.destroyDrawingCache();
            comBitmap.setWillNotCacheDrawing(willNotCache);
            comBitmap.setDrawingCacheBackgroundColor(color);
        }

        drawable = new BitmapDrawable(bitmap);
        return drawable;
    }

    /**
     * 把View绘制到Bitmap上
     * @return 返回Bitmap对象
     * add by csj 13-11-6
     */
    public static Drawable getViewBitmap(View v) {


        Bitmap bitmap = null;

        Bitmap cacheBitmap = null;

        Drawable drawable = null;

        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e("Folder", "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }

        try {
            // 实例化Bitmap
            bitmap = Bitmap.createBitmap(cacheBitmap);
        } catch (OutOfMemoryError e) {
            //

            bitmap = Bitmap.createBitmap(cacheBitmap);
        }

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        drawable = new BitmapDrawable(bitmap);

        return drawable;
    }


    public static Drawable shot(View view,int width,int height) {

        Drawable drawable = null;
        //View是你需要截图的View
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        // 获取状态栏高度 /
        Rect frame = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;
//        Log.i("TAG", "" + statusBarHeight);
        // 获取屏幕长和高
        // 去掉标题栏
//        Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap b = Bitmap.createBitmap(b1);
        view.destroyDrawingCache();
        drawable = new BitmapDrawable(b);
        return drawable;
    }

}
