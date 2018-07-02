package com.example.administrator.myapptextttttttt.utils.glideutils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.myapptextttttttt.R;


/**
 * Created by Administrator on 2018/4/28.
 */

public class GlideUtils {

    /**
     * 加载网络图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadImage(Context mContext, String path,
                                 ImageView imageview) {
        Glide.with(mContext).load(path)
                //.centerCrop()
                /**
                 * 在设置图片到 ImageView 的时候，为了避免图片被挤压失真，
                 * ImageView 本身提供了 ScaleType 属性，这个属性可以控制图片显示时的方式，具体的属性使用还是去搜索吧！
                 * Glide 也提供了两个类似的方法 CenterCrop() 和 FitCenter()，CenterCrop() 方法是将图片按比例缩放到足矣填充 ImageView 的尺寸，
                 * 但是图片可能会显示不完整；而 FitCenter() 则是图片缩放到小于等于 ImageView 的尺寸，这样图片是显示完整了，
                 * 但是 ImageView 就可能不会填满了。
                 */
                .placeholder(R.drawable.img_error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }

    /**
     * 加载带尺寸的图片
     *
     * @param mContext
     * @param path
     * @param Width
     * @param Height
     * @param imageview
     */
    public static void LoadImageWithSize(Context mContext, String path,
                                         int Width, int Height, ImageView imageview) {
        Glide.with(mContext).load(path).override(Width, Height)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }

    /**
     * 加载本地图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadImageWithLocation(Context mContext, Integer path,
                                             ImageView imageview) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageview);
    }

    /**
     * 圆形加载
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadCircleImage(Context mContext, String path,
                                       ImageView imageview) {
        Glide.with(mContext).load(path)
                //.centerCrop()
                .placeholder(R.drawable.img_error)
                .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);

    }

    /**
     * 圆形带边框加载
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadCircleImage(Context mContext, String path, float strokeWidth, int strokeColor,
                                       ImageView imageview) {
        Glide.with(mContext).load(path)
                //.centerCrop()
                .placeholder(R.drawable.img_error)
                .transform(new GlideCircleTransformFrame(mContext, strokeWidth, strokeColor))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);

    }

}
