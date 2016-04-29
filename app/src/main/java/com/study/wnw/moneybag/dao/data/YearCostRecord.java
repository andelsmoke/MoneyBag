package com.study.wnw.moneybag.dao.data;

/**
 * Created by root on 16-4-7.
 */
public class YearCostRecord {

    private int id;

    private int year;

    private double yearCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getYearCost() {
        return yearCost;
    }

    public void setYearCost(double yearCost) {
        this.yearCost = yearCost;
    }
}
