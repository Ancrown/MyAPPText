package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 将一些重复的操作提出来，放到父类以免Loader 里每个接口都有重复代码
 * Created by zhouwei on 16/11/10.
 */
public class ObjectLoader {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Gson g;
    private Context context;
    public ProgressDialog pd;

    public ObjectLoader(Context context) {
        this.context = context;
        g = new Gson();
        pd = new ProgressDialog(context);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("加载...");
    }

    /**
     * @param observable
     * @param <T>
     * @return
     */
    protected <T> Observable<T> observe(Observable<T> observable) {
        pd.show();
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 转化成json字符串
     *
     * @param map
     */
    protected String toJson(Map<String, Object> map) {
        String json = null;
        if (map == null) map = new HashMap<>();
        json = g.toJson(map);
        Log.e("OkHttp", json);
        return json;
    }

    /**
     * 获得RequestBody
     */
    protected RequestBody getBoy(Map map) {
        return RequestBody.create(JSON, toJson(map));
    }
}