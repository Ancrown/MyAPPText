package com.example.administrator.myapptextttttttt.utils.web;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.myapptextttttttt.activity.DisplayPhotoActivity;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.PhotoPreviewActivity;
import com.example.administrator.myapptextttttttt.views.photoview.photopicker.intent.PhotoPreviewIntent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MJavascriptInterface {
    private Context context;
    private List<String> imageUrls;

    public MJavascriptInterface(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = Arrays.asList(imageUrls);
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img) {

        PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
        intent.setCurrentItem(imageUrls.indexOf(img));
        intent.setPhotoPaths((ArrayList<String>) imageUrls);
        context.startActivity(intent);

//        Intent intent = new Intent();
//        intent.putExtra("imageUrls", imageUrls);
//        intent.putExtra("curImageUrl", img);

//        intent.setClass(context, PhotoBrowserActivity.class);
//        context.startActivity(intent);
        for (int i = 0; i < imageUrls.size(); i++) {
            Log.e("图片地址" + i, imageUrls.get(i).toString());
        }
    }
}
