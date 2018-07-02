package com.example.administrator.myapptextttttttt.views.photoview.photopicker;

/**
 * 照片选择类型
 * Created by foamtrace on 2015/8/25.
 */
public enum SelectModel {
    SHEAR(PhotoPickerActivity.MODE_SHEAR),  //单选
    SINGLE(PhotoPickerActivity.MODE_SINGLE),  //单选
    MULTI(PhotoPickerActivity.MODE_MULTI);      //多选

    private int model;

    SelectModel(int model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.valueOf(this.model);
    }
}
