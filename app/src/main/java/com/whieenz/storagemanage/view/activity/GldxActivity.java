package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;
import com.whieenz.storagemanage.view.myView.LoadListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



/**
 * Created by heziwen on 2017/3/23.
 */

public class GldxActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{

    private LoadListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList datalist;
    private TextView titleView;
    private MyApp myApp;
    private String tag;
    private View newView;
    private TextView name;
    private EditText nameValue;
    private EditText bzValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gldx);
        myApp = (MyApp) getApplication();
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        initView();

    }

    /**
     * 初始化界面
     */
    private void initView() {
        listView = (LoadListView) findViewById(R.id.gldx_list);
        titleView = (TextView) findViewById(R.id.tv_gldx_title);
        name = (TextView) findViewById(R.id.tv_gldx_dxmc);
        bzValue = (EditText) findViewById(R.id.et_gldx_bz);
        nameValue = (EditText) findViewById(R.id.et_gldx_dxmc);
        newView = findViewById(R.id.gldx_bottom_layout);
        datalist = new ArrayList();


        if (tag.equals(SQLitConstant.DXFL_WLDW)){
            titleView.setText("往来单位");
            name.setText("单位名称");
            datalist = myApp.getWldwArray();
        }else if (tag.equals(SQLitConstant.DXFL_JLDW)){
            titleView.setText("计量单位");
            name.setText("单位名称");
            datalist = myApp.getJldwArray();
        }else if (tag.equals(SQLitConstant.DXFL_RKLX)){
            titleView.setText("入库类型");
            name.setText("入库类型");
            datalist = myApp.getRklxArray();
        }else if (tag.equals(SQLitConstant.DXFL_CKLX)){
            titleView.setText("出库类型");
            name.setText("出库类型");
            datalist = myApp.getCklxArray();
        }else if (tag.equals(SQLitConstant.DXFL_WZFL)){
            titleView.setText("物资分类");
            name.setText("分类名称");
            datalist = myApp.getWzflArray();
        }
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datalist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    public void onAdd(View view){
        newView.setVisibility(View.VISIBLE);
    }
    public void onSave(View view){
        if(!checkInput()){
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);

        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_GLDX,null,SQLitConstant.GLDX_DXMC+"=?",
                new String[]{nameValue.getText().toString()},null,null,null);
        if (cursor.moveToFirst()){
            Toast.makeText(this,"已存在！",Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.GLDX_DXID,String.valueOf((int)(Math.random() * 10000)));
        values.put(SQLitConstant.GLDX_USER,myApp.getUserInfo().getNum());
        values.put(SQLitConstant.GLDX_DXMC,nameValue.getText().toString());
        values.put(SQLitConstant.GLDX_BZ,bzValue.getText().toString());
        values.put(SQLitConstant.GLDX_DXFL,tag);
        values.put(SQLitConstant.GLDX_TIME,time);
        long result = db.insert(SQLitConstant.TABLE_GLDX,null,values);
        db.close();
        if (result == -1){
            Toast.makeText(this,"添加失败!",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,"添加成功!",Toast.LENGTH_SHORT).show();
        myApp.initApp();
        datalist.add(nameValue.getText().toString());
        arrayAdapter.notifyDataSetChanged();
        newView.setVisibility(View.GONE);
    }




    private boolean checkInput() {
        if (nameValue.getText().toString().equals("")){
            Toast.makeText(this,"请输入"+titleView.getText().toString()+"名称！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (bzValue.getText().toString().equals("")){
            Toast.makeText(this,"请输入备注信息！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
