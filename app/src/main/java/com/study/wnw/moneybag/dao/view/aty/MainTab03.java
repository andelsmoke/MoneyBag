package com.study.wnw.moneybag.dao.view.aty;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.study.wnw.moneybag.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.PieChartValueFormatter;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


/**
 * Created by wangnainwen on 2016/3/1.
 */
public class MainTab03 extends Fragment implements AdapterView.OnItemSelectedListener{

    private Context mContext;

    private PieChartView chart;
    private PieChartData data;

    private boolean hasLabels = true;
    private boolean hasLabelsOutside = true;
    private boolean hasCenterCircle = false;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;

    private Spinner mSpinner;
    private TextView mTextView;

    /**
     * 定义要输出的种类有多少种
     * 定义得到到对应年的数据
     *
     * */

    private int typeCount = 7;
    private double costTab[];
    private String[] type = new String[]{"衣","食","住","行","用","健康","其他"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_tab_03, container, false);

        chart = (PieChartView) rootView.findViewById(R.id.pie_view);
        chart.setOnValueTouchListener(new ValueTouchListener());

        mSpinner =(Spinner)rootView.findViewById(R.id.spinner_sle_year1);
        mSpinner.setOnItemSelectedListener(this);
        mTextView = (TextView)rootView.findViewById(R.id.text_cost1);

        initSpinner();

        int selYear = mSpinner.getSelectedItemPosition()+2012;


        generateData(selYear);
        mTextView.setText("￥"+getAllCost());
        return rootView;
    }

    private void initSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.spinner_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(4,true);
    }

    /**全年的消费总额*/
    private double getAllCost(){
        double cost = 0;
        for (int i=0;i<typeCount;i++){
            cost += costTab[i];
        }
        return cost;
    }

    private void generateData(int year) {
        EveryTypeCost everyTypeCost = new EveryTypeCost(mContext);
        costTab = everyTypeCost.getEveryTypeCost(year);

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < typeCount; ++i) {
            SliceValue sliceValue = new SliceValue((float)costTab[i],ChartUtils.pickColor());
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);

        if(getAllCost() == 0.0){
            hasCenterCircle = true;
            hasCenterText1 = true;
        }else{
            hasCenterCircle = false;
            hasCenterText1 = false;
        }
        data.setHasCenterCircle(hasCenterCircle);

        if (isExploded) {
            data.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            data.setCenterText1("0");
            data.setCenterText1FontSize(200);
            data.setCenterText1Color(Color.GREEN);

            // Get roboto-italic font.
            /*Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");
            data.setCenterText1Typeface(tf);*/

            // Get font size from dimens.xml and convert it to sp(library uses sp values).
            /*data.setCenterText1FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen.pie_chart_text1_size)));*/
        }

        if (hasCenterText2) {
            /*data.setCenterText2("Charts (Roboto Italic)");

            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");

            data.setCenterText2Typeface(tf);
            data.setCenterText2FontSize(ChartUtils.px2sp(getResources().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen.pie_chart_text2_size)));*/
        }
        chart.setPieChartData(data);

    }
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(getActivity(), type[arcIndex]+":"+value.getValue()+"元", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        /**
         * 更新界面
         * */
        mSpinner.setSelection(i);
        generateData(i+2012);
        mTextView.setText("￥"+getAllCost());
    }
}
