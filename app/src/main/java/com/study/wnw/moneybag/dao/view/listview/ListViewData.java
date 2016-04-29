package com.study.wnw.moneybag.dao.view.listview;

import android.content.Context;

/**
 * Created by wangnainwen on 2016/3/12.
 */
public class ListViewData {
    private int mTextColor;
    private String mTextType;
    private Context mContext;
    private int imageResource;

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int textColor) {
        this.mTextColor = textColor;
    }

    public String getmTextType() {
        return mTextType;
    }

    public void setmTextType(String textType) {
        this.mTextType = textType;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
