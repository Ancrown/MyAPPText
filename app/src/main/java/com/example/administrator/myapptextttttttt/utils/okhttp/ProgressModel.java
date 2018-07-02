package com.example.administrator.myapptextttttttt.utils.okhttp;

import java.io.Serializable;

/**
 * @创建时间 ：2016/8/24
 * @项目名称 ：WeWe
 * @类型名称 : ProgressModel
 * @文件类型 : ProgressModel.java
 * @创始人 : lius
 * @包名 ：com.ioi.wewe.okhttp
 */
public class ProgressModel implements Serializable {
    //当前读取字节长度
    private long currentBytes;
    //总字节长度
    private long contentLength;
    //是否读取完成
    private boolean done;

    public ProgressModel(long currentBytes, long contentLength, boolean done) {
        this.currentBytes = currentBytes;
        this.contentLength = contentLength;
        this.done = done;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public void setCurrentBytes(long currentBytes) {
        this.currentBytes = currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ProgressModel{" +
                "currentBytes=" + currentBytes +
                ", contentLength=" + contentLength +
                ", done=" + done +
                '}';
    }
}