
package com.study.wnw.moneybag.dao.data;


import java.sql.Date;

public class CostRecord  {


    private int id;
    private double cost;
    private String type;
    private Date date;
    private String remark;


    public CostRecord(){

    }

    public CostRecord(double cost,String type,Date date,String remark){
        this.cost = cost;
        this.type = type;
        this.date = date;
        this.remark = remark;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}

