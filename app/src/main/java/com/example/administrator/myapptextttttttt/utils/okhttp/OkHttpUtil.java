package com.example.administrator.myapptextttttttt.utils.okhttp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.base.BaseApplication;
import com.example.administrator.myapptextttttttt.dialog.TipLoadDialog;
import com.example.administrator.myapptextttttttt.utils.okhttp.ProgressRequestBody;
import com.example.administrator.myapptextttttttt.utils.okhttp.UIProgressRequestListener;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Author  ：龙 on 2016/6/20 16:20
 * Email   : wangjinlong@aiouai.com
 * Function: okHttp请求
 */
public class OkHttpUtil {
    private final static String TAG = "OkHttpUtil";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient;
    private static OkHttpUtil mInstance;
    private Gson g;
    private Handler handler;
    //加载框
    public static TipLoadDialog tipLoadDialogYes;
    public static TipLoadDialog tipLoadDialogNo;

    public OkHttpUtil() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
        g = new Gson();
    }

    public static OkHttpUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (OkHttpUtil.class) {
                mInstance = new OkHttpUtil();
            }
        }
        tipLoadDialogYes = new TipLoadDialog(context).setNoShadowTheme();

        tipLoadDialogNo = new TipLoadDialog(context).setNoShadowTheme().setMsgAndType("无法连接到服务器", TipLoadDialog.ICON_TYPE_FAIL);


        return mInstance;
    }

    /**
     * getq请求
     *
     * @param url
     * @param callback
     */
    public void doGet(String url, final ResultCallback callback) {
        if (okHttpClient == null) okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //new call
        Call call = okHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onError(request, e);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                Object o = g.fromJson(response.body().string(), callback.mType);
                callback.onResponse(o);
            }
        });
    }


    /**
     * 单文件上传
     */
    public void UpLoadFile(String url, final ResultCallback callback, File file, String fileKey, Map<String, String> map) {
        List<File> files = new ArrayList<>();
        files.add(file);
        List<String> fileKeys = new ArrayList<>();
        fileKeys.add(fileKey);
        Log.e("OkHttp", "url = " + url);
        upLoad(url, callback, files, fileKeys, map, false);
    }

    /**
     * 多文件上传
     */
    public void UpLoadFile(String url, final ResultCallback callback, List<File> files, List<String> fileKeys, Map<String, String> map) {
        upLoad(url, callback, files, fileKeys, map, false);
    }

    private void upLoad(String url, final ResultCallback callback, List<File> files, List<String> fileKeys, Map<String, String> map, boolean isProgress) {

        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                    RequestBody.create(null, String.valueOf(entry.getValue())));
        }
        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
//                if(file==null){
//                    file = new File("");
//                    fileBody = RequestBody.create(MediaType.parse(guessMimeType("")), file);
//                    //TODO 根据文件名设置contentType
//                    builder.addPart(Headers.of("Content-Disposition",
//                                    "form-data; name=\"" + fileKeys.get(i) + "\"; filename=\"" + "" + "\""),
//                            fileBody);
//                }else {
                if (file != null) {
                    String fileName = file.getName();
                    fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                    //TODO 根据文件名设置contentType
                    builder.addPart(Headers.of("Content-Disposition",
                            "form-data; name=\"" + fileKeys.get(i) + "\"; filename=\"" + fileName + "\""),
                            fileBody);
                }

//                }


            }
        }
        RequestBody requestBody = builder.build();
//        Log.e("requestBody", requestBody.toString());
        Request request;
        if (isProgress) {
            request = new Request.Builder()
                    .url(url)
                    .post(new ProgressRequestBody(requestBody, progressListener))
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BaseApplication.appContext, BaseApplication.appContext.getString(R.string.serverError), Toast.LENGTH_SHORT).show();
                        callback.onError(request, e);
                    }
                });
            }

            @Override
            public void onResponse(Response response) {
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

    private UIProgressRequestListener progressListener;

    /**
     * 带进度的文件上传
     */

    public void UpLoadFile(String url, final ResultCallback callback, List<File> files, List<String> fileKeys, Map<String, String> map, UIProgressRequestListener progressListener) {
        this.progressListener = progressListener;
        upLoad(url, callback, files, fileKeys, map, true);
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    //    /**
//     * 同步post请求
//     *
//     * @param url
//     * @param callback
//     * @param map
//     */
//    public synchronized void _DoPost(String url, final ResultCallback callback, Map<String, Object> map) {
//        if (okHttpClient == null) okHttpClient = new OkHttpClient();
//        RequestBody requestBody = RequestBody.create(JSON, toJson(map));
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                String json = null;
//                try {
//                    json = okHttpClient.newCall(request).execute().body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.e(TAG, json);
//                final Object o = g.fromJson(json, callback.mType);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        callback.onResponse(o);
//                    }
//                });
//            }
//        }.start();
//    }
    public static Call call;

    /**
     * 异步post请求 带加载框的
     *
     * @param url
     * @param callback
     * @param map
     */
    public void doPost(String url, final ResultCallback callback, Map<String, Object> map, final String dialogText) {
        //设置另一种loading文字动画,注意不要加后缀...
        tipLoadDialogYes.setMsgAndType(TextUtils.isEmpty(dialogText) ? "加载中" : dialogText, TipLoadDialog.ICON_TYPE_LOADING2)
                .show();

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
            public void onFailure(final Request request, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tipLoadDialogYes.isShowing())
                            tipLoadDialogYes.dismiss();
                        tipLoadDialogNo.show();
                        callback.onError(request, e);
                    }
                });
            }

            @Override
            public void onResponse(Response response) {
                String json = null;
                int status = 0;
                String msg = "";
                JSONObject jsonObject = null;

                try {
                    json = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, json);

                try {
                    jsonObject = new JSONObject(json);
                    status = (int) jsonObject.get("status");
                    msg = (String) jsonObject.get("msg");
                    Log.e(TAG + "eeeeee", status + "网络请求状态");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status == 0) {
                    final Object o = g.fromJson(json, callback.mType);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //结束加载框
                            tipLoadDialogYes.dismiss();

                            callback.onResponse(o);
                        }
                    }, 2000);
                } else {
                    final String finalMsg = msg;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (tipLoadDialogYes.isShowing())
                                tipLoadDialogYes.dismiss();
                            tipLoadDialogNo.setMsgAndType(finalMsg, TipLoadDialog.ICON_TYPE_FAIL);
                            tipLoadDialogNo.show();
                        }
                    });
                }

            }
        });
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
            public void onFailure(final Request request, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tipLoadDialogNo.show();
                        callback.onError(request, e);
                    }
                });
            }

            @Override
            public void onResponse(Response response) {
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
     * 处理上拉下拉加载的
     */
    public void doPostList(String url, final ResultCallback callback, Map<String, Object> map, String dialogText, int page) {
        if (page == 0) {
            doPost(url, callback, map, dialogText);
        } else {
            doPost(url, callback, map);
        }
    }

//    /**
//     * 异步post请求
//     * 判断另个手机重复登陆
//     *
//     * @param url
//     * @param callback
//     * @param map
//     */
//    public void doPost(String url, final ResultCallback callback, Map<String, Object> map, final Activity activity) {
//
//        if (okHttpClient == null) okHttpClient = new OkHttpClient();
//        RequestBody requestBody = RequestBody.create(JSON, toJson(map));
//        Log.e("OkHttp", "url = " + url);
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(final Request request, final IOException e) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(BaseApplication.appContext, activity.getString(R.string.serverError), Toast.LENGTH_SHORT).show();
//                        callback.onError(request, e);
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Response response) {
//                String json = null;
//                try {
//                    json = response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.e(TAG, json);
//                final Object o = g.fromJson(json, callback.mType);
//
//
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject(json);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    final String status = jsonObject.getString("status");
//                    Log.e("OkHttp::status", status);
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            callback.onResponse(o);
//
//                            if ("-1".equals(status)) {
//                                new AlertDialog.Builder(activity).setTitle(activity.getString(R.string.Off_line_notification))
//                                        .setMessage(activity.getString(R.string.Off_line_notification_MESSAGE)).
//                                        setPositiveButton(activity.getString(R.string.yes), new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//
//                                                SharedPreferences sp = activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                                                sp.edit().putBoolean("ISCHECK", false).commit();
//
//                                                activity.startActivity(new Intent(activity, LoginActivity.class)
//                                                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//                                            }
//                                        }).setCancelable(false).show();
//
//                            }
//
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }


    /**
     * post请求
     *
     * @param url
     * @param callback
     * @param map
     */
    public void doPost(String url, final Callback callback, Map<String, Object> map) {
        if (okHttpClient == null) okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, toJson(map));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
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


    }
}
