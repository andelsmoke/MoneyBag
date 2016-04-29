package com.study.wnw.moneybag.dao.view.aty;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.study.wnw.moneybag.R;
import com.study.wnw.moneybag.dao.dao.CostRecordDao;
import com.study.wnw.moneybag.dao.data.CostRecord;
import com.study.wnw.moneybag.dao.imp.CostRecordDaoImpl;
import com.study.wnw.moneybag.dao.log.LogUtil;
import com.study.wnw.moneybag.dao.sql.MySQLiteHelper;
import com.study.wnw.moneybag.dao.view.expandablelistview.ExpandableListViewChild;
import com.study.wnw.moneybag.dao.view.expandablelistview.ExpandableListViewParent;
import com.study.wnw.moneybag.dao.view.expandablelistview.Tab01ElvAdapter;
import com.study.wnw.moneybag.dao.view.listview.AddMainActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-4-18.
 */
public class MainTab01 extends Fragment implements ExpandableListView.OnChildClickListener{



    private CostRecordDao mCostRecordDao;
    private MySQLiteHelper mSQLiteHelper;

    private Context mContext;
    //private Spinner mSpinner;
    public int selYear;
    private TextView mTextView;
    private LayoutInflater mInflater;
    private static ExpandableListView mExpandableListView;
    private List<CostRecord> mCostRecordList;
    private static Tab01ElvAdapter mLvAdapter;
    private double everyMonthCost[];
    private List<ExpandableListViewParent> mParentList;
    private Map<ExpandableListViewParent,List<ExpandableListViewChild>> children;
    private EveryMonthCost e;

    public MainTab01(){

    }

    public  MainTab01(int year){
        selYear = year;
    }

    private static MainTab01 instance;
    public static MainTab01 getInstance(){
        if(instance == null){
            instance = new MainTab01(2016);
        }
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /**通过Inflater从而初始化Fragment的总体布局，然后根据得到的view来初始化组件i，得到id */
        View view = inflater.inflate(R.layout.main_tab_01,container,false);
        mInflater = inflater;

        mSQLiteHelper = new MySQLiteHelper(mContext, "MoneyRecord.db", null, 1);
        mCostRecordDao = new CostRecordDaoImpl(mSQLiteHelper);

        mExpandableListView = (ExpandableListView)view.findViewById(R.id.main3);
        mExpandableListView.setOnChildClickListener(this);
        /**
         * ListView 3 steps:
         * 第一步，初始化ListView
         * 第二步，初始化数据
         * 第三步，加载Adapter到view中
         * 4. notify the data change
         * */

        setAdapterData();

        mTextView =(TextView)view.findViewById(R.id.text_cost02);
        mTextView.setText("￥"+getAllCost());
        return view;
    }

    private void setAdapterData(){

        initData(selYear);
        mLvAdapter = new Tab01ElvAdapter(mContext,mParentList,children);
        mExpandableListView.setAdapter(mLvAdapter);
    }

    private double getAllCost(){
        double cost = 0;
        for (int i =0;i<12;i++){
            cost += everyMonthCost[i];
        }
        return cost;
    }


    private void initData(int year){

        /**存放月份的数据*/
        int month [] = new int[12];
        int hasCount = 0;

        everyMonthCost = new double[12];
        /**在这里要对数据进行处理，得到两个数据集合*/
        e = new EveryMonthCost(mContext);
        everyMonthCost = e.getPerMonthCost(year);

        mParentList = new ArrayList<ExpandableListViewParent>();
        /**设置parent*/
        for (int i= everyMonthCost.length-1;i>=0;i--){
            if(everyMonthCost[i] != 0.0) {
                ExpandableListViewParent parent = new ExpandableListViewParent(mContext);
                parent.setMonth(getMonth(i + 1));
                parent.setCost("￥" + everyMonthCost[i]);
                mParentList.add(parent);
                month[hasCount] = i+1;
                hasCount ++;
            }
        }

        /**
         * 设置child
         * */

        children = new HashMap<ExpandableListViewParent, List<ExpandableListViewChild>>();
        for(int i=0;i<hasCount;i++){
            List<CostRecord> list = e.getSelYearAllRecord(year,month[i]);
            List<ExpandableListViewChild> listViewChildren = new ArrayList<ExpandableListViewChild>();

            for(int j =0;j<list.size();j++){
                ExpandableListViewChild child = new ExpandableListViewChild(mContext);
                CostRecord costRecord = list.get(j);

                child.setId(costRecord.getId());
                child.setCost(costRecord.getCost());
                child.setDate(costRecord.getDate());
                child.setType(costRecord.getType());
                child.setRemark(costRecord.getRemark());
                listViewChildren.add(child);
            }
            children.put(mParentList.get(i),listViewChildren);
        }
    }

    private String getMonth(int month){
        String monthStr = null;
        if(month==1){
            monthStr = "一月";
        } else if (month == 2){
            monthStr = "二月";
        }
        else if (month == 3){
            monthStr = "三月";
        }
        else if (month == 4){
            monthStr = "四月";
        }
        else if (month == 5){
            monthStr = "五月";
        } else if (month == 6){
            monthStr = "六月";
        } else if (month == 7){
            monthStr = "七月";
        } else if (month == 8){
            monthStr = "八月";
        } else if (month == 9){
            monthStr = "九月";
        } else if (month == 10){
            monthStr = "十月";
        } else if (month == 11){
            monthStr = "十一月";
        } else{
            monthStr = "十二月";
        }
        return monthStr;
    }

    @Override
    public void onStop() {
        LogUtil.d("wnwww","Stop");
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d("wnwww","onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("wnwww","onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("wnwww","onDeV");

    }

    @Override
    public void onResume() {
        LogUtil.d("wnwww","onResume");
        mLvAdapter.notifyDataSetChanged();
        mExpandableListView.invalidateViews();
        super.onResume();
    }

    @Override
    public void onStart() {
        LogUtil.d("wnwww","onStart");
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public int getSelYear(){
        if(selYear==0){
            selYear = 2016;
        }
        return selYear;
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        ExpandableListViewChild child = children.get(mParentList.get(i)).get(i1);
        Intent intent = new Intent(mContext, AddMainActivity.class);
        intent.putExtra("id",child.getId());
        intent.putExtra("cost",child.getCost());
        intent.putExtra("type", child.getType());
        intent.putExtra("date", child.getDate().toString());
        intent.putExtra("remark",child.getRemark());
        startActivity(intent);
       return true;
    }
}
