package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/7.
 */

public class HttpUtils {
    private final static String TAG = "OkHttpUtil";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient;
    private static HttpUtils mInstance;
    private Gson g;
    private Handler handler;
    public static Call call;
    private Context context;

    public HttpUtils() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
        g = new Gson();
    }

    public static HttpUtils getInstance(Context context) {
        mInstance.context = context;
        if (mInstance == null) {
            synchronized (HttpUtils.class) {
                mInstance = new HttpUtils();
            }
        }
        return mInstance;
    }


    /**
     * 异步post请求
     *
     * @param url
     * @param callback
     * @param map
     */
    public void doPost(String url, final ResultCallback callback, Map<String, Object> map) {

        if (okHttpClient == null) okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, toJson(map));
        Log.e("OkHttp", "url = " + url);
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "无法连接到服务器", Toast.LENGTH_SHORT).show();
                        callback.onError(request, e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = null;
                try {
                    json = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, json);
                final Object o = g.fromJson(json, callback.mType);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(o);
                    }
                });
            }


        });
    }






    /**
     * 转化成json字符串
     *
     * @param map
     */
    private String toJson(Map<String, Object> map) {
        String json = null;
        if (map == null) map = new HashMap<>();
        json = g.toJson(map);
        Log.e("OkHttp", json);
        return json;
    }

    public static abstract class ResultCallback<T> {
        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);

        public abstract void onFail(String response);
    }


}
