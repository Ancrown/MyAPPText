package com.example.administrator.myapptextttttttt.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.saoyisao.Constant;
import com.example.administrator.myapptextttttttt.saoyisao.ScannerActivity;
import com.example.administrator.myapptextttttttt.utils.PermissionManager;
import com.example.administrator.myapptextttttttt.utils.SharedPreferencesUtils;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.zbarcode.CaptureActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.zxing.BarcodeFormat.QR_CODE;

/**
 * 创建人: Administrator
 * 创建时间: 2018/6/6
 * 描述:
 */

public class SaoYiSaoActivity extends BaseActivity {
    private static final int RESULT_REQUEST_CODE = 100;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button1)
    Button button1;

    boolean b;

    @Override
    protected void initData() {
        SharedPreferencesUtils.setParam(SaoYiSaoActivity.this, "SAOYISAO", "我我我我我我");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("扫一扫", 0, 0, null, 0, null, null);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_sao_yi_sao;
    }


    @OnClick({R.id.button, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (!b) {
                    ToolUtils.showToast(SaoYiSaoActivity.this, SharedPreferencesUtils.getParam(SaoYiSaoActivity.this, "SAOYISAO", "") + "");
                    SharedPreferencesUtils.clear(SaoYiSaoActivity.this, "SAOYISAO");
                } else {
                    ToolUtils.showToast(SaoYiSaoActivity.this, SharedPreferencesUtils.getParam(SaoYiSaoActivity.this, "SAOYISAO", "") + "");
                    SharedPreferencesUtils.setParam(SaoYiSaoActivity.this, "SAOYISAO", "我我我我我我");
                }
                b = !b;

                if (PermissionManager.permissionApplication(SaoYiSaoActivity.this, PermissionManager.Camera(), PermissionManager.PERMISSION)) {
                    goScanner();
                }
                break;
            case R.id.button1:

                if (PermissionManager.permissionApplication(SaoYiSaoActivity.this, PermissionManager.Camera(), PermissionManager.PERMISSION1)) {
                    Intent intent2 = new Intent(SaoYiSaoActivity.this, CaptureActivity.class);
                    startActivityForResult(intent2, CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA);
                }


                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0) return;
        int count = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                count++;
            }
        }

        if (count == grantResults.length) {
            if (requestCode == PermissionManager.PERMISSION) {
                goScanner();
            } else if (requestCode == PermissionManager.PERMISSION1) {
                Intent intent2 = new Intent(SaoYiSaoActivity.this, CaptureActivity.class);
                startActivityForResult(intent2, CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            if (requestCode == PermissionManager.PERMISSION) {
                PermissionManager.showDialog(SaoYiSaoActivity.this,
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'相机)");
            } else if (requestCode == PermissionManager.PERMISSION1) {
                PermissionManager.showDialog(SaoYiSaoActivity.this,
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'相机)");
            }

        }
    }

    private void goScanner() {
        Intent intent = new Intent(this, ScannerActivity.class);
        //  这里可以用intent传递一些参数，比如扫码聚焦框尺寸大小，支持的扫码类型。
        //设置扫码框的宽
        intent.putExtra(Constant.EXTRA_SCANNER_FRAME_WIDTH, 400);
        //设置扫码框的高
        intent.putExtra(Constant.EXTRA_SCANNER_FRAME_HEIGHT, 400);
        //设置扫码框距顶部的位置
        intent.putExtra(Constant.EXTRA_SCANNER_FRAME_TOP_PADDING, 100);
        //设置是否启用从相册获取二维码。
        intent.putExtra(Constant.EXTRA_IS_ENABLE_SCAN_FROM_PIC, true);
//        Bundle bundle = new Bundle();
//        //设置支持的扫码类型
//        bundle.putSerializable(Constant.EXTRA_SCAN_CODE_TYPE, mHashMap);
//        intent.putExtras(bundle);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_OK) {
//            if (requestCode == RESULT_REQUEST_CODE) {
//                if (data == null) return;
//
//                String type = data.getStringExtra(Constant.EXTRA_RESULT_CODE_TYPE);
//
//                String content = data.getStringExtra(Constant.EXTRA_RESULT_CONTENT);
//
//                Toast.makeText(SaoYiSaoActivity.this, "codeType:" + type
//
//                        + "-----content:" + content, Toast.LENGTH_SHORT).show();
//
//
//            }
//        }
        if (requestCode == CaptureActivity.MY_PERMISSIONS_REQUEST_CAMERA) {
            if (null == data) return;
            Bundle b = data.getExtras();
            String result = b.getString(CaptureActivity.EXTRA_STRING);
            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
        }
    }
}
