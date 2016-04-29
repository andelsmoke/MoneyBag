package com.study.wnw.moneybag.dao.view.aty;

import android.content.Context;

import com.study.wnw.moneybag.dao.dao.CostRecordDao;
import com.study.wnw.moneybag.dao.data.CostRecord;
import com.study.wnw.moneybag.dao.imp.CostRecordDaoImpl;
import com.study.wnw.moneybag.dao.sql.MySQLiteHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnw on 16-4-23.
 */
public class EveryTypeCost {
    /***/

    private Context mContext;
    private List<CostRecord> mCostRecordList;
    private MySQLiteHelper mSQLiteHelper;
    private CostRecordDao mCostRecordDao;

    /**最多显示十年,2012,2013,....2021*/
    private int YEAR_SIZE = 10;
    private int TYPE_SIZE = 7;
    private double everyTypeCost[][];
    private double selYear[];

    public EveryTypeCost(Context context){

        mContext = context;
        mCostRecordList = new ArrayList<CostRecord>();

        /**get all data from database*/
        mSQLiteHelper = new MySQLiteHelper(mContext,"MoneyRecord.db",null,1);
        mCostRecordDao = new CostRecordDaoImpl(mSQLiteHelper);
        mCostRecordList = mCostRecordDao.findAllCostRecord();

        everyTypeCost = new double[YEAR_SIZE][TYPE_SIZE];
        if(mCostRecordList != null){

            int length = mCostRecordList.size();
            for (int i = 0;i<length;i++){
                String date = mCostRecordList.get(i).getDate().toString();
                Date date1 = Date.valueOf(date);

                //获取年份
                String year = date.substring(0,4);
                int year1 = Integer.parseInt(year);

                double cost = mCostRecordList.get(i).getCost();
                String type = mCostRecordList.get(i).getType();

                int typeID = getTypeId(type);

                /**对应的这个月就加上*/
            if(year1 >=2012 && year1 <=2021)
                everyTypeCost[year1-2012][typeID] += cost;
            }
        }
    }


    /**
     * 我们应该提供的是一个每个月花费的数组
     * */
    public double[] getEveryTypeCost(int year){
        selYear = new double[TYPE_SIZE];
        for(int i = 0;i<TYPE_SIZE;i++){
            selYear[i] = everyTypeCost[year-2012][i];
        }
        return selYear;
    }

    private int getTypeId(String type){

        if (type.equals("衣")){
            return 0;
        }else if (type.equals("食")){
            return 1;
        }else if (type.equals("住")){
            return 2;
        }else if (type.equals("行")){
            return 3;
        }else if (type.equals("用")){
            return 4;
        }else if (type.equals("健康")){
            return 5;
        }else {
            return 6;
        }
    }
}
