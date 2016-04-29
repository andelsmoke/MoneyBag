package com.study.wnw.moneybag.dao.dao;


import com.study.wnw.moneybag.dao.data.YearCostRecord;

import java.util.List;

/**
 * Created by root on 16-4-7.
 */
public interface YearCostRecordDao {
    /**add a month's cost rerord*/
    public void addYearCostRecord(YearCostRecord yearCostRecord);

    /**delete*/
    public void deleteYearCostRecord(int id);

    /**update*/
    public void updateYearCostRecord(YearCostRecord yearCostRecord);

    /**find by id*/
    public YearCostRecord findYearCostRecord(int id);

    /**find by year*/
    public List<YearCostRecord> findYearCostRecordByMonth(int year);

    /**find all*/
    public List<YearCostRecord> finAllYearCostRecord();
}
