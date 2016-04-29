package com.study.wnw.moneybag.dao.imp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.study.wnw.moneybag.dao.dao.CostRecordDao;
import com.study.wnw.moneybag.dao.data.CostRecord;
import com.study.wnw.moneybag.dao.log.LogUtil;
import com.study.wnw.moneybag.dao.sql.MySQLiteHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-4-7.
 */

public class CostRecordDaoImpl implements CostRecordDao {

    private MySQLiteHelper sqLiteHelper;

    public CostRecordDaoImpl(MySQLiteHelper mySQLiteHelper){
        this.sqLiteHelper = mySQLiteHelper;
    }


    public void insertCostRecord(CostRecord costRecord) {
        SQLiteDatabase database = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cost",costRecord.getCost());
        values.put("date", String.valueOf(costRecord.getDate()));
        values.put("type",costRecord.getType());
        values.put("remark",costRecord.getRemark());
        database.insert("costrecord",null,values);
        LogUtil.d("wnw2",String.valueOf(costRecord.getDate()));
    }

    @Override
    public void deleteCostRecord(int id) {
        SQLiteDatabase database = sqLiteHelper.getWritableDatabase();
        database.delete("costrecord","id = ?",new String[]{id+""});
    }

    @Override
    public void updateCostRecord(CostRecord costRecord) {
        SQLiteDatabase database = sqLiteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",costRecord.getId());
        values.put("cost",costRecord.getCost());
        values.put("date",costRecord.getDate().toString());
        values.put("type",costRecord.getType());
        values.put("remark",costRecord.getRemark());
        database.update("costrecord",values,"id = ?",new String[]{costRecord.getId()+""});

    }

    @Override
    public CostRecord findCostRecord(int id) {
        SQLiteDatabase database = sqLiteHelper.getWritableDatabase();
        Cursor cursor = database.query("costrecord",null,"id=?",new String[]{id+""},null,null,null);
        CostRecord costRecord = new CostRecord();
        if (cursor != null && cursor.moveToFirst()){
            do {
                double cost = cursor.getDouble(cursor.getColumnIndex("cost"));
                Date date = Date.valueOf(cursor.getString(cursor.getColumnIndex("date")));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));


                costRecord.setId(id);
                costRecord.setCost(cost);
                costRecord.setDate(date);
                costRecord.setType(type);
                costRecord.setRemark(remark);
            }while (cursor.moveToNext());
        }
        return costRecord;
    }

    @Override
    public List<CostRecord> findAllCostRecord() {
        SQLiteDatabase database = sqLiteHelper.getWritableDatabase();
        Cursor cursor = database.query("costrecord", null, null, null, null, null, null);
        List<CostRecord> costRecordList = new ArrayList<CostRecord>();

        if (cursor != null && cursor.moveToFirst()){
            do {

                CostRecord costRecord = new CostRecord();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                double cost = cursor.getDouble(cursor.getColumnIndex("cost"));

                /*String d = cursor.getString(cursor.getColumnIndex("data"));
                SimpleDateFormat format = new SimpleDateFormat("YY-MM-DD");*/
                //Date date = new Date(cursor.getColumnIndex("date"));
                Date date = Date.valueOf(cursor.getString(cursor.getColumnIndex("date")));
               // Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
                LogUtil.d("wnw",date.toString());

                String type = cursor.getString(cursor.getColumnIndex("type"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));

                costRecord.setId(id);
                costRecord.setCost(cost);
                costRecord.setDate(date);
                costRecord.setType(type);
                costRecord.setRemark(remark);

                costRecordList.add(costRecord);

            }while (cursor.moveToNext());
        }
        return costRecordList;
    }
}
