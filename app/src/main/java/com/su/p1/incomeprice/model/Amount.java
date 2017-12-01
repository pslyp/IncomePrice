package com.su.p1.incomeprice.model;

/**
 * Created by ~ { P_Slyp } ~ on 12/1/2017.
 */

public class Amount {

    private double income;
    private double Expenditure;

    public Amount() {

    }

    public Amount(double income, double expenditure) {
        this.income = income;
        this.Expenditure = expenditure;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpenditure() {
        return Expenditure;
    }

    public void setExpenditure(double expenditure) {
        Expenditure = expenditure;
    }

    public String getInEx(double money) {
        String ie;
        if(String.valueOf(money).substring(String.valueOf(money).indexOf(".")).length() == 2)
            ie = String.format("%.0f", money);
        else
            ie = String.valueOf(money);
        
        return ie;
    }

}
