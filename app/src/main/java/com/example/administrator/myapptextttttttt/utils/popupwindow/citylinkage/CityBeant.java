package com.example.administrator.myapptextttttttt.utils.popupwindow.citylinkage;

/**
 * 作者 ：刘晓伟
 * 工程名 ：FMW
 * 包名 ：www.ioi.com.fmw.dialog
 * 描述 ：Mark
 * 创建时间 ：2018/1/9
 */
public class CityBeant {
    private String name;
    private String zipcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public CityBeant(String name, String zipcode) {
        this.name = name;
        this.zipcode = zipcode;
    }
    public CityBeant() {
        super();
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "name='" + name + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
