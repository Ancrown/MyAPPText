package com.example.administrator.myapptextttttttt.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.dialog.TipLoadDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/30
 * 描述:
 */

public class DialogActivity extends BaseActivity {
    @BindView(R.id.btn_dialog1)
    Button btnDialog1;
    @BindView(R.id.btn_dialog2)
    Button btnDialog2;
    @BindView(R.id.btn_dialog3)
    Button btnDialog3;
    @BindView(R.id.btn_dialog4)
    Button btnDialog4;
    @BindView(R.id.btn_dialog5)
    Button btnDialog5;

    public static final String LOADING_玩命 = "玩命加载中...";
    private final String sucTip = "发送成功";
    private final String failTip = "发送失败";
    private final String infoTip = "字数太多就分段显示，保证textview的宽度";

    public TipLoadDialog tipLoadDialog;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initView() {
        tipLoadDialog = new TipLoadDialog(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_dialog;
    }


    @OnClick({R.id.btn_dialog1, R.id.btn_dialog2, R.id.btn_dialog3, R.id.btn_dialog4, R.id.btn_dialog5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog1:
                //设置另一种loading文字动画,注意不要加后缀...
                tipLoadDialog.setNoShadowTheme()
                        .setMsgAndType("加载中", TipLoadDialog.ICON_TYPE_LOADING2)
                        .show();
                //   new LooperThread().start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tipLoadDialog.dismiss();
                    }
                }, 3000);

                break;
            case R.id.btn_dialog2:

                break;
            case R.id.btn_dialog3:
                break;
            case R.id.btn_dialog4:
                break;
            case R.id.btn_dialog5:
                break;
        }
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {

        }
    }

    class LooperThread extends Thread {

        public void run() {
            try {
                sleep(3000);
                tipLoadDialog.dismiss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
