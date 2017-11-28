package com.su.p1.incomeprice.model;

/**
 * Created by ~ { P_Slyp } ~ on 11/23/2017.
 */

public class List {

    private final String date;
    private final int pictureList;
    private final String title;
    private final String memo;
    private final int money;

    public List(String date, int pictureList, String title, String memo, int money) {
        this.date = date;
        this.pictureList = pictureList;
        this.title = title;
        this.memo = memo;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public int getPictureList() {
        return pictureList;
    }

    public String getTitle() {
        return title;
    }

    public String getMemo() {
        return memo;
    }

    public int getMoney() {
        return money;
    }

}
