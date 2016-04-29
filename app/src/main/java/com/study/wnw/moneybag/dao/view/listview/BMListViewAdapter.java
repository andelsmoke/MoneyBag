package com.study.wnw.moneybag.dao.view.listview;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.wnw.moneybag.R;

import java.util.List;

/**
 * Created by wangnainwen on 2016/3/12.
 */
public class BMListViewAdapter extends BaseAdapter {

    /**定义,context,listview对象*/
    private Context mContext;
    private List<ListViewData> mLists = null;

    public BMListViewAdapter(Context context,List<ListViewData> lists){
        this.mContext = context;
        this.mLists = lists;
    }
    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int i) {
        return mLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_type,null);
        }
        ImageView picText = (ImageView)view.findViewById(R.id.lv_text_pic);
        TextView typeText = (TextView)view.findViewById(R.id.lv_text_type);
        ImageView imageView = (ImageView)view.findViewById(R.id.lv_imageview);
        picText.setImageResource(mLists.get(i).getmTextColor());
        typeText.setText(mLists.get(i).getmTextType());
        imageView.setImageResource(mLists.get(i).getImageResource());
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }
}
