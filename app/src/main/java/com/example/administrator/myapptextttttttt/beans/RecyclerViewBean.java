package com.example.administrator.myapptextttttttt.beans;

/**
 * 创建人: Administrator
 * 创建时间: 2018/5/30
 * 描述:
 */

public class RecyclerViewBean {
    private String text;
    private String id;
    private int type;

    public RecyclerViewBean(String text, String id, int type) {
        this.text = text;
        this.id = id;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
