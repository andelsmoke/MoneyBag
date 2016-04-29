package com.study.wnw.moneybag.dao.view.listview;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.study.wnw.moneybag.R;
import com.study.wnw.moneybag.dao.dao.CostRecordDao;
import com.study.wnw.moneybag.dao.data.CostRecord;
import com.study.wnw.moneybag.dao.imp.CostRecordDaoImpl;
import com.study.wnw.moneybag.dao.sql.MySQLiteHelper;
import com.study.wnw.moneybag.dao.view.mydialog.MyAlertDialog;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wangnainwen on 2016/3/10.
 */
public class AddMainActivity extends Activity implements AdapterView.OnItemClickListener ,
        View.OnClickListener{

    /**定义顶部两个按钮，一个是保存，一个是取消，并为这这两个按钮添加点击事件处理*/
    private Button saveButton;
    private Button quitButton;

    /**定义选择日期,选择话费的按钮*/
    private Button selectDate;
    private Button selectCost;

    /**定义获取备注的remark*/
    private EditText Et_remark;

    /**定义一个Dialog,当用户选中cost_sel按钮时，弹出该NumberDialog*/
    private MyAlertDialog myDialog = null;

    /**定义颜色的种类*/
    private int colors[] ={
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7};

    /**定义花费的类型*/
    private String types[] = {"衣","食","住","行","用","健康","其他"};

    /**第一单选按钮的图片,包括未选中和选中的图片*/
    private int imageResource_nol = R.drawable.radio_button_off;
    private int imageResource_sel = R.drawable.radio_button_on;

    /**定义ListView*/
    private ListView  mListView = null;

    /**定义List数据*/
    private List<ListViewData> lists = null;

    /**定义Adapter*/
    BMListViewAdapter bmListViewAdapter = null;

    /**定义要加载ListViewDate的数组*/
    ListViewData listViewData[];

    /**定义ListView中当前选中Item的Id,默认是第一个*/
    private int currentItemId;

    /**create a helper to create the db*/
    private MySQLiteHelper dbHelper;

    /** define a id which update*/
    private int mId;

    /**define a tag add or update: add : true  update : false*/
    private boolean addOrUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_main);



        Et_remark = (EditText)findViewById(R.id.edit_remark);

        /**初始化保存，取消，选择日期,选择花费四个按钮，并添加事件处理*/
        saveButton = (Button)findViewById(R.id.lv_btn_save);
        quitButton = (Button)findViewById(R.id.lv_btn_quit);
        selectDate = (Button)findViewById(R.id.picker_date);
        selectCost = (Button)findViewById(R.id.cost_sel);
        saveButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
        selectDate.setOnClickListener(this);
        selectCost.setOnClickListener(this);



        /**给selectDate设默认的值,为今天的日期*/
        Calendar currentCalendar = Calendar.getInstance();
        int selYear = currentCalendar.get(Calendar.YEAR);
        int selMonth = currentCalendar.get(Calendar.MONTH)+1;
        int selDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        selectDate.setText(selYear + "-" + selMonth + "-" + selDay);

        /**listView的步骤
         * 第一步：初始化ListView
         * 第二步：初始化数据集，Adapter
         * 第三步：将Adapter加载到ListView中
         * */
        mListView = (ListView)findViewById(R.id.main_list);
        mListView.setOnItemClickListener(this);
        initData();
        mListView.setAdapter(bmListViewAdapter);

        Intent intent = getIntent();
        mId = intent.getIntExtra("id",-1);
        if(mId != -1){
            quitButton.setText("删除");
            selectCost.setText(intent.getDoubleExtra("cost",0.0)+"");
            selectDate.setText(intent.getStringExtra("date"));
            Et_remark.setText(intent.getStringExtra("remark"));
            String type = intent.getStringExtra("type");
            if (type.equals("衣")){
                resetCurrentItem(0);
            } else if (type.equals("食")){
                resetCurrentItem(1);
            }else if (type.equals("住")){
                resetCurrentItem(2);
            }else if (type.equals("行")){
                resetCurrentItem(3);
            }else if (type.equals("用")){
                resetCurrentItem(4);
            }else if (type.equals("健康")){
                resetCurrentItem(5);
            }else{
                resetCurrentItem(6);
            }
            addOrUpdate = false;
        }
    }

    private void initData(){

        dbHelper = new MySQLiteHelper(AddMainActivity.this,"MoneyRecord.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        lists = new ArrayList<ListViewData>();
        bmListViewAdapter = new BMListViewAdapter(this,lists);
        listViewData = new ListViewData[types.length];

        for(int i =0;i<types.length;i++){
            listViewData[i] = new ListViewData();
            listViewData[i].setmTextColor(colors[i]);
            listViewData[i].setmTextType(types[i]);
            /**默认选中第一个，所以设置第一个的图标为选中状态，并且设置当前选中的ID == 0*/
            if(i == 0){
                listViewData[i].setImageResource(imageResource_sel);
                currentItemId = i;
            }
            lists.add(listViewData[i]);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        /**如果选中某一个，有以下步骤：
         * 1. 首先设置上次选中这个的图标为正常状态
         * 2. 设置当前选中的Item状态为选中状态
         * 3. 设置当前currentItemId*/
        switch (i){
            case 0:
                resetCurrentItem(i);
                break;
            case 1:
                resetCurrentItem(i);
                break;
            case 2:
                resetCurrentItem(i);
                break;
            case 3:
                resetCurrentItem(i);
                break;
            case 4:
                resetCurrentItem(i);
                break;
            case 5:
                resetCurrentItem(i);
                break;
            case 6:
                resetCurrentItem(i);
                break;
        }
        bmListViewAdapter.notifyDataSetChanged();
    }
    /**重新设置listView中当前选中的状态和更改currentItemId
    *如果选中某一个，有以下步骤：
     * 1. 首先设置上次选中这个的图标为正常状态
     * 2. 设置当前选中的Item状态为选中状态
     * 3. 设置当前currentItemId*/

    private void resetCurrentItem(int i ){
        listViewData[currentItemId].setImageResource(imageResource_nol);
        listViewData[i].setImageResource(imageResource_sel);
        currentItemId = i;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lv_btn_save:
                /**to save the data to database and flush the view*/
                saveData();
                break;
            case R.id.lv_btn_quit:
                /**如果点击了取消按钮，则返回主界面，finish()即可
                 * 如果是删除，执行删除功能*/
                if(mId != -1){
                    CostRecordDao costRecordDao = new CostRecordDaoImpl(dbHelper);
                    costRecordDao.deleteCostRecord(mId);
                }
                this.finish();
                break;
            case R.id.picker_date:
               /**在这里弹出DatePicker,并将选择的日期显示在button上*/
                Toast.makeText(AddMainActivity.this,"date",Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        selectDate.setText(i+"-"+(i1+1)+"-"+i2);

                        /**设置当前选中年月日*/
                    }
                };
                new DatePickerDialog(AddMainActivity.this,dateSetListener,calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.cost_sel:
                showNumberDialog();
                break;
        }
    }

    /**创建myAlertDialog对象，显示NumberPicker*/
    private void showNumberDialog(){
        myDialog = new MyAlertDialog(AddMainActivity.this,listener);
    }

    private MyAlertDialog.OnMyNumberSetListener listener = new MyAlertDialog.OnMyNumberSetListener() {
        @Override
        public void onNumberSet(String number) {
            selectCost.setText(number);
        }
    };

    private void saveData(){

        if ((selectCost.getText().toString()).equals("0.00")
                || (selectCost.getText().toString()).equals("0.0")
                ||(selectCost.getText().toString()).equals("0")){
            Toast.makeText(AddMainActivity.this,"花费不能为0",Toast.LENGTH_SHORT).show();
            return;
        }else{
            double cost = Double.valueOf(selectCost.getText().toString());
            String type = types[currentItemId];
            Date date = Date.valueOf(selectDate.getText().toString());
            String remark = Et_remark.getText().toString();

            CostRecord costRecord = new CostRecord(cost,type,date,remark);
            CostRecordDao costRecordDao = new CostRecordDaoImpl(dbHelper);

            if(addOrUpdate){
                costRecordDao.insertCostRecord(costRecord);
            }else {
                costRecord.setId(mId);
                costRecordDao.updateCostRecord(costRecord);
            }

            finish();

            //int year = selYear ;
            //int month = selMonth;
            //LogUtil.d("wnw","年月："+year+" "+month+"  "+date+" "+cost +" "+type+" "+remark);




           /* YearCostRecordDao yearCostRecordDao = new YearCostRecordImpl(dbHelper);
            CostRecordDao costRecordDao = new CostRecordDaoImpl(dbHelper);
            MonthCostRecordDao monthCostRecordDao = new MonthCostRecordImpl(dbHelper);


            *//**//**如果年份这里没有，先增加年份和对应的月份*//**//*
            List<YearCostRecord> list1 = yearCostRecordDao.findYearCostRecordByMonth(selYear);
            if(list1.isEmpty()){
                YearCostRecord yearCostRecord = new YearCostRecord();
                yearCostRecord.setYear(year);
                yearCostRecord.setYearCost(cost);
                yearCostRecordDao.addYearCostRecord(yearCostRecord);
            }else {
                YearCostRecord yearCostRecord = new YearCostRecord();
                yearCostRecord.setYear(year);
                yearCostRecord.setYearCost(list1.get(0).getYearCost()+cost);
                yearCostRecordDao.updateYearCostRecord(yearCostRecord);
            }

            *//**//**如果对应某年的月份这里没有，先在增加月份，如果有了，就在当前月份插入一条数据*//**//*
            *//**//**重新查找到对应年份的id*//**//*
            List<YearCostRecord> yearList = yearCostRecordDao.findYearCostRecordByMonth(selYear);
            int yearId = yearList.get(0).getId();

            List<MonthCostRecord> list =monthCostRecordDao.findMonthCostRecordByMonth(yearId,selMonth);
            if(list.isEmpty()){
                MonthCostRecord monthCostRecord = new MonthCostRecord();
                monthCostRecord.setMonth(month);
                monthCostRecord.setMonthCost(cost);
                monthCostRecord.setYearid(yearId);
                monthCostRecordDao.addMonthCostRecord(monthCostRecord);
            }else{
                MonthCostRecord monthCostRecord = new MonthCostRecord();
                monthCostRecord.setMonth(list.get(0).getMonth());
                monthCostRecord.setMonthCost(list.get(0).getMonthCost() + cost);
                monthCostRecord.setYearid(yearId);
                monthCostRecordDao.updateMonthCostRecord(monthCostRecord);

            }*//*

           *//**插入costRecord*//*
            List<MonthCostRecord> monthList = monthCostRecordDao.findMonthCostRecordByMonth(yearId,month);
            int monthId = monthList.get(0).getId();
            CostRecord costRecord = new CostRecord(cost,type,date,remark,monthId);
            costRecordDao.insertCostRecord(costRecord);
            Toast.makeText(AddMainActivity.this,"save",Toast.LENGTH_SHORT).show();
            finish();*/
        }
    }
}
