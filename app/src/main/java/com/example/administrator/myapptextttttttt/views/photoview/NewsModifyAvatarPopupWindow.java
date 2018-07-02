package com.example.administrator.myapptextttttttt.views.photoview;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.activity.CameraTextActivity;
import com.example.administrator.myapptextttttttt.activity.camera.CameraActivity;
import com.example.administrator.myapptextttttttt.utils.AppUtils;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.SelectModel;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.intent.PhotoPickerIntent;

import java.io.File;
import java.util.ArrayList;


/**
 * 作者 ：刘晓伟
 * 工程名 ：FMC
 * 包名 ：www.ioi.com.fmc.view.photoview
 * 描述 ：Mark
 * 创建时间 ：2017/5/15
 */
public class NewsModifyAvatarPopupWindow extends PopupWindow implements View.OnClickListener {


    private Activity activity;
    private Fragment fragment;
    //判断是activity(1)还是fragment(2)启动相机
    private int select;

    private int selectModel; //多张还是单张

    private ArrayList<String> imagePaths;

    public NewsModifyAvatarPopupWindow(Activity activity, int select, int selectModel) {
        this.activity = activity;
        this.select = select;
        this.selectModel = selectModel;
        showPopup();
    }

    public int getSelectModel() {
        return selectModel;
    }

    public void setSelectModel(int selectModel) {
        this.selectModel = selectModel;
    }

    public NewsModifyAvatarPopupWindow(Activity activity, int select, int selectModel, ArrayList<String> imagePaths) {
        this.activity = activity;
        this.select = select;
        this.selectModel = selectModel;
        this.imagePaths = imagePaths;
        showPopup();
    }

    public NewsModifyAvatarPopupWindow(Activity activity, Fragment fragment, int select) {
        this.activity = activity;
        this.fragment = fragment;
        this.select = select;
        showPopup();
    }

    private void showPopup() {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_modifypopupwindow, null);

        TextView photoGraph = (TextView) view.findViewById(R.id.activity_modifypopupwindow_photograph);
        TextView albumStyle = (TextView) view.findViewById(R.id.activity_modifypopupwindow_albumstyle);
        TextView cancelStyle = (TextView) view.findViewById(R.id.activity_modifypopupwindow_cancelstyle);


        setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);

        //从相册选择监听事件
        photoGraph.setOnClickListener(this);

        //取消监听事件
        cancelStyle.setOnClickListener(this);

        //拍照监听
        albumStyle.setOnClickListener(this);

        //设置PopupWindow的View
        this.setContentView(view);
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
        this.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 1.0f;
        activity.getWindow().setAttributes(params);
        this.dismiss();
        switch (v.getId()) {
            case R.id.activity_modifypopupwindow_photograph:
                //   choseHeadImageFromCameraCapture();
//                Intent intent1 = new Intent(activity, CameraActivity.class);
//                activity.startActivityForResult(intent1, 11);
                CameraActivity.startForResult(activity, CameraActivity.MY_CAMER_REQUSTCODE,selectModel);
                break;
            case R.id.activity_modifypopupwindow_cancelstyle:

                break;
            case R.id.activity_modifypopupwindow_albumstyle:
                if (selectModel <= 0) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(activity);
                    intent.setSelectModel(selectModel==0?SelectModel.SINGLE:SelectModel.SHEAR);
//                intent.setImageConfig(null);
                    activity.startActivityForResult(intent, 11);
                } else {
                    Log.e("selectModel:", selectModel + "");
                    PhotoPickerIntent intent = new PhotoPickerIntent(activity);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setMaxTotal(selectModel); // 最多选择照片数量，默认为9
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
//                ImageConfig config = new ImageConfig();
//                config.minHeight = 400;
//                config.minWidth = 400;
//                config.mimeType = new String[]{"image/jpeg", "image/png"};
//                config.minSize = 1 * 1024 * 1024; // 1Mb
//                intent.setImageConfig(config);
                    activity.startActivityForResult(intent, 11);
                }

                break;
        }
    }


}

