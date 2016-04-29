package com.study.wnw.moneybag.dao.view.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.wnw.moneybag.R;

/**
 * Created by wnw on 16-4-28.
 */
public class PicYearAty extends Activity implements View.OnClickListener{

    private Button btn_save;
    private Button btn_quit;
    private Button btn_de;
    private Button btn_add;
    private TextView text_title;
    private TextView text_year;
    private ImageView dic_year_img;

    private int oldYear;
    //当前年份所在的index
    private int index;
    private int MAX_INDEX = 9;
    private int MIN_INDEX = 0;

    private String[] text = new String[]{
            "皇皇三十载，书剑两无成",
            "志士昔日短，愁人知夜长",
            "少壮不努力，老大徒伤悲",
            "一日无二晨，时间不重临",
            "节气不饶苗，岁月不饶人",
            "不饱食以终日，不弃功于寸阴",
            "年年岁岁花相似，岁岁年年人不同",
            "黑发不知勤学早，白首方悔读书迟",
            "花儿还有重开日，人生没有再少年",
            "莫等闲，白了少年头，空悲切"
    };

    private int img[] = new int[]{
            R.drawable.one,
            R.drawable.tow,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nigh,
            R.drawable.ten
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_year);
        initView();

        //获得传递过来的年份
        Intent intent = getIntent();
        oldYear = intent.getIntExtra("year",3);
        index =oldYear -2012;
        changeImg(index);
        changeText(index);
    }

    private void initView(){
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_quit = (Button)findViewById(R.id.btn_quit);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_de = (Button)findViewById(R.id.btn_de);
        text_year = (TextView)findViewById(R.id.text_year);
        text_title = (TextView)findViewById(R.id.text_title);
        dic_year_img = (ImageView)findViewById(R.id.pic_year_img);

        btn_save.setOnClickListener(this);
        btn_quit.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_de.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_save){

            //to flush the tab01
            int year =  Integer.parseInt(text_year.getText().toString());
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("year",year);
            startActivity(intent);
            finish();
        }else if(view.getId() == R.id.btn_quit){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("year",oldYear);
            startActivity(intent);
            finish();

        }else if(view.getId() == R.id.btn_add){

            //如过达到最大，就不再改变
            if(index < MAX_INDEX){
                index++;
                changeImg(index);
                changeText(index);
            }

        }else if (view.getId() == R.id.btn_de){

            //如过达到最大，就不再改变
            if(index > MIN_INDEX){
                index--;
                changeImg(index);
                changeText(index);
            }

        }
    }

    private void changeImg(int id){
        dic_year_img.setImageResource(img[id]);
    }
    private void changeText(int id){
        text_year.setText((id+2012)+"");
        text_title.setText(text[id]);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("year",oldYear);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
