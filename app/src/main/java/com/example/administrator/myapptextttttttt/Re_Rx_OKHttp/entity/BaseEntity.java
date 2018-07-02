package com.example.administrator.myapptextttttttt.Re_Rx_OKHttp.entity;

/**
 * Created by Administrator on 2018/5/7.
 */

public class BaseEntity<T> {
    /**
     * status :
     * msg :
     * data :
     */

    private String status;
    private String msg;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
