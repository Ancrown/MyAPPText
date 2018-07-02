package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader;

import android.content.Context;

import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.HttpService;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.MovieSubject;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.RetrofitServiceManager;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader.base.ObjectLoader;

import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2018/5/8.
 */

public class MovieNewLoader extends ObjectLoader {
    private HttpService httpService;

    public MovieNewLoader(Context context) {
        super(context);
        httpService = RetrofitServiceManager.getInstance().create(HttpService.class);
    }

    public Observable getList(Map map) {

        return observe(httpService.getTop250POST_RX(getBoy(map))).map(new Func1<MovieSubject, MovieSubject>() {
            @Override
            public MovieSubject call(MovieSubject movieSubject) {
                pd.dismiss();
                return movieSubject;
            }
        });
    }


}
