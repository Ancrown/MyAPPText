package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp;

import com.example.administrator.myapptextttttttt.activity.NetworkRequestActivity;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/7.
 */

public interface HttpService<T> {


    /**
     * 1、@GET GET网络请求方式
     2、@POST POST网络请求方式
     3、@Headers() 头信息参数
     4、@Path() 路径参数，替换url地址中 { } 所括的部分
     5、@Query() 查询参数，将在url地址中追加类似“page=1”的字符串，形成提交给服务端的请求参数
     6、@QueryMap 查询参数集合，将在url地址中追加类似
     “type=text&username=abc&password=123”的字符串
     7、@FormUrlEncoded 对表单域中填写的内容进行编码处理，避免乱码
     8、@Field() 指定form表单域中每个空间的额name以及相应的数值
     9、@FieldMap 表单域集合
     10、@Multipart Post提交分块请求，如果上传文件，必须指定Multipart
     11、@Body Post提交分块请求
     */
    //获取豆瓣Top250 榜单  Retrofit

    /**
     * 获取豆瓣Top250 榜单
     * 说明：定义了一个方法getTop250,使用get请求方式，加上@GET 标签，
     * 标签后面是这个接口的 尾址top250,完整的地址应该是 baseUrl+尾址,
     * 参数 使用@Query标签，如果参数多的话可以用@QueryMap标签，接收一个Map
     */
    @GET("top250")
    Call<MovieSubject> getTop250GET(@Query("start") int start, @Query("count") int count);

    /**
     * 说明：使用POST 请求方式时，只需要更改方法定义的标签，
     * 用@POST 标签，参数标签用 @Field 或者@Body或者FieldMap
     * 注意：使用POST 方式时注意2点，
     * 1，必须加上 @FormUrlEncoded标签，否则会抛异常。
     * 2，使用POST方式时，必须要有参数，否则会抛异常, 源码抛异常的地方如下：
     */
    @FormUrlEncoded
    @POST("top250")
    Call<MovieSubject> getTop250POST(@Field("start") int start, @Field("count") int count);
    // Call<String> getTop250POST(@FieldMap Map map);


    //获取豆瓣Top250 榜单  Retrofit+RxJava

    /**
     * 更改定义的接口，返回值不再是一个Call ,而是返回的一个Observble.
     */
    @GET("top250")
    Observable<MovieSubject> getTop250GET_RX(@Query("start") int start, @Query("count") int count);


    //@Field() 指定form表单域中每个空间的额name以及相应的数值
    @FormUrlEncoded
    @POST("top250")
    Observable<MovieSubject> getTop250POST_RX(@Field("start") int start, @Field("count") int count);


    //添加头文件，第一种方式直接在api中请求方式之上添加，如下
//        @Headers({"Content-type:application/json",
//                "Content-Length:59"})
    //@FieldMap 表单域集合
    @FormUrlEncoded
    @POST("{path}")
    Observable<MovieSubject> getTop250POST_RX(@Path("path") String path, @FieldMap Map<String, Integer> map);


    @POST("top250")
    Observable<MovieSubject> getTop250POST_RX(@Body NetworkRequestActivity.TestBean testBean);

    @POST("top250")
    Observable<MovieSubject> getTop250POST_RX(@Body RequestBody body);

    //@Path() 路径参数，替换url地址中 { } 所括的部分
//        @FormUrlEncoded  @Body标签不能同时和@FormUrlEncoded、@Multipart标签同时使用。
    @POST("{path}")
    Observable<MovieSubject> getTop250POST_RX(@Path("path") String path, @Body RequestBody body);


//    //上传图片和描述
//    @Multipart
//    @POST("上传的地址")
//    Observable<String> uploadUserFile(
//            @Part("fileName") RequestBody description,
//            @Part("file\"; filename=\"image.png\"")RequestBody img
//    );
//
//    //上传图片和图片描述，图片类型，用户id等其它信息
//    //如果除了图片外还需要传递其它信息，只需要增加几个@Part就可以了。
//    @Multipart
//    @POST("上传的地址")
//    Observable<String> uploadUserFileAndId(
//            @Part("describe") String describe,
//            @Part("type") String type,
//            @Part("userid") String userid,
//            @Part("fileName") RequestBody description,
//            @Part("file\"; filename=\"image.png\"")RequestBody img
//    );

}
