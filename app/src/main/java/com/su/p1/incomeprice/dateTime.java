package com.su.p1.incomeprice;

import java.util.Calendar;

/**
 * Created by ~ { P_Slyp } ~ on 12/1/2017.
 */

public class dateTime {

    private Calendar c = Calendar.getInstance();

    private int day, month, year;
    private String[] monthEngList = {" ", " Jan ", " Fab ", " Mar ", " Apr ", " May ", " Jun ", " Jul ", " Aug ", " Sep ", " Oct ", " Nov ", " Dec "};

    public dateTime() {
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
    }

    public dateTime(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDatePresent() {
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
    }

    public String getDateText(int day, int month, int year) {
        if(day == 0) {
            if(month == 0)
                return String.valueOf(year);
            return String.valueOf(month + 1) + String.valueOf(year);
        }
        else
            return (String.valueOf(day) + String.valueOf(month + 1) + String.valueOf(year));
    }

    public String getDateTextMonth(int day, int month, int year) {
        return (String.valueOf(day) + String.valueOf(monthEngList[month + 1]) + String.valueOf(year));
    }

}
