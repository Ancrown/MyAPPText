package com.example.administrator.myapptextttttttt.utils.okhttp;

/**
 * @创建时间 ：2016/8/24
 * @项目名称 ：WeWe
 * @类型名称 : ProgressRequestListener
 * @文件类型 : ProgressRequestListener.java
 * @创始人 : lius
 * @包名 ：com.ioi.wewe.okhttp
 */

/**
 * Created by h2h on 2015/9/8.
 * 这只是个接口
 */
public interface ProgressRequestListener {
    void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}