package com.study.wnw.moneybag.dao.view.aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.wnw.moneybag.R;

/**
 * Created by wangnainwen on 2016/3/1.
 */
public class MainTab04 extends Fragment implements View.OnClickListener{
    private Context mContext;
    private Button picYear;
    private TextView disImg;
    private ImageView mImageView;

    private EveryTypeCost mEveryTypeCost;
    private double everyTypeCost[];

    private int year;

    private double MAX_COST;

    private String text[]=new String[]{
            "剁手剁手",
            "你除了吃就知道吃",
            "住这里，还怕老公不回家？",
            "你的出行费用赶上了开赛车",
            "看着就好贤惠",
            "嘿，你该吃药了",
            "它在未来等你"
    };

    private int img[] = new int[]{
            R.drawable.yi,
            R.drawable.chi,
            R.drawable.house,
            R.drawable.zixingche,
            R.drawable.jujia,
            R.drawable.yao,
            R.drawable.car
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_04,null);

        picYear = (Button)view.findViewById(R.id.picker_year);
        picYear.setOnClickListener(this);

        disImg = (TextView)view.findViewById(R.id.type_text);
        mImageView = (ImageView)view.findViewById(R.id.dis_img);

        initView();
        return view;
    }

    public void initView(){
        MainTab01 mainTab01 = MainTab01.getInstance();
        year = mainTab01.getSelYear();
        setContent(getMAX_COST_Index());
        setImg(getMAX_COST_Index());
    }

    private int getMAX_COST_Index(){
        int index = 0;
        mEveryTypeCost = new EveryTypeCost(mContext);
        everyTypeCost = mEveryTypeCost.getEveryTypeCost(year);
        MAX_COST = everyTypeCost[0];
        for (int i = 1;i<everyTypeCost.length;i++){
            if(MAX_COST<everyTypeCost[i]){
                MAX_COST = everyTypeCost[i];
                index = i;
            }
        }
        return index;
    }

    private void setContent(int index){
        disImg.setText(text[index]);
    }
    private void setImg(int index){
        mImageView.setImageResource(img[index]);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.picker_year){
            //把当前选中的年份传递过去
            Intent intent = new Intent(mContext,PicYearAty.class);
            intent.putExtra("year",year);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }
}
