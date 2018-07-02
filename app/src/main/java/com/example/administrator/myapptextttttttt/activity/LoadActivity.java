package com.example.administrator.myapptextttttttt.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.MovieSubject;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.dialog.TipLoadDialog;
import com.example.administrator.myapptextttttttt.utils.AddressRequestString;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.utils.okhttp.OkHttpUtil;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/31
 * 描述:
 */

public class LoadActivity extends BaseActivity {
    @BindView(R.id.load_text)
    TextView loadText;


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        ToolUtils.fitsSystemWindows(LoadActivity.this);

    }

    @Override
    protected int getLayout() {
        return R.layout.aty_load;
    }

    private int page = 1;


    @OnClick(R.id.load_text)
    public void onViewClicked() {

        Map map = new HashMap<>();
        map.put("start", 0);
        map.put("count", 5);
        OkHttpUtil.getInstance(this).doPost(AddressRequestString.BASE_URL + "top250", new OkHttpUtil.ResultCallback<MovieSubject>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(MovieSubject response) {
                loadText.setText(response.getTitle());
            }
        }, map, "登陆中");
    }


}
