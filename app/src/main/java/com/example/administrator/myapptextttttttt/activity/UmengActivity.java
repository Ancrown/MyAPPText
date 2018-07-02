package com.example.administrator.myapptextttttttt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/22
 * 描述: 友盟
 */

public class UmengActivity extends BaseActivity implements ActionBarClickListener {

    @BindView(R.id.umeng_tuisong)
    TextView umengTuisong;
    @BindView(R.id.umeng_fenxiang)
    TextView umengFenxiang;
    @BindView(R.id.umeng_fenxiang_wechat)
    TextView umengFenxiangWechat;
    @BindView(R.id.umeng_fenxiang_qq)
    TextView umengFenxiangQq;
    @BindView(R.id.umeng_fenxiang_qq_z)
    TextView umengFenxiangQqZ;


    private UMWeb web;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("友盟", 0, R.drawable.back, null, 0, null, this);
        //分享的内容
        web = new UMWeb("https://www.baidu.com/");
        web.setTitle("This is music title");//标题
        web.setThumb(new UMImage(UmengActivity.this, "https://upload-images.jianshu.io/upload_images/12011034-69e0586e9043b085.jpg"));  //缩略图
        web.setDescription("my description");//描述

    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(UmengActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(UmengActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(UmengActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected int getLayout() {
        return R.layout.aty_umeng;
    }


    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.umeng_tuisong, R.id.umeng_fenxiang, R.id.umeng_fenxiang_wechat, R.id.umeng_fenxiang_qq, R.id.umeng_fenxiang_qq_z})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.umeng_tuisong:
                break;
            case R.id.umeng_fenxiang:
                //分享列表
                new ShareAction(UmengActivity.this).withMedia(web).
                        setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(shareListener).open();
                break;
            case R.id.umeng_fenxiang_wechat:

                break;
            case R.id.umeng_fenxiang_qq:
                new ShareAction(UmengActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(web)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.umeng_fenxiang_qq_z:
                new ShareAction(UmengActivity.this)
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withMedia(web)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }
}
