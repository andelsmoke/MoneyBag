package com.study.wnw.moneybag.dao.view.mydialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.study.wnw.moneybag.R;
import com.study.wnw.moneybag.dao.view.listview.AddMainActivity;

/**
 * Created by wangnainwen on 2016/3/17.
 */
public class MyAlertDialog implements View.OnTouchListener{

    /**定义涉及到的Button*/
    private Button btn_sure_input;
    private Button btn_quit_input;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_0;
    private Button btn_clear;
    private Button btn_dot;
    private ImageButton imbtn_arrow;

    /**显示输入的信息*/
    private TextView input_text ;

    /**自定义一个AlertDialog*/
    private AlertDialog mAlertDialog = null;

    /**创建一个本地的listener*/
    private OnMyNumberSetListener onMyNumberSetListener;

    public interface OnMyNumberSetListener {
        void onNumberSet(String number);
    }

    public MyAlertDialog(Context context,OnMyNumberSetListener listener){
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.show();
        onMyNumberSetListener = listener;
        mAlertDialog.getWindow().setContentView(R.layout.numberpicker);
        initView();
    }
    private void initView(){
        btn_sure_input = (Button)mAlertDialog.findViewById(R.id.sure_input);
        btn_quit_input = (Button)mAlertDialog.findViewById(R.id.quit_input);
        btn_0 = (Button)mAlertDialog.findViewById(R.id.num_0);
        btn_1 = (Button)mAlertDialog.findViewById(R.id.num_1);
        btn_2 = (Button)mAlertDialog.findViewById(R.id.num_2);
        btn_3 = (Button)mAlertDialog.findViewById(R.id.num_3);
        btn_4 = (Button)mAlertDialog.findViewById(R.id.num_4);
        btn_5 = (Button)mAlertDialog.findViewById(R.id.num_5);
        btn_6 = (Button)mAlertDialog.findViewById(R.id.num_6);
        btn_7 = (Button)mAlertDialog.findViewById(R.id.num_7);
        btn_8 = (Button)mAlertDialog.findViewById(R.id.num_8);
        btn_9 = (Button)mAlertDialog.findViewById(R.id.num_9);
        btn_dot = (Button)mAlertDialog.findViewById(R.id.num_plot);
        btn_clear = (Button)mAlertDialog.findViewById(R.id.num_c);
        imbtn_arrow = (ImageButton)mAlertDialog.findViewById(R.id.btn_arrow);

        btn_sure_input.setOnTouchListener(this);
        btn_quit_input.setOnTouchListener(this);
        btn_0.setOnTouchListener(this);
        btn_1.setOnTouchListener(this);
        btn_2.setOnTouchListener(this);
        btn_3.setOnTouchListener(this);
        btn_4.setOnTouchListener(this);
        btn_5.setOnTouchListener(this);
        btn_6.setOnTouchListener(this);
        btn_7.setOnTouchListener(this);
        btn_8.setOnTouchListener(this);
        btn_9.setOnTouchListener(this);
        btn_dot.setOnTouchListener(this);
        btn_clear.setOnTouchListener(this);
        imbtn_arrow.setOnTouchListener(this);
        input_text = (TextView)mAlertDialog.findViewById(R.id.text_input);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()){
            case R.id.sure_input:

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_sure_input.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //return the number and dismiss the dialog
                    String number = input_text.getText().toString();
                    if(number.endsWith(".")){
                        number = number.substring(0,number.length()-1);
                    }
                    mAlertDialog.dismiss();
                    onMyNumberSetListener.onNumberSet(number);
                    btn_sure_input.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;

            case R.id.quit_input:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_quit_input.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    mAlertDialog.dismiss();
                    btn_quit_input.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;

            case R.id.num_0:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_0.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("0");
                    btn_0.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;
            case R.id.num_1:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_1.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("1");
                    btn_1.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;
            case R.id.num_2:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_2.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("2");
                    btn_2.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_3:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_3.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("3");
                    btn_3.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_4:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_4.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("4");
                    btn_4.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_5:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_5.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("5");
                    btn_5.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_6:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_6.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("6");
                    btn_6.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_7:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_7.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("7");
                    btn_7.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_8:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_8.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("8");
                    btn_8.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_9:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_9.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText("9");
                    btn_9.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_c:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_clear.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    deleteText();
                    btn_clear.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.num_plot:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    btn_dot.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    setText(".");
                    btn_dot.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
            case R.id.btn_arrow:
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    imbtn_arrow.setBackgroundColor(Color.GREEN);
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    deleteOne();
                    imbtn_arrow.setBackgroundColor(Color.parseColor("#00000000"));
                }

                break;
        }

        return false;
    }

    private void setText(String num){

        /**nowNumber 为当前TextView中显示的数字，newNumber为新的字符串*/
        String nowNumber = input_text.getText().toString();
        String newNumber = "";

        /**限制最多位数为12*/
        if(nowNumber.length()>=12){
            return;
        }

        /**限制为两位小数,dotSize为“.”的位置*/
        int dotSize = nowNumber.indexOf(".");
        if(dotSize > 0 && dotSize+2<nowNumber.length()){
            return;
        }

        /**输入的不是小数点*/
        if(!num.equals(".")){
            if(nowNumber.equals("")||nowNumber.equals("0")){
                newNumber = String.valueOf(num);
            }else{
                newNumber = nowNumber.concat(String.valueOf(num));
            }
        }else{
            if(nowNumber.contains(".")||nowNumber.equals("")){
                return;
            }else{
                newNumber = nowNumber.concat(String.valueOf(num));
            }
        }
        input_text.setText(newNumber);

    }

    /**这里是删除，直接删除掉所有*/
    private void deleteText(){
        input_text.setText("0");
    }

    /**这里是回删
     * 1. 记录输入前一次的nowString,
     * 2. 将当前的TextView设置成这次的nowString*/
    private void deleteOne(){
        String nowString = input_text.getText().toString();
        //如果不是0，那就直接取字串
        if(!nowString.equals("0")){
            nowString = nowString.substring(0,nowString.length()-1);
            if (nowString.length()==0){
                input_text.setText("0");
                return ;
            }
            input_text.setText(nowString);
        }
    }

}
