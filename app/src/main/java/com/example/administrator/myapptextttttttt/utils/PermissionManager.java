package com.example.administrator.myapptextttttttt.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 ：刘晓伟
 * 工程名 ：dis_
 * 包名 ：www.ioi.com.fmc.util
 * 描述 ：Mark
 * 创建时间 ：2016/12/30
 */
public class PermissionManager {

    public static int PERMISSION = 1000;
    public static int PERMISSION1 = 1001;
    //授权
    public static boolean permissionApplication(Activity activity, String[] permissions, int requestCode) {
//        String[] permissions = null;
//        switch (type){
//            case 0:
//                 permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
//                break;
//
//        }
        List<String> mPermissionList = new ArrayList<>();
        //    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        //   String[] permissionsString = new String[]{"储存空间", "相机"};
        if (Build.VERSION.SDK_INT >= 23) {
            /**
             * 判断哪些权限未授予
             */
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);
                    Log.e("permission1  未申请", permissions[i]);
                }
            }
            Log.e("permission1  未申请个数", mPermissionList.size() + "");
            String[] permissionsNo = new String[mPermissionList.size()];
            for (int i = 0; i < mPermissionList.size(); i++) {
                permissionsNo[i] = mPermissionList.get(i);
            }
            if (!mPermissionList.isEmpty()) {
                ActivityCompat.requestPermissions(activity, permissionsNo, requestCode);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void showDialog(final Context context, String msgInfo) {
        new AlertDialog.Builder(context)
                .setTitle("使用警告")
                .setMessage(msgInfo)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //前往应用详情界面
                        try {
                            Uri packUri = Uri.parse("package:" + context.getPackageName());
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packUri);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(context, "跳转失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                }).create().show();
    }

    //全部
    public static String[] AllPermission() {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};
    }

    //关于图片的权限
    public static String[] ImgPermission() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    }

    //存储权限
    public static String[] Camera() {
        return new String[]{Manifest.permission.CAMERA};
    }

    //存储权限
    public static String[] Write() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    //关于定位
    public static String[] LocationPermission() {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
    }

    //关于麦克风
    public static String[] AudioPermission() {
        return new String[]{Manifest.permission.RECORD_AUDIO};
    }

    //聊天权限
    public static String[] ChatPermission() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    }

}
