package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader;

import android.content.Context;

import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.HttpService;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.MovieSubject;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.RetrofitServiceManager;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.bean.Movie;
import com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.loader.base.ObjectLoader;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2018/5/5.
 */

public class MovieLoader extends ObjectLoader {
    private HttpService httpService;

    public MovieLoader(Context context) {
        super(context);
        httpService = RetrofitServiceManager.getInstance().create(HttpService.class);
    }

    /**
     * 获取电影列表
     *
     * @return
     */
    public Observable<List<Movie>> getMovie(Map map) {



        return observe(httpService.getTop250POST_RX(getBoy(map)))
                .map(new Func1<MovieSubject, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieSubject movieSubject) {
                        pd.dismiss();
                        return movieSubject.subjects;
                    }
                });
    }


}

