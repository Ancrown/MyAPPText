package com.example.administrator.myapptextttttttt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.myapptextttttttt.R;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.HttpService;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.HttpUtils;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.MovieSubject;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.RetrofitServiceManager;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.bean.Movie;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader.MovieLoader;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader.MovieNewLoader;
import com.example.administrator.myapptextttttttt.utils.AddressRequestString;
import com.example.administrator.myapptextttttttt.utils.BasicParamsInterceptor;
import com.google.gson.Gson;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 网络请求
 */

public class NetworkRequestActivity extends Activity {
    private Map<String, Object> map;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_network_request);
        map = new HashMap();
        map.put("start", 0);
        map.put("count", 5);
        //  initRetrofit();
        //initRXJava();
        // initOkHttp();
        //init();
        // initSeal();
        initNew();
        initttt();

    }

    /**
     * Retrofit 写一个网络请求
     */
    public void initRetrofit() {
        /**
         * 创建一个Retrofit 实例，并且完成相关的配置
         * 说明：配置了接口的baseUrl和一个converter,GsonConverterFactory 是默认提供的Gson 转换器，Retrofit 也支持其他的一些转换器
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AddressRequestString.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        //获取接口实例
        HttpService HttpServiece = retrofit.create(HttpService.class);

        //调用方法得到一个Call
        //post请求
        Call<MovieSubject> call = HttpServiece.getTop250GET(0, 20);


        //进行网络请求
        call.enqueue(new Callback<MovieSubject>() {
            //成功
            @Override
            public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
                Log.e("eeeeeee", "成功！");
                Log.e("eeeeeee", response.toString());
            }

            //失败
            @Override
            public void onFailure(Call<MovieSubject> call, Throwable t) {
                Log.e("eeeeeee", "失败！");

            }
        });
//        //同步请求
//        try {
//            Response<MovieSubject> response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 配合RxJava 使用
     */
    public void initRXJava() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AddressRequestString.BASE_URL)
                // 创建Retrofit 的时候添加如下代码
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        /**
         * Activity 或者 Fragment 中传入 Subscriber 建立订阅关系
         *
         */
        //获取接口实例
        HttpService httpServiece = retrofit.create(HttpService.class);

        Subscription subscription = httpServiece.getTop250GET_RX(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    //处理数据
                    @Override
                    public void onNext(MovieSubject o) {
                        Log.e("eeeeee", "成功！");
                        Log.e("eeeeee", o.toString());
                    }
                });

    }

    //超时时间
    private final int DEFAULT_TIME_OUT = 5000;

    /**
     * Retrofit+RXjava+Okhttp
     */
    public void initOkHttp() {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        // 添加公共参数拦截器
        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addHeaderParam("userName", "")//添加公共参数
                .addHeaderParam("device", "")
                .build();

        //绑定拦截
        builder.addInterceptor(basicParamsInterceptor);

        // 创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(AddressRequestString.BASE_URL)
                .build();
        /**
         * Activity 或者 Fragment 中传入 Subscriber 建立订阅关系
         *
         */
        //获取接口实例
        HttpService HttpServiece = retrofit.create(HttpService.class);

        Subscription subscription = HttpServiece.getTop250POST_RX(0, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    //处理数据
                    @Override
                    public void onNext(MovieSubject o) {
                        Log.e("eeeeee", "成功！");
                        Log.e("eeeeee", o.toString());
                    }
                });

    }


    /**
     * 使用管理
     * 说明：创建了一个RetrofitServiceManager类，
     * 该类采用单例模式，在私有的构造方法中，生成了Retrofit 实例，并配置了OkHttpClient和一些公共配置。
     * 提供了一个create（）方法，生成接口实例，接收Class范型，因此项目中所有的接口实例Service都可以用这个来生成，
     * 代码如下：
     */
    public void init() {

        HttpService httpServiece = RetrofitServiceManager.getInstance().create(HttpService.class);

        Gson gson = new Gson();
        String testString = gson.toJson(new TestBean(0, 5));
        Log.e("eeee", "请求GOSN：" + testString);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), testString);
        //  HttpServiece.getTop250POST_RX(new TestBean(0, 10))
        httpServiece.getTop250POST_RX("top250", body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieSubject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    //处理数据
                    @Override
                    public void onNext(MovieSubject o) {
                        Log.e("eeeeee", o.toString());
                    }
                });
    }


    public void initNew() {

        MovieLoader movieLoader = new MovieLoader(this);
//        Subscription subscription = movieLoader.getMovie(map).subscribe(new Action1<List<Movie>>() {
//            @Override
//            public void call(List<Movie> movies) {
//                Log.e("eeeee", movies.get(0).getTitle() + "  " + movies.size());
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//            }
//        });

//        Subscription subscription1 = movieLoader.getMovie(map).subscribe(new Action1<List<Movie>>() {
//            @Override
//            public void call(List<Movie> movies) {
//                Log.e("eeeee", movies.get(0).getTitle() + "  " + movies.size());
//            }
//        });

        MovieNewLoader movieNewLoader = new MovieNewLoader(this);
        Subscription subscription2 = movieNewLoader.getList(map).subscribe(new Action1<MovieSubject>() {
            @Override
            public void call(MovieSubject o) {
                Log.e("eeeee", o.getTitle() + "  " + o.getSubjects().size());
            }
        });


//        Gson gson = new Gson();
//        String testString = gson.toJson(new TestBean(0, 5));
//        Log.e("eeee", "请求GOSN：" + testString);
//
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), testString);
//
//        RetrofitServiceManager.getInstance().create(HttpService.class)
//                .getTop250POST_RX(new TestBean(0, 5))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//
//                    }
//                });

    }

    //封装的retrofit网络工具类 https://github.com/open-android/RetrofitUtils
    public void initttt() {
        Request request = ItheimaHttp.newPostRequest("top250");
        request.putParamsMap(map);
        ItheimaHttp.send(request, new HttpResponseListener<MovieSubject>() {

            @Override
            public void onResponse(MovieSubject movieSubject, Headers headers) {
                Log.e("eeeeee", "initttt  " + movieSubject.getTitle());
            }

            /**
             * 可以不重写失败回调
             * @param call
             * @param e
             */
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {

            }
        });
    }

    public class TestBean {
        public TestBean(int start, int count) {
            this.start = start;
            this.count = count;
        }

        private int start;
        private int count;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }
    }

}
