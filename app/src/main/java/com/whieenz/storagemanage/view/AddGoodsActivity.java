package com.whieenz.storagemanage.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;

import java.util.HashMap;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by heziwen on 2017/3/9.
 */

public class AddGoodsActivity extends Activity{

    private EditText wzbm;
    private EditText wzmc;
    private EditText ggxh;
    private EditText bzq;
    private EditText cd;
    private EditText dj;
    private EditText bz;
    private Button   jldw;
    private Button   wzlx;
    private Button   scrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        initView();
    }

    private void initView() {
        wzbm = (EditText) findViewById(R.id.goods_value_wzbm);
        wzmc = (EditText) findViewById(R.id.goods_value_wzmc);
        ggxh = (EditText) findViewById(R.id.goods_value_ggxh);
        jldw = (Button) findViewById(R.id.goods_value_jldw);
        wzlx = (Button) findViewById(R.id.goods_value_wzlx);
        bzq = (EditText) findViewById(R.id.goods_value_bzq);
        scrq = (Button) findViewById(R.id.goods_value_scrq);
        cd = (EditText) findViewById(R.id.goods_value_cd);
        dj = (EditText) findViewById(R.id.goods_value_dj);
        bz = (EditText) findViewById(R.id.goods_value_bz);

    }

    /**
     * 年月日时间学择器
     * @param view
     */
    public void onYearMonthDayPicker(View view) {
        final DatePicker picker = new DatePicker(this);
        picker.setTopPadding(2);
        picker.setRangeStart(2016, 8, 29);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(2017, 03, 10);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                scrq.setText(year + "-" + month + "-" + day);

            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 物资类型选择
     * @param view
     */
    public void onWzlxPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "产成品", "原材料", "其他"
        });
        picker.setCycleDisable(false);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                wzlx.setText(item);
            }
        });
        picker.show();
    }

    /**
     * 物资类型选择
     * @param view
     */
    public void onJldwPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "个", "件", "箱","千克","克","吨","条","件","瓶","打"
        });
        picker.setCycleDisable(false);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                jldw.setText(item);
            }
        });
        picker.show();
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_goods_save:
                Toast.makeText(this,getEdittext().toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public Map<String,Object> getEdittext() {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("wzbm",wzbm.getText().toString());
        map.put("wzmc",wzmc.getText().toString());
        map.put("ggxh",ggxh.getText().toString());
        map.put("wzlx",wzlx.getText().toString());
        map.put("jldw",jldw.getText().toString());
        map.put("bzq", bzq.getText().toString());
        map.put("scrq",scrq.getText().toString());
        map.put("cd",  cd.getText().toString());
        map.put("dj",  dj.getText().toString());
        map.put("bz",  bz.getText().toString());
        return  map;
    }

}
