package com.example.administrator.myapptextttttttt.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.activity.camera.CameraActivity;
import com.example.administrator.myapptextttttttt.utils.glideutils.GlideUtils;
import com.example.administrator.myapptextttttttt.utils.PermissionManager;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.PhotoPickerActivity;

import java.io.File;
import java.io.FileNotFoundException;

public class CameraTextActivity extends AppCompatActivity {
    private ImageView picture;
    private Button start;
    private Button start1;
    private Button start2;
    private final int IMG_PERMISSION1 = 1000; //图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_camera);

        start = (Button) findViewById(R.id.btn_start);
        start1 = (Button) findViewById(R.id.btn_start1);
        start2=(Button) findViewById(R.id.btn_start2);

        picture = (ImageView) findViewById(R.id.iv_picture);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionManager.permissionApplication(CameraTextActivity.this, PermissionManager.ImgPermission(), IMG_PERMISSION1)) {
                    ToolUtils.getNewsPopupWindow(picture, CameraTextActivity.this, -1);
                }

            }
        });
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionManager.permissionApplication(CameraTextActivity.this, PermissionManager.ImgPermission(), IMG_PERMISSION1)) {
                    ToolUtils.getNewsPopupWindow(picture, CameraTextActivity.this, 0);
                }
            }
        });
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionManager.permissionApplication(CameraTextActivity.this, PermissionManager.ImgPermission(), IMG_PERMISSION1)) {
                    ToolUtils.getNewsPopupWindow(picture, CameraTextActivity.this, 9);
                }
            }
        });

    }

    /**
     * 回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ssssssssssss", requestCode + "  +  " + resultCode);

        if (requestCode == 11) {
            if (resultCode == RESULT_OK) {
                GlideUtils.LoadImage(this, data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0), picture);
            }
        }

    }
}
