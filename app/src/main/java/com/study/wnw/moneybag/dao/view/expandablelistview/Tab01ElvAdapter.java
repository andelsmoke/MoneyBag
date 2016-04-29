package com.study.wnw.moneybag.dao.view.expandablelistview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.study.wnw.moneybag.R;
import com.study.wnw.moneybag.dao.data.CostRecord;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by wnw on 16-4-27.
 */
public class Tab01ElvAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<ExpandableListViewParent> mParentList;
    private Map<ExpandableListViewParent,List<ExpandableListViewChild>> mChildren;

    private String colorTags[]={"#99cc00","#FFBB33","#FF4444","#33B5E5","#FF4080","#AA66CC","#ee6e6e"};

    private TextView typeView;
    private TextView dateView;
    private TextView remarkView;
    private TextView costView;

    private Handler mHandle;

    public Tab01ElvAdapter(Context context, List<ExpandableListViewParent> parents,
                           Map<ExpandableListViewParent,List<ExpandableListViewChild>> children){
        mContext = context;
        mParentList = parents;
        mChildren = children;
        mHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                notifyDataSetChanged();
                super.handleMessage(msg);
            }

        };

    }
    public void refresh(){
        mHandle.handleMessage(new Message());
    }

    public int getGroupCount() {

        return mParentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mChildren.get(mParentList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return mParentList.get(i);
    }
    @Override
    public Object getChild(int i, int i1) {
        return mChildren.get(mParentList.get(i)).get(i1);
    }
     @Override
     public long getGroupId(int i) {
         return i;
     }
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandablelistview_parent,null);
        }

        TextView pMonthView = (TextView)view.findViewById(R.id.expand_text_parent_month);
        TextView pCostView = (TextView)view.findViewById(R.id.expand_text_parent_cost);

        pMonthView.setText(mParentList.get(i).getMonth());
        pCostView.setText(mParentList.get(i).getCost());

        return view;
    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandablelistview_child,null);
        }

        typeView = (TextView)view.findViewById(R.id.child_text_left);
        dateView = (TextView)view.findViewById(R.id.child_text_center_date);
        remarkView = (TextView)view.findViewById(R.id.child_text_center_remark);
        costView = (TextView)view.findViewById(R.id.child_text_right_cost);


        dateView.setText(mChildren.get(mParentList.get(i)).get(i1).getDate().toString());
        remarkView.setText(mChildren.get(mParentList.get(i)).get(i1).getRemark());
        costView.setText("￥"+mChildren.get(mParentList.get(i)).get(i1).getCost());

        String type = mChildren.get(mParentList.get(i)).get(i1).getType();
        typeView.setBackgroundColor(Color.parseColor(getRandomColor()));
        setTypeView(type);
        return view;
    }
    private void setTypeView(String type){

        if (type.equals("衣")){
            typeView.setText("衣");

        } else if (type.equals("食")){
            typeView.setText("食");

        }else if (type.equals("住")){
            typeView.setText("住");

        }else if (type.equals("行")){
            typeView.setText("行");

        }else if (type.equals("用")){
            typeView.setText("用");

        }else if (type.equals("健康")){
            typeView.setText("健");

        }else{
            typeView.setText("其");
        }
    }

    private String getRandomColor(){
        String color;
        Random random = new Random();
        color = colorTags[random.nextInt(7)];
        return color;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
