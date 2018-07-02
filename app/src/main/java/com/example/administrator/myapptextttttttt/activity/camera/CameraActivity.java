package com.example.administrator.myapptextttttttt.activity.camera;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.myapptextttttttt.utils.FileUtil;
import com.example.administrator.myapptextttttttt.utils.camera.CameraView;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.PhotoPickerActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by hzwangchenyan on 2017/6/15.
 */
public class CameraActivity extends Activity implements CameraView.CameraListener {

    public static final int MY_CAMER_REQUSTCODE = 11;

    private static final String TAG = "CameraActivity";
    private CameraView mCameraView;
    private String mPath;
    private int selectModel;

    public static void startForResult(Activity activity, int requestCode, int selectModel) {


        String path = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/camera_" + System.currentTimeMillis() + ".jpg";
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, path);
        intent.putExtra("selectModel", selectModel);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mCameraView = new CameraView(this);
        setContentView(mCameraView);

        mPath = getIntent().getStringExtra(MediaStore.EXTRA_OUTPUT);
        selectModel = getIntent().getIntExtra("selectModel", 0);
        if (TextUtils.isEmpty(mPath)) {
            finish();
            return;
        }

        mCameraView.setCameraListener(this);
    }

    @Override
    public void onCapture(Bitmap bitmap) {
        File file = new File(mPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            Log.e(TAG, "save picture error", e);
        }

        if (file.exists()) {
//            Intent data = new Intent();
//            data.setData(Uri.parse(mPath));
//            setResult(RESULT_OK, data);

            if (selectModel >= 0) {
                ArrayList resultList = new ArrayList<>();
                resultList.add(mPath);
                Intent data1 = new Intent();
                data1.putStringArrayListExtra("select_result", resultList);
                setResult(RESULT_OK, data1);
                finish();
                Log.e("eeeeee", "照片：" + mPath);

            } else {
                imageUri = Uri.fromFile(new File(mPath));
                startPhotoZoom(imageUri);
            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_CUT) {

            if (imageUri != null) {
                Log.e("eeeeee", "剪切：" + mPath);

                mPath = getFileByUri(imageUri, CameraActivity.this).getPath();
                ArrayList resultList = new ArrayList<>();
                resultList.add(mPath);
                Intent data1 = new Intent();
                data1.putStringArrayListExtra("select_result", resultList);
                setResult(RESULT_OK, data1);
                finish();
            }


        }
    }

    private final int PHOTO_REQUEST_CUT = 502;
    public Uri imageUri;

    /**
     * 调用系统裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);

        //设置了true的话直接返回bitmap，可能会很占内存
        intent.putExtra("return-data", false);
        //设置输出的格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //设置输出的地址
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //不启用人脸识别
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //uri 转成file
    public static File getFileByUri(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
// 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();
            return new File(path);
        } else {
//Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

    @Override
    public void onCameraClose() {
        finish();
    }

    @Override
    public void onCameraError(Throwable th) {
        Log.e(TAG, "camera error", th);
        onCameraClose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraView.onPause();
    }
}
