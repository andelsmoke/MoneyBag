package com.study.wnw.moneybag.dao.view.expandablelistview;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by wangnainwen on 2016/3/7.
 * 自定义ExpandableListView中Group的数据，在这里主要是Group中包含两个TextView中的数据
 */
public class ExpandableListViewParent extends RelativeLayout{

    private String month;
    private String cost;

    public ExpandableListViewParent(Context context)
    {
        super(context);
    }
    public void setmView(String Month,String Cost){
        month = Month;
        cost = Cost;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
