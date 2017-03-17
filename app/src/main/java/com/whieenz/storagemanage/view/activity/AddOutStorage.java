package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.whieenz.storagemanage.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by heziwen on 2017/3/13.
 */

public class AddOutStorage extends Activity {
    private TextView djrq;
    private Button djlx;
    private Button wldw;
    private Button add;
    private Button ck;
    private EditText djbh;
    private EditText jbr;
    private EditText bz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_storage);
        initView();
    }

    private void initView() {
        djrq =(TextView) findViewById(R.id.out_value_djrq);
        djbh =(EditText) findViewById(R.id.out_value_djbh);
        jbr =(EditText) findViewById(R.id.out_value_jbr);
        bz =(EditText) findViewById(R.id.out_value_bz);
        ck = (Button) findViewById(R.id.out_value_ck);
        wldw = (Button) findViewById(R.id.out_value_wldw);
        djlx = (Button) findViewById(R.id.out_value_djlx);
        add = (Button) findViewById(R.id.out_add);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        djrq.setText(str);
    }

    /**
     *
     */
    public void onDjlxPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "采购入库", "产成品入库", "其它入库"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                djlx.setText(item);
            }
        });
        picker.show();
    }
    /**
     *
     */
    public void onCkPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "武汉仓库", "北京仓库", "上海仓库"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                ck.setText(item);
            }
        });
        picker.show();
    }
    /**
     *
     */
    public void onWldwPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "远光软件", "中国移动", "中国航天"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                wldw.setText(item);
            }
        });
        picker.show();
    }

    /**
     *
     */
    public void onAdd(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "远光软件", "中国移动", "中国航天"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                wldw.setText(item);
            }
        });
        picker.show();
    }
}
