package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.interf.ActionBarClickListener;
import com.example.administrator.myapptextttttttt.utils.HtmlFormat;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.ScrollViewExt;
import com.example.administrator.myapptextttttttt.views.TestWebView;

/**
 * Created by Administrator on 2018/5/11.
 */

public class WebTextImgActivity extends BaseActivity implements ActionBarClickListener {
    private CountDownTimer timer;
    private boolean out;
    private WebView webView;
    private ScrollViewExt scrollViewExt;

    private String text = "<p style=\"text-indent:2em;\"> <strong>央广网北京4月20日消息 2018年4月17日-19日</strong>，由<span style=\"color:#E53333;\">中国石油学会、中国石油、</span>中国石化、中国海油、中国中化、延长石油联合主办，以“大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化”为主题的“2018中国石油石化企业信息技术交流大会暨展示会”在北京成功召开。会议围绕进一步推进我国石油石化企业两化深度融合，全面提升企业数字化、网络化、智能化水平和网络安全能力展开交流研讨。 </p> <p style=\"text-indent:2em;\"> 近年来，我国石油石化企业积极顺应信息技术发展趋势，围绕主营业务提质增效、转型升级，大力开展信息化建设和应用，以信息化驱动现代化，加快推进智慧油田、智慧工厂、智慧管道、智慧加油站建设，努力开创信息化推动企业数字化转型、智能化发展的新时代。 </p> <p> <img src=\"http://www.rifengsy.com/UploadFiles/2016012313132086730.jpg\" alt=\"\" /> </p><video width=\"100%\"  controls=\"controls\"> <source src=\" \" type=\"video/ogg\"> <source src=\"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/118064948_28fd2f3b834a56b5c1c14a471f77e75b_0.mp4\" type=\"video/mp4\"> Your browser does not support the video tag. </video> <p style=\"text-indent:2em;\"> 本次大会以大力发展石油石化工业互联网，全面提升网络安全，有效促进企业数字化智能化为主题，深度探讨搭建共享服务平台、大数据技术应用、物联网、互联网+行动计划、移动应用、网络安全解决方案、信息系统应用等技术，广泛交流推广、集中展示各单位在工业互联、共享智能、网络安全等方面取得的新成果、新进展，为“十三五”信息化规划与目标任务的全面实现提供了保障。旨在通过交流研讨，大力推进能源行业工业互联网的建设应用，促进传统产业转型升级；有效促进行业间、企业间的广泛合作，共同推动石油石化企业依靠信息技术实现创新发展，不断提升企业现代化管理水平、可持续发展能力。 </p> <p style=\"text-indent:2em;\"> 会议期间，石化行业专家围绕智慧油田、智慧工厂、智慧管道、智慧加油站、云计算与大数据、网络安全等主题进行了深入研讨，内容丰富、观点新颖，受到了与会各方的广泛好评。同时，会议期间还举办了新技术、新成果、新设备应用推广与展示，为一百多家信息技术服务商提供了展台，充分展现了石油石化行业创新发展的良好形象。 </p>";


    //提示框
    private AlertDialog.Builder builder;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        scrollViewExt.setScrollViewListener(new ScrollViewExt.IScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                Log.e("eeeeeee", "到底部了");
            }

            @Override
            public void onScrolledToTop() {
                Log.e("eeeeeee", "到头部了");
            }
        });
    }

    @Override
    protected void initView() {
        getTitleView().setData("HTIL", 0, R.drawable.back, null, 0, null, this);
        webView = (WebView) findViewById(R.id.webview);
        scrollViewExt = (ScrollViewExt) findViewById(R.id.scrollviewext);
        webView.loadDataWithBaseURL(null, HtmlFormat.getNewContent(text), "text/html", "utf-8", null);
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!WebTextImgActivity.this.isFinishing()) {
                    Log.e("eeeee", " 倒计时：" + millisUntilFinished / 1000);
                }


            }

            @Override
            public void onFinish() {
                //停止倒计时 
                cancel();
                out = true;
                Log.e("eeeee", " 倒计时：结束！！！");

            }
        };
        timer.start();
        builder = new AlertDialog.Builder(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_web_text_img;
    }


    @Override
    public void onLeftClick() {
        onBack();
    }

    @Override
    public void onRightClick() {

    }


    @Override
    public void onBack() {
        if (!out) {
            showDialog();
            return;
        }
        super.onBack();
    }

    @Override
    public void onBackPressed() {
        if (!out) {
            showDialog();
            return;
        }
        super.onBackPressed();
    }

    public void showDialog() {
        builder.setTitle("提示").setMessage("您当前阅读的时间还不够60s")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.cancel();
                        finish();

                    }
                }).setNegativeButton("取消", null)
                .show();
    }
}
