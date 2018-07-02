package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.utils.CodeUtils;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/5.
 */

public class FontRollingActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_text)
    TextView imgText;

    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {


        List<String> info = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            info.add("这里是热门头条" + i);
        }
        marqueeView.startWithList(info);

        //点击事件

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), String.valueOf(marqueeView.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();

                img.setImageBitmap(CodeUtils.getInstance().createBitmap());
                imgText.setText(CodeUtils.getInstance().getCode());
            }
        });
        ToolUtils.fitsSystemWindows(this);
        //设置验证码个数
        CodeUtils.DEFAULT_CODE_LENGTH = 4;
        img.setImageBitmap(CodeUtils.getInstance().createBitmap());
        imgText.setText(CodeUtils.getInstance().getCode());

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_font_rolling;
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }
}
