package com.study.wnw.moneybag.dao.dao;


import com.study.wnw.moneybag.dao.data.CostRecord;

import java.util.List;

/**
 * Created by wangnainwen on 2016/3/20.
 * this is a interface of to execute the action of database
 */
public interface CostRecordDao {
    /** insert a cost record */
    public void insertCostRecord(CostRecord costRecord);

    /** delete a cost record */
    public void deleteCostRecord(int id);

    /**update a cost record*/
    public void updateCostRecord(CostRecord costRecord);

    /**select a cost record*/
    public CostRecord findCostRecord(int id);

    /** select all cost record*/
    public List<CostRecord> findAllCostRecord();
}
