package com.su.p1.incomeprice.model;

/**
 * Created by ~ { P_Slyp } ~ on 11/23/2017.
 */

public class List {

    public final int pictureList;
    public final String title;
    public final String memo;
    public final int money;

    public List(int pictureList, String title, String memo, int money) {
        this.pictureList = pictureList;
        this.title = title;
        this.memo = memo;
        this.money = money;
    }
}
