package com.example.administrator.myapptextttttttt.base;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.activity.MainActivity;
import com.example.administrator.myapptextttttttt.beans.UmengMsgBean;
import com.example.administrator.myapptextttttttt.utils.AddressRequestString;
import com.example.administrator.myapptextttttttt.utils.DensityHelper;
import com.google.gson.Gson;
import com.itheima.retrofitutils.ItheimaHttp;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 * 应用基础类
 *
 * @创建时间 ：2016/11/21
 * @项目名称 ：Circle
 * @类型名称 : BaseApplication
 * @文件类型 : BaseApplication.java
 * @创始人 : liuxi
 * @包名 ：com.ioi.distribution.base
 */
public class BaseApplication extends Application {
    public static final String TAG = "MyApplication";
    /**
     * 栈
     */
    public static Stack<Activity> activityStack;

    private static int mainTid;
    /**
     * 应用程序数据存储
     */
    private static Map<String, Object> appData;
    /**
     * 实例
     */
    private static BaseApplication instance;


    private Handler handler;

    public static Context appContext;

    public static synchronized BaseApplication getInstance() {
        return instance;
    }


    public static Context applicationContext;
    public static String token = "";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }


    @Override
    public void onCreate() {

        super.onCreate();

        ItheimaHttp.init(this, AddressRequestString.BASE_URL);


        applicationContext = this;
        instance = this;


        appContext = this;

        instance = this;

        //初始化集合
        appData = new HashMap<>();

        mainTid = android.os.Process.myTid();


        //初始化Activity管理
        initActivityManager();

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        UMConfigure.init(this, "5b2c4ce7f43e487250000092", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
        //umeng
        initUmeng();

    }

    private void initUmeng() {





        PushAgent mPushAgent = PushAgent.getInstance(this);


        /**
         *  通知的展示及提醒
         自定义通知打开动作
         开发者可自定义用户点击通知栏时的后续动作。自定义行为的数据放在UMessage.custom字段。
         在【友盟+】后台或通过API发送消息时，在“后续动作”中的“自定义行为”中输入相应的值或代码即可实现。
         若开发者需要处理自定义行为，则可以重写方法dealWithCustomAction()。
         其中自定义行为的内容，存放在UMessage.custom中。请在自定义Application类中添加以下代码：
         */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {


//                UmengMsgBean bean = new Gson().fromJson(msg.getRaw().toString(), UmengMsgBean.class);
//
//                Log.e("eeeeee", "dealWithCustomAction:" + bean.getPayload().getBody().getCustom() + " #######               " + msg.getRaw().toString());
//
//                Log.e("eeeeee", "dealWithCustomAction:" + msg.custom + "    " + bean.toString());

                Log.e("eeeeee", "dealWithCustomAction:" + msg.custom);
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);


        //自定义通知栏样式
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 通知的回调方法（通知送达时会回调）
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super，会展示通知，不调用super，则不展示通知。
                super.dealWithNotificationMessage(context, msg);


                Log.e("eeeeee", "dealWithNotificationMessage:" + msg.custom);
            }

            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);


        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        //  mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATIONPLAYSERVER);

        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                Log.i(TAG, "device token: " + deviceToken);

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i(TAG, "register failed: " + s + " " + s1);

            }
        });
    }


    /**
     * 初始化Activity管理
     */
    private void initActivityManager() {

        activityStack = new Stack<>();

        if (!activityStack.empty()) {
            throw new RuntimeException("不能二次初始化activity管理栈");
        }
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }


    public Map<String, Object> getAppData() {
        return appData;
    }

    public void setAppData(Map<String, Object> appData) {
        this.appData = appData;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public Handler getHandler() {
        return handler;
    }

    public void finishActivites() {

        if (activityStack != null && activityStack.size() != 0) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    {
        //微信
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //qq
        PlatformConfig.setQQZone("1106988350", "5dS59TyeosacpZ88");

    }
}
