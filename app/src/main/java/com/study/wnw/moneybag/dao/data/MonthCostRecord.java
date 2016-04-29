
package com.study.wnw.moneybag.dao.data;


import java.util.List;


/**
 * Created by wangnainwen on 2016/3/21.
 * this is a table to save the pill of a month
 */

public class MonthCostRecord {

    private int id;

    private int month;

    private double monthCost;

    private int yearid;


/**这里一个月的账单和单个账单是一对多的关系**/

    private List<CostRecord> costRecords ;

    public List<CostRecord> getCostRecords() {
        return costRecords;
    }

    public void setCostRecords(List<CostRecord> costRecords) {
        this.costRecords = costRecords;
    }

    public MonthCostRecord(){

    }

    public MonthCostRecord(int yearid, int month, double cost){
        this.yearid = yearid;
        this.month = month;
        this.monthCost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getMonthCost() {
        return monthCost;
    }

    public void setMonthCost(Double monthCost) {
        this.monthCost = monthCost;
    }

    public void setMonthCost(double monthCost) {
        this.monthCost = monthCost;
    }

    public int getYearid() {
        return yearid;
    }

    public void setYearid(int yearid) {
        this.yearid = yearid;
    }
}

