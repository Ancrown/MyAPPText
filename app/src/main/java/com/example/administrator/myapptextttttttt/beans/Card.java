package com.example.administrator.myapptextttttttt.beans;

import com.example.administrator.myapptextttttttt.observer.Observer;
import com.example.administrator.myapptextttttttt.observer.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Card implements Serializable, Subject {

    private List<Observer> observers;

    private static final long serialVersionUID = -5376313495678563362L;

    public String title;
    public String info;
    public int imageSrc;
    public int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Card(String title, String info, int imageSrc, int type) {
        this.title = title;
        this.info = info;
        this.imageSrc = imageSrc;
        this.type = type;
        this.observers = new ArrayList<Observer>();
    }

    public Card(String title, String info, int imageSrc) {
        this.title = title;
        this.info = info;
        this.imageSrc = imageSrc;
        this.type = type;
        this.observers = new ArrayList<Observer>();
    }

    public void setTitle(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }
}