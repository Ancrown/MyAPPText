package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.BroadcastView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class BroadcastNewActivity extends Activity {
    private BroadcastView bView;
    private List<String> imageResIds;
    private List<String> contentDescs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_broadcast_new);
        ToolUtils.fitsSystemWindows(this);

        bView = (BroadcastView) findViewById(R.id.bro);
        imageResIds = new ArrayList<>();
        imageResIds.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925682&di=d12775547359b0c44d837f51f02e6518&imgtype=0&src=http%3A%2F%2Fu5.qiyipic.com%2Fimage%2F20170815%2F9c%2F5d%2Fuv_3066296689_m_601_480_270.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=51e62c9a9f63768cbb089f90668a17a2&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201601%2F06%2F20160106173647_yiXdf.jpeg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=f92d19cc97cb850b93d622c248a9327a&imgtype=0&src=http%3A%2F%2Fi2.qhmsg.com%2Ft01e909ef83b1591352.jpg");
        imageResIds.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=ae1442d7c0da3969ab98dfcdf8bf2a31&imgtype=0&src=http%3A%2F%2Fp2.qhmsg.com%2Ft01fcf832a92d4bd986.jpg");

        contentDescs = new ArrayList<>();
        contentDescs.add("巩俐不低俗，我就不能低俗");
        contentDescs.add("扑树又回来啦！再唱经典老歌引万人大合唱");
        contentDescs.add("揭秘北京电影如何升级");
        contentDescs.add("乐视网TV版大派送");
        contentDescs.add("热血屌丝的反杀");

        bView.setData(imageResIds, contentDescs);

        bView.setOnImgClick(new BroadcastView.OnImgClick() {
            @Override
            public void onItme(String id) {
                Log.e("eeeee", "position:" + id);
            }
        });
    }
}
