package com.study.wnw.moneybag.dao.view.expandablelistview;

import android.content.Context;
import android.widget.RelativeLayout;

import java.sql.Date;

/**
 * Created by wangnainwen on 2016/3/7.
 * 自定义的ExpandableListView中child的内容，在child中主要是四个TextView中的数据，在这里得到他的数据
 */
public class ExpandableListViewChild extends RelativeLayout {

    private int id;
    private String type;
    private Date date;
    private String remark;
    private double cost;

    public ExpandableListViewChild(Context context){
        super(context);
    }

    public void setView(String mType,Date mDate,String mRemark,double mCost){
        type = mType;
        date = mDate;
        remark = mRemark;
        cost = mCost;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date mdate) {
        this.date = mdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String Remark) {
        this.remark = Remark;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double mCost) {
        this.cost = mCost;
    }
}
