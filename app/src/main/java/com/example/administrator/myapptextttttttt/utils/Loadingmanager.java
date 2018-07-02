package com.example.administrator.myapptextttttttt.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.views.LoadingView;


/**
 * Author  ：龙 on 2017/6/21 08:49
 * Function:
 */
public class Loadingmanager {
//    private ProgressDialog dialog;
//
//    public Loadingmanager(Context context) {
//        dialog = new ProgressDialog(context);
//    }
//
//    public void show(String message) {
//        dialog.setMessage(message);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//    }
//
//    public void dismiss() {
//        dialog.dismiss();
//    }
//
//    public boolean isShow() {
//        return dialog.isShowing();
//    }

    private Context mContext;
    private Dialog mDialog;
    private LoadingView mLoadingView;
    private View mDialogContentView;


    public Loadingmanager(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        mDialog = new Dialog(mContext, R.style.custom_dialog);
        mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog, null);
        mLoadingView = (LoadingView) mDialogContentView.findViewById(R.id.loadView);
        mDialog.setContentView(mDialogContentView);
        mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
    }

    public void setBackground(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) mDialogContentView.getBackground();
        gradientDrawable.setColor(color);
    }

    public void setLoadingText(CharSequence charSequence) {
        mLoadingView.setLoadingText(charSequence);
    }

    public void show(String text) {
        setLoadingText(text);
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public boolean isShow() {
        return mDialog.isShowing();
    }
}
