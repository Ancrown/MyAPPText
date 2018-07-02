package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.utils.StatusBarUtil;
import com.example.administrator.myapptextttttttt.utils.countdown.CountDownTimerSupport;
import com.example.administrator.myapptextttttttt.utils.countdown.OnCountDownTimerListener;



/**
 * @author zzq
 * @date 创建时间：2017/4/28 11:27
 * 描述:欢迎界面
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG             #
 * #                                                   #
 */

public class SplashActivity extends Activity implements View.OnClickListener {
    private boolean aBoolean;

    ImageView mIvSplash;

    TextView mLlCountDown;
    //倒计时
    private CountDownTimerSupport mTimer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        //设置透明
        StatusBarUtil.setTransparent(this);
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);
        mLlCountDown = (TextView) findViewById(R.id.ll_count_down);

        //设置默认图片
        mIvSplash.setImageResource(R.mipmap.ic_default_bg);

        mLlCountDown.setOnClickListener(this);

        mTimer = new CountDownTimerSupport(1000 * 5, 1000);
        mTimer.start();
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!SplashActivity.this.isFinishing()) {
                    mLlCountDown.setText("跳过 " + millisUntilFinished / 1000 + "秒");
                }
            }

            @Override
            public void onFinish() {
                if (!SplashActivity.this.isFinishing()) {
                    mLlCountDown.setText("跳过 0秒");
                    if (!aBoolean) {
                        //操作
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }
            }
        });
    }


    /**
     * 跳转到首页
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_count_down:
                mTimer.stop();
                aBoolean = true;
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

}
