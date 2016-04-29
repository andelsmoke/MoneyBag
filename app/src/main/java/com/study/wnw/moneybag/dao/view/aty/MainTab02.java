package com.study.wnw.moneybag.dao.view.aty;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.study.wnw.moneybag.R;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by wangnainwen on 2016/3/1.
 */
public class MainTab02 extends Fragment implements AdapterView.OnItemSelectedListener{

    private Context mContext;
    private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;


    /**选择年的按钮*/
    private Spinner selYeatBtn;
    private TextView mTextView;

    /**组存放着返回的那一年的数据*/
    private double[] selNUmberTab = new double[numberOfPoints];

    /**最大年份和最小年份*/
    private int MAX_YEAR = 2021;
    private int MIN_YEAR = 2012;

    /**一年中所有的花费*/
    private double allCost;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;

    private EveryMonthCost mEveryMonthCost;
    private double MAX_COST;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.main_tab_02, container, false);

        /**
         * 1. define the view
         * 2. get the data
         * 3. set vector
         * 4. flush the view
         *
         * */

        chart = (LineChartView) rootView.findViewById(R.id.line_chart);
        chart.setOnValueTouchListener(new ValueTouchListener());

        mTextView = (TextView)rootView.findViewById(R.id.text_cost);
        selYeatBtn = (Spinner) rootView.findViewById(R.id.spinner_sle_year);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.spinner_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        selYeatBtn.setAdapter(adapter);
        selYeatBtn.setSelection(4,true);
        selYeatBtn.setOnItemSelectedListener(this);
        // Generate some random values.

        //to get the year from spinner
        int selYear = selYeatBtn.getSelectedItemPosition()+2012;
        generateValues(selYear);

        allCost = getAllCost();
        mTextView.setText("￥"+allCost);

        generateData();

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        resetViewport();



        return rootView;
    }

    private void generateValues(int year) {
        /*for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }*/
        /**得到那一年的消费数据*/
        mEveryMonthCost = new EveryMonthCost(mContext);
        selNUmberTab = mEveryMonthCost.getEveryMonthCost(year);

        /**获取最大的那个值作为坐标的top*/
        MAX_COST = selNUmberTab[0];
        for (int i=1;i<selNUmberTab.length;i++){
            if (selNUmberTab[i] > MAX_COST){
                MAX_COST = selNUmberTab[i];
            }
        }
    }

    private double getAllCost(){
        double cost = 0;
        for (int i=0;i<numberOfPoints;i++){
            cost += selNUmberTab[i];
        }
        return cost;
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = (int)MAX_COST;
        v.left = 1;
        v.right = numberOfPoints;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();

            /**我们从第一月分开始，所以0就设置为0*/
            values.add(0,new PointValue(0,0));

            for (int j = 0; j < numberOfPoints; j++) {
                values.add(new PointValue(j+1, (float)selNUmberTab[j]));
            }

            Line line = new Line(values);
            line.setColor(Color.GREEN);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            if (pointsHaveDifferentColor){
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setName("月份");
                axisY.setName("消费");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);
        chart.setInteractive(true);
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(mContext,(int)value.getX()+"月份花费"+value.getY()+"元", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public void onAttach(Context context) {
        mContext = context.getApplicationContext();
        super.onAttach(context);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        selYeatBtn.setSelection(i,true);

        //to flush the LineChartView and set Cost to Text
        generateValues(i+2012);
        generateData();
        resetViewport();
        mTextView.setText("￥"+getAllCost());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sel_year_btn:
                showDialog();
                break;
            default:
                break;
        }
    }

   private void showDialog(){

        *//**这里不能用context,因为我们的context是从context.getApplicationContext（）得到的*//*
        final Dialog dialog = new Dialog(MainActivity.getInstance());
        dialog.setTitle("年份选择器");
        dialog.setContentView(R.layout.numberpickerdialog);
        Button finishBtn = (Button)dialog.findViewById(R.id.num_picker_yes);

        *//**打开选择年的picker*//*
        final NumberPicker numberPicker = (NumberPicker)dialog.findViewById(R.id.number_picker_dialog);
        numberPicker.setMinValue(MIN_YEAR);
        numberPicker.setMaxValue(MAX_YEAR);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(this);
        numberPicker.setValue(Integer.parseInt(selYeatBtn.getText().toString()));


        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.num_picker_yes:
                        selYeatBtn.setText(String.valueOf(numberPicker.getValue()));
                        dialog.dismiss();

                        *//**在这里去重新生成折线图*//*

                        break;
                    default:

                        break;
                }
            }
        });
        dialog.show();
    }
    *//**i是旧的，i1是新的*//*
    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        if(numberPicker.getId() == R.id.number_picker_dialog){


            *//**
             * 在这里去观察数据的改变
             * *//*
        }
    }*/
}
