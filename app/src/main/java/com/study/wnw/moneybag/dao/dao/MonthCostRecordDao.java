package com.study.wnw.moneybag.dao.dao;


import com.study.wnw.moneybag.dao.data.MonthCostRecord;

import java.util.List;



/**
 * Created by wangnainwen on 2016/3/21.
 */
public interface MonthCostRecordDao {

    /**add a month's cost rerord*/
    public void addMonthCostRecord(MonthCostRecord monthCostRecord);

    /**delete*/
    public void deleteMonthCostRecord(int id);

    /**update*/
    public void updateMonthCostRecord(MonthCostRecord monthCostRecord);
    
    /**find by id*/
    public MonthCostRecord findMonthCostRecord(int id);

    /**find by month*/
    public List<MonthCostRecord> findMonthCostRecordByMonth(int year, int month);

    /**find all*/
    public List<MonthCostRecord> finAllMonthCostRecord();
}
