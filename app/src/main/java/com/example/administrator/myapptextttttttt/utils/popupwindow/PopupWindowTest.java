package com.example.administrator.myapptextttttttt.utils.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.activity.camera.CameraActivity;
import com.example.administrator.myapptextttttttt.utils.AppUtils;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.SelectModel;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.intent.PhotoPickerIntent;

import java.util.ArrayList;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/30
 * 描述:
 */

public class PopupWindowTest extends PopupWindow {

    private View mView;
    public TextView btnSaveProject, btnAbandonProject, btnCancelProject;

    private Activity context;
    private int selectModel;
    private ArrayList list;

    public PopupWindowTest(final Activity context, final int selectModel) {
        super(context);
        this.context = context;
        this.selectModel = selectModel;
        init();
    }

    public void init() {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_test, null);

        btnSaveProject = (TextView) mView.findViewById(R.id.pop_photograph);
        btnAbandonProject = (TextView) mView.findViewById(R.id.pop_album);
        btnCancelProject = (TextView) mView.findViewById(R.id.pop_cancel);


        btnSaveProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraActivity.startForResult(context, CameraActivity.MY_CAMER_REQUSTCODE, selectModel);
            }
        });
        btnAbandonProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectModel <= 0) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(context);
                    intent.setSelectModel(selectModel == 0 ? SelectModel.SINGLE : SelectModel.SHEAR);
//                intent.setImageConfig(null);
                    context.startActivityForResult(intent, 11);
                } else {
                    Log.e("selectModel:", selectModel + "");
                    PhotoPickerIntent intent = new PhotoPickerIntent(context);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setMaxTotal(selectModel); // 最多选择照片数量，默认为9
                    //  intent.setSelectedPaths(list); // 已选中的照片地址， 用于回显选中状态
//                ImageConfig config = new ImageConfig();
//                config.minHeight = 400;
//                config.minWidth = 400;
//                config.mimeType = new String[]{"image/jpeg", "image/png"};
//                config.minSize = 1 * 1024 * 1024; // 1Mb
//                intent.setImageConfig(config);
                    context.startActivityForResult(intent, 11);
                }
            }
        });
        // 设置按钮监听
        btnCancelProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.pop_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(AppUtils.getColor(R.color.translate));
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
    }
}
