package com.study.wnw.moneybag.dao.view.aty;

import android.content.Context;

import com.study.wnw.moneybag.dao.dao.CostRecordDao;
import com.study.wnw.moneybag.dao.data.CostRecord;
import com.study.wnw.moneybag.dao.imp.CostRecordDaoImpl;
import com.study.wnw.moneybag.dao.log.LogUtil;
import com.study.wnw.moneybag.dao.sql.MySQLiteHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by wnw on 16-4-23.
 */
public class EveryMonthCost {

    /**YEAR:2012,2017,2018......,2021*/

    private int YEAR_SIZE = 10;
    private int MONTH_SIZE = 12;

    private Context mContext;
    private List<CostRecord> mCostRecordList;
    private MySQLiteHelper mSQLiteHelper ;
    private CostRecordDao mCostRecordDao;

    /**所有年的数据*/
    private double everyMonthCost[][];

    /**用户选中那一年的数据*/
    private double selYearCost[];

    public EveryMonthCost(Context context){

        mContext = context;
        mCostRecordList = new ArrayList<CostRecord>();

        /**get all data from database*/
        mSQLiteHelper = new MySQLiteHelper(mContext,"MoneyRecord.db",null,1);
        mCostRecordDao = new CostRecordDaoImpl(mSQLiteHelper);
        mCostRecordList = mCostRecordDao.findAllCostRecord();

        everyMonthCost = new double[YEAR_SIZE][MONTH_SIZE];

        //
        if(mCostRecordList != null){

            int length = mCostRecordList.size();
            for (int i = 0;i<length;i++){
                String date = mCostRecordList.get(i).getDate().toString();
                Date date1 = Date.valueOf(date);

                //int year = date1.getYear();      //每一年的
                int month = date1.getMonth()+1;  //这个是对应的月份
                double cost = mCostRecordList.get(i).getCost();
                String year = date.substring(0,4);
                int year1 = Integer.parseInt(year);


                /**对应的这个月就加上*/
                LogUtil.d("wnww",year1+" "+(year1-2012)+" "+(month-1));
                if(year1>=2012 && year1<=2021){
                    everyMonthCost[year1-2012][month-1] += cost;
                }
            }
        }

    }


    /**
     * 我们应该提供的是一个某一年的每个月花费的数组
     * */
    public double[] getEveryMonthCost(int year){
        selYearCost = new double[MONTH_SIZE];
        for(int i =0;i<MONTH_SIZE;i++){
            selYearCost[i] = everyMonthCost[year-2012][i];
        }
        return selYearCost;
    }

    /**返回一个数组，12个对应每个月的消费*/
    public double[] getPerMonthCost(int year){
        double cost[] =new double[12];
        for(int i=0;i<MONTH_SIZE;i++){
            cost[i] = everyMonthCost[year-2012][i];
        }
        return cost;
    }

    /**
     * 返回某一年的全部记录
     * */
    public List<CostRecord> getSelYearAllRecord(int year,int month){
        List<CostRecord> everyRecord = new ArrayList<CostRecord>();
        if(mCostRecordList != null){
            for(int i =0;i<mCostRecordList.size();i++){
                String date = mCostRecordList.get(i).getDate().toString();
                Date date1 = Date.valueOf(date);
                int month1 = date1.getMonth()+1;
                String year1 = date.substring(0,4);
                int year2 = Integer.parseInt(year1);
                if(year == year2 && month1 == month){
                    everyRecord.add(mCostRecordList.get(i));
                }
            }
        }
        return everyRecord;
    }
}
