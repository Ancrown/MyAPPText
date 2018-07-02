package com.example.administrator.myapptextttttttt.views.photoview.photopicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;

import java.io.IOException;
import java.util.ArrayList;



/**
 * 作者 ：刘晓伟
 * 工程名 ：LIUXIAOWEI
 * 包名 ：com.mc.liuxiaowei.addImg
 * 描述 ：Mark
 * 创建时间 ：2017/11/2
 */
public class CameraActivity extends Activity {

    // 结果数据
    private ArrayList<String> resultList = new ArrayList<>();

    private ImageCaptureManager captureManager; // 相机拍照处理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        captureManager = new ImageCaptureManager(this);

        /**
         * 选择相机
         */

        try {
            Intent intent = captureManager.dispatchTakePictureIntent();
            startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
        } catch (IOException e) {
            Toast.makeText(CameraActivity.this, R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("eeeeeeeeee", "Camera" + requestCode + "  " + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 相机拍照完成后，返回图片路径
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    Intent data1 = new Intent();
                    if (captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        resultList.add(captureManager.getCurrentPhotoPath());
                    }
                    data1.putStringArrayListExtra("select_result", resultList);
                    Log.e("eeee", resultList.size() + "      拍照");
                    setResult(RESULT_OK, data1);
                    break;
            }
        }
        finish();
    }

}
