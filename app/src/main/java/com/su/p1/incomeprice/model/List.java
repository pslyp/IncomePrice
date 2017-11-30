package com.su.p1.incomeprice.model;

/**
 * Created by ~ { P_Slyp } ~ on 11/23/2017.
 */

public class List {

    private String date;
    private int pictureList;
    private String title;
    private String memo;
    private double money;
    private String type;

    public List() {

    }

    public List(String date, int pictureList, String title, String memo, double money, String type) {
        this.date = date;
        this.pictureList = pictureList;
        this.title = title;
        this.memo = memo;
        this.money = money;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPictureList() {
        return pictureList;
    }

    public void setPictureList(int pictureList) {
        this.pictureList = pictureList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
