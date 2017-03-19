package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by heziwen on 2017/310.
 */

public class AddInStorageActivity extends Activity {
    private TextView djrq;
    private Button djlx;
    private Button wldw;
    private Button add;
    private Button ck;
    private EditText djbh;
    private EditText jbr;
    private EditText bz;
    private View firstBottom;
    private View resultBottom;
    private TextView  resultTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_storage);
        initView();
    }

    private void initView() {
        djrq =(TextView) findViewById(R.id.in_value_djrq);
        resultTv =(TextView) findViewById(R.id.tv_result);
        djbh =(EditText) findViewById(R.id.in_value_djbh);
        jbr =(EditText) findViewById(R.id.in_value_jbr);
        bz =(EditText) findViewById(R.id.in_value_bz);
        ck = (Button) findViewById(R.id.in_value_ck);
        wldw = (Button) findViewById(R.id.in_value_wldw);
        djlx = (Button) findViewById(R.id.in_value_djlx);
        add = (Button) findViewById(R.id.in_add);
        firstBottom =  findViewById(R.id.bottom_first_layout);
        resultBottom = findViewById(R.id.bottom_result_layout);

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
     *添加物资
     */
    public void onAdd(View view){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_GOODS,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            Toast.makeText(this,"当前系统没有物资信息，请先新增！",Toast.LENGTH_LONG).show();
            db.close();
            return;
        }
        db.close();
        Intent intent = new Intent(this,SelectGoodsActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    List list = bundle.getParcelableArrayList("result");
                    if(list==null){
                        return;
                    }
                    List<Map<String, String>> lists= (List<Map<String, String>>)list.get(0);
                    int sum = 0;
                    for (int i = 0; i < lists.size() ; i++) {
                        String str = lists.get(i).get("num");
                        sum += Integer.valueOf(str);
                    }
                    if (lists!=null&&lists.size()>0){
                        resultBottom.setVisibility(View.VISIBLE);
                        firstBottom.setVisibility(View.GONE);
                        resultTv.setText("合计：已选 "+lists.size()+" 种，总数量 " + sum );
                    }
                }
                break;
            default:
        }
    }
}
