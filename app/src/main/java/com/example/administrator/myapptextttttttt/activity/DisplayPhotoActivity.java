package com.example.administrator.myapptextttttttt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.adapter.DisplayPhotoAdapter;
import com.example.administrator.myapptextttttttt.adapter.RecyclerviewAdapter;
import com.example.administrator.myapptextttttttt.adapter.base.CommonHolder;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.PhotoPreviewActivity;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.intent.PhotoPreviewIntent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/23
 * 描述:
 */

public class DisplayPhotoActivity extends BaseActivity {

    @BindView(R.id.dispaly_photo_recy)
    RecyclerView recyclerView;

    private DisplayPhotoAdapter adapter;
    private List<String> list;

    @Override

    protected void initData() {

    }

    @Override
    protected void initListener() {
        adapter.setOnClickItem(new RecyclerviewAdapter.OnClickItem() {
            @Override
            public void onItem(String t, int i) {
                PhotoPreviewIntent intent = new PhotoPreviewIntent(DisplayPhotoActivity.this);
                intent.setCurrentItem(i);
                intent.setPhotoPaths((ArrayList<String>) list);
                startActivityForResult(intent, PhotoPreviewActivity.REQUEST_PREVIEW);
            }
        });
    }

    @Override
    protected void initView() {
        ToolUtils.fitsSystemWindows(this);
        list = new ArrayList<>();
        list.add("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925682&di=d12775547359b0c44d837f51f02e6518&imgtype=0&src=http%3A%2F%2Fu5.qiyipic.com%2Fimage%2F20170815%2F9c%2F5d%2Fuv_3066296689_m_601_480_270.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=51e62c9a9f63768cbb089f90668a17a2&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201601%2F06%2F20160106173647_yiXdf.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=f92d19cc97cb850b93d622c248a9327a&imgtype=0&src=http%3A%2F%2Fi2.qhmsg.com%2Ft01e909ef83b1591352.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525344925648&di=ae1442d7c0da3969ab98dfcdf8bf2a31&imgtype=0&src=http%3A%2F%2Fp2.qhmsg.com%2Ft01fcf832a92d4bd986.jpg");

        adapter = new DisplayPhotoAdapter();
        GridLayoutManager mgr = new GridLayoutManager(this, 4);
        LinearLayoutManager lmr = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(lmr);
        //设置Adapter
        recyclerView.setAdapter(adapter);
        adapter.setDataList(list);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_display_photo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPreviewActivity.REQUEST_PREVIEW) {
            list = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
            adapter.setDataList(list);
        }
    }
}
