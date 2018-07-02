package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.LabelAdapter;
import com.example.administrator.myapptextttttttt.views.FlowLayout;
import com.example.administrator.myapptextttttttt.views.StarBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class PersonalDetailsActivity extends Activity {
    private TextView name; //名字
    private StarBar level; //星星
    private TextView follow; //关注
    private TextView address; //地址
    private TextView love; //爱心
    // private TextView note; //
    private FlowLayout notes; //技能标签
    private FlowLayout impressions; //印象标签
    private TextView activities;  //活动次数
    private TextView help; //帮助次数
    private EditText edit; //问题
    private ImageView expression; //表情
    private TextView send; //发送



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_personal_details);
        initView();
        level.setStarMark(5);

        initdata();
    }

    private void initdata() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = 5;
        lp.topMargin = 5;

        notes.removeAllViews();
        for (int i = 0; i < 5; i++) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(lp);
            tv.setText("电脑高手");
            tv.setTextSize(10);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setBackgroundColor(Color.parseColor("#4d7ecb"));
            tv.setPadding(7, 5, 7, 5);
            notes.addView(tv);
        }
        impressions.removeAllViews();
        for (int i = 0; i < 5; i++) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(lp);
            tv.setText("安卓高手");
            tv.setTextSize(10);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setBackgroundResource(R.drawable.semicircle_blue);
            tv.setPadding(7, 5, 7, 5);
            impressions.addView(tv);
        }

    }

    private void initView() {
        name = (TextView) findViewById(R.id.personal_details_name);
        level = (StarBar) findViewById(R.id.personal_details_level);
        follow = (TextView) findViewById(R.id.personal_details_follow);
        address = (TextView) findViewById(R.id.personal_details_address);
        love = (TextView) findViewById(R.id.personal_details_love);
        //   note = (TextView) findViewById(R.id.personal_details_note);
        notes = (FlowLayout) findViewById(R.id.personal_details_notes);
        impressions = (FlowLayout) findViewById(R.id.personal_details_impressions);
        activities = (TextView) findViewById(R.id.personal_details_activities);
        help = (TextView) findViewById(R.id.personal_details_help);
        edit = (EditText) findViewById(R.id.personal_details_edit);
        expression = (ImageView) findViewById(R.id.personal_details_expression);
        send = (TextView) findViewById(R.id.personal_details_send);
    }

    private void submit() {
        // validate
        String edit1 = edit.getText().toString().trim();
        if (TextUtils.isEmpty(edit1)) {
            Toast.makeText(this, "写下你的问题", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
    }


}
