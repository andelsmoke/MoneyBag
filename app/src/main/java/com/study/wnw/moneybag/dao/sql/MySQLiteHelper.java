package com.study.wnw.moneybag.dao.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 16-4-7.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    /**finish the sql of create table*/

    /**
     * Create yearCostRecordTable
     * */

    private String CREATE_YCR_TABLE = "create table yearcostrecord("
             +"id integer primary key autoincrement,"
             +"year integer not null,"
             +"yearcost real not null )";

    /**
     * Create monthCostTable
     */
    private String CREATE_MCR_TABLE = "create table monthcostrecord("
            +"id integer primary key autoincrement,"
            +"month integer not null,"
            +"monthcost real not null,"
            +"yearid integer constraint id_fk references CREATE_YCR_TABLE(id) on delete cascade)";

    /**
     * Create a CostTable,set the pf, fk for id
     * */

    private String CREATE_CR_TABLE = "create table costrecord("
            +"id integer primary key autoincrement,"
            +"cost real not null,"
            +"date integer not null,"
            +"type text not null,"
            +"remark text,"
            +"monthid integer constraint id_fk references CREATE_MCR_TABLE(id) on delete cascade)";



     public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


      //  sqLiteDatabase.execSQL(CREATE_YCR_TABLE);
        //sqLiteDatabase.execSQL(CREATE_MCR_TABLE);
        sqLiteDatabase.execSQL(CREATE_CR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        /**
         * to upgrade the database here
         * Note: no 'break' in the case
         * */
        switch (i){
            case 1:
                /**
                 * to do upgrade db
                 * */

            case 2:

            case 3:

            default:
        }
     }
}
