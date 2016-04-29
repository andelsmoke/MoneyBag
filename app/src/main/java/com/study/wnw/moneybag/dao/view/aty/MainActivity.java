package com.study.wnw.moneybag.dao.view.aty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.study.wnw.moneybag.R;
import com.study.wnw.moneybag.dao.log.LogUtil;
import com.study.wnw.moneybag.dao.view.listview.AddMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    //定义变量
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    //定义底下四个按钮
    private LinearLayout mTabBtnWeixin;
    private LinearLayout mTabBtnFriend;
    private LinearLayout mTabBtnContact;
    private LinearLayout mTabBtnSeeting;

    /**定义在顶部的添加按钮，并在初始化的时候为添加事件处理*/
    private Button addCostButton;

    //定义底下的四个ImageButton
    private ImageView btn1;
    private ImageView btn2;
    private ImageView btn3;
    private ImageView btn4;

    //定义底下四个TextView
    private TextView mTabTewWeixin;
    private TextView mTabTewFriend;
    private TextView mTabTewContact;
    private TextView mTabTewSetting;

    //构建一个颜色
    String textColor = "#1d9413";

    private int mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);

        /**
         * 第一步，初始化组件，并将得到的Fragment添加到mFragments中
         * 第二步，构造Adapter数据
         * 第三步，将adapter加载到ViewPager中
         * 第四步，监听ViewPager的变化
         * */
        Intent intent = getIntent();
        mYear = intent.getIntExtra("year",2016);
        Toast.makeText(this,mYear+"",Toast.LENGTH_SHORT).show();
        initView();

        //构造Adapter数据
        mAdapter =  new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            /**overWrite this method is very important
             * this is to flush the every pager when the ListView data has changed
             * */
            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }
        };

        //将Adapter加载到布局ViewPager中
        mViewPager.setAdapter(mAdapter);

        //监听ViewPager的变化
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //定义当前在哪一个pager
            private int curentIndex;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /**1. 先让全部的图标变成正常状态，重置ViewPager中的状态为正常状态
                 * 2. 得到ViewPager后面Id,并且将该id的ViewPager设置为选中状态
                 * */
                resetTabBtn();

                switch (position) {
                    case 0:
                        ((ImageView) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                                .setImageResource(R.drawable.tab_weixin_pressed);
                        ((TextView) mTabTewWeixin.findViewById(R.id.text_bottom_weixin))
                                .setTextColor(Color.parseColor(textColor));
                        break;
                    case 1:
                        ((ImageView) mTabBtnFriend.findViewById(R.id.btn_tab_bottom_friend))
                                .setImageResource(R.drawable.tab_find_frd_pressed);
                        ((TextView) mTabTewFriend.findViewById(R.id.text_bottom_friend))
                                .setTextColor(Color.parseColor(textColor));
                        break;
                    case 2:
                        ((ImageView) mTabBtnContact.findViewById(R.id.btn_tab_bottom_contact))
                                .setImageResource(R.drawable.tab_address_pressed);
                        ((TextView) mTabTewContact.findViewById(R.id.text_bottom_contact))
                                .setTextColor(Color.parseColor(textColor));
                        break;
                    case 3:
                        ((ImageView) mTabBtnSeeting.findViewById(R.id.btn_tab_bottom_setting))
                                .setImageResource(R.drawable.tab_settings_pressed);
                        ((TextView) mTabTewSetting.findViewById(R.id.text_bottom_setting))
                                .setTextColor(Color.parseColor(textColor));
                        break;
                }
                curentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化View
    private void initView(){

        //初始化4个按钮
        mTabBtnWeixin = (LinearLayout)findViewById(R.id.id_tab_bottom_weixin);
        mTabBtnFriend = (LinearLayout)findViewById(R.id.id_tab_bottom_friend);
        mTabBtnContact = (LinearLayout)findViewById(R.id.id_tab_bottom_contact);
        mTabBtnSeeting = (LinearLayout)findViewById(R.id.id_tab_bottom_setting);

        //初始化四个imageView
        btn1 = (ImageView)findViewById(R.id.btn_tab_bottom_weixin);
        btn2 = (ImageView)findViewById(R.id.btn_tab_bottom_friend);
        btn3 = (ImageView)findViewById(R.id.btn_tab_bottom_contact);
        btn4 = (ImageView)findViewById(R.id.btn_tab_bottom_setting);

        //初始化四个TextView
        mTabTewWeixin = (TextView)findViewById(R.id.text_bottom_weixin);
        mTabTewFriend = (TextView)findViewById(R.id.text_bottom_friend);
        mTabTewContact = (TextView)findViewById(R.id.text_bottom_contact);
        mTabTewSetting = (TextView)findViewById(R.id.text_bottom_setting);

        mTabTewFriend.setTextColor(Color.GRAY);
        mTabTewContact.setTextColor(Color.GRAY);
        mTabTewSetting.setTextColor(Color.GRAY);


        //为他们添加事件处理
        mTabBtnWeixin.setOnClickListener(this);
        mTabBtnFriend.setOnClickListener(this);
        mTabBtnContact.setOnClickListener(this);
        mTabBtnSeeting.setOnClickListener(this);

        //新建四个Fragment对应的View对象
        MainTab01 tab01 = null;
        if(mYear != 0){
             tab01 = new MainTab01(mYear);
        }else {
            tab01 = new MainTab01(2016);
        }

        MainTab02 tab02 = new MainTab02();
        MainTab03 tab03 = new MainTab03();
        MainTab04 tab04 = new MainTab04();

        //将四个tab添加到fragments中
        mFragments.add(tab01);
        mFragments.add(tab02);
        mFragments.add(tab03);
        mFragments.add(tab04);


        /**初始化顶部的添加按钮，并添加事件处理*/
        addCostButton = (Button)findViewById(R.id.add_cost);
        addCostButton.setOnClickListener(this);
    }

    protected void resetTabBtn(){
        ((ImageView)mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                .setImageResource(R.drawable.tab_weixin_normal);
        ((ImageView)mTabBtnFriend.findViewById(R.id.btn_tab_bottom_friend))
                .setImageResource(R.drawable.tab_find_frd_normal);
        ((ImageView)mTabBtnContact.findViewById(R.id.btn_tab_bottom_contact))
                .setImageResource(R.drawable.tab_address_normal);
        ((ImageView)mTabBtnSeeting.findViewById(R.id.btn_tab_bottom_setting))
                .setImageResource(R.drawable.tab_settings_normal);

        ((TextView) mTabTewWeixin.findViewById(R.id.text_bottom_weixin))
                .setTextColor(Color.GRAY);
        ((TextView) mTabTewFriend.findViewById(R.id.text_bottom_friend))
                .setTextColor(Color.GRAY);
        ((TextView) mTabTewContact.findViewById(R.id.text_bottom_contact))
                .setTextColor(Color.GRAY);
        ((TextView) mTabTewSetting.findViewById(R.id.text_bottom_setting))
                .setTextColor(Color.GRAY);
    }

    /**为底部的按钮设置点击事件，得到点击事件的ID,该改变当前view,设置viewPager的当前页*/

    @Override
    public void onClick(View view) {

        //重置ImageButton变回正常状态
        switch (view.getId()){
            case R.id.id_tab_bottom_weixin:
                resetTabBtn();
                ((ImageView) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
                        .setImageResource(R.drawable.tab_weixin_pressed);

                ((TextView) mTabTewWeixin.findViewById(R.id.text_bottom_weixin))
                        .setTextColor(Color.parseColor(textColor));

                mViewPager.setCurrentItem(0,true);
                break;
            case R.id.id_tab_bottom_friend:
                resetTabBtn();
                ((ImageView) mTabBtnFriend.findViewById(R.id.btn_tab_bottom_friend))
                        .setImageResource(R.drawable.tab_find_frd_pressed);
                mViewPager.setCurrentItem(1, true);

                ((TextView) mTabTewFriend.findViewById(R.id.text_bottom_friend))
                        .setTextColor(Color.parseColor(textColor));
                break;
            case R.id.id_tab_bottom_contact:
                resetTabBtn();
                ((ImageView) mTabBtnContact.findViewById(R.id.btn_tab_bottom_contact))
                        .setImageResource(R.drawable.tab_address_pressed);

                ((TextView) mTabTewContact.findViewById(R.id.text_bottom_contact))
                        .setTextColor(Color.parseColor(textColor));
                mViewPager.setCurrentItem(2,true);
                break;
            case R.id.id_tab_bottom_setting:
                resetTabBtn();
                ((ImageView) mTabBtnSeeting.findViewById(R.id.btn_tab_bottom_setting))
                        .setImageResource(R.drawable.tab_settings_pressed);
                ((TextView) mTabTewSetting.findViewById(R.id.text_bottom_setting))
                        .setTextColor(Color.parseColor(textColor));
                mViewPager.setCurrentItem(3, true);
                break;
            /**顶部的添加消费按钮*/
            case R.id.add_cost:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddMainActivity.class);
                startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        mAdapter.notifyDataSetChanged();
        LogUtil.d("wnw","Aty onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtil.d("wnw","Aty onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        LogUtil.d("wnw","Aty onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        LogUtil.d("wnw","Aty onStop[");
        super.onStop();
    }

    public void flushPager(){
        mAdapter.notifyDataSetChanged();
    }
}
