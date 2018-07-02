package com.example.administrator.myapptextttttttt.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseActivity;
import com.example.administrator.myapptextttttttt.utils.glideutils.GlideUtils;
import com.example.administrator.myapptextttttttt.utils.PermissionManager;
import com.example.administrator.myapptextttttttt.utils.ToolUtils;
import com.example.administrator.myapptextttttttt.utils.fileutils.CompressHelper;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.PhotoPickerActivity;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/28
 * 描述: 图片压缩
 */

public class CompressImg extends BaseActivity {

    private final int IMG_PERMISSION1 = 1000; //图片

    private File fileOld;

    private File fileNew;

    @BindView(R.id.main_image_old)
    ImageView mainImageOld;
    @BindView(R.id.main_text_old)
    TextView mainTextOld;

    @BindView(R.id.main_image_new)
    ImageView mainImageNew;
    @BindView(R.id.main_text_new)
    TextView mainTextNew;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        getTitleView().setData("图片文件压缩", 0, 0, null, 0, null, null);
    }

    @Override
    protected int getLayout() {
        return R.layout.aty_compress_img;
    }


    @OnClick({R.id.main_text_old, R.id.main_text_new, R.id.main_button_old, R.id.main_button_new})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.main_button_old:

                if (PermissionManager.permissionApplication(CompressImg.this, PermissionManager.ImgPermission(), IMG_PERMISSION1)) {
                    ToolUtils.getNewsPopupWindow(mainImageOld, CompressImg.this, -1);
                }

                break;
            case R.id.main_button_new:
                fileNew = CompressHelper.getDefault(getApplicationContext()).compressToFile(fileOld);

                GlideUtils.LoadImage(this, fileNew.getPath(), mainImageNew);

                mainTextNew.setText(String.format("Size : %s", getReadableFileSize(fileNew.length())));
                break;

        }
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
        Log.e("ssssssssssss", requestCode + "+" + resultCode);

        if (resultCode == RESULT_OK) {
            GlideUtils.LoadImage(this, data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0), mainImageOld);
            fileOld = new File(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0));
            mainTextOld.setText(String.format("Size : %s", getReadableFileSize(fileOld.length())));
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
            if (requestCode == IMG_PERMISSION1) {
                ToolUtils.getNewsPopupWindow(mainImageOld, CompressImg.this, -1);
            }
        } else {
            if (requestCode == IMG_PERMISSION1) {
                PermissionManager.showDialog(CompressImg.this,
                        "使用权限使用权限被禁止，一些功能无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'储存空间，相机)");
            }
        }
    }

    /**
     * 获取文件大小的String
     *
     * @param size
     * @return
     */
    public String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
