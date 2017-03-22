package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SGAdapter;
import com.whieenz.storagemanage.utls.SQLitConstant;
import com.whieenz.storagemanage.view.myView.LoadListView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;
import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/17.
 */

public class SelectGoodsActivity extends Activity implements SGAdapter.SGAdapterCallback,AbsListView.OnScrollListener,AdapterView.OnItemClickListener{

    private LoadListView listView;
    private SGAdapter simp_adapter;
    private List<Map<String,Object>> datalist;
    private String tag;
    private String ckName;



    private TextView selectInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_goods);
        listView = (LoadListView) findViewById(R.id.select_loadlist);
        selectInfo = (TextView) findViewById(R.id.tv_select_info);
        datalist = new ArrayList<Map<String,Object>>();
        //获取上一个界面传递的值，判断操作类型
        Intent intent = getIntent();
        ckName = intent.getStringExtra("ck");
        tag = intent.getStringExtra("tag");

        getData();

        if(datalist.size()>0){
            simp_adapter = new SGAdapter(this,datalist,R.layout.goods_item,new String[]{"wzmc","wzbm","ggxh","zkc"},new int[]{R.id.tv_item_wzmc,R.id.tv_item_wzbm,R.id.tv_item_ggxh,R.id.tv_item_zkc},this);
            //3.视图（ListView）加载适配器
            listView.setAdapter(simp_adapter);
            //加载监听器
            listView.setOnItemClickListener(this);
            listView.setOnScrollListener(this);
        }

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

    private List<Map<String,Object>> getData(){

        if(tag.equals("RK")){
            getRkInfo();
        }else if (tag.equals("CK")){
            getCkInfo();
        }
        return  datalist;
    }

    /**
     * 入库
     * @return
     */
    private boolean getRkInfo() {
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        //ContentValues values = new ContentValues();
        Cursor cursor = db.query(SQLitConstant.TABLE_GOODS,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            return true;
        }
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_WZBM));
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_WZMC));
            String ggxh = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_GGXH));
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_JLDW));
            String sl= "";
            Cursor kcmxCursor = db.query(SQLitConstant.TABLE_KCMX,null,"WZBM=? AND CK =?",new String[]{wzbm,ckName},null,null,null);

            while (kcmxCursor.moveToNext()){
                sl = kcmxCursor.getString(kcmxCursor.getColumnIndex(SQLitConstant.KCMX_SL));
            }
            if(sl.equals("")){
                sl = "0";
            }
            map.put("wzbm",wzbm);
            map.put("wzmc",wzmc);
            map.put("ggxh","规格型号："+ggxh);
            map.put("zkc","本库存量："+sl+" "+jldw);
            datalist.add(map);
        }
        db.close();
        return false;
    }

    /**
     * 出库
     * @return
     */
    private boolean getCkInfo() {
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        //ContentValues values = new ContentValues();
        Cursor cursor = db.query(SQLitConstant.TABLE_KCMX,null,"CK=?",new String[]{ckName},null,null,null);
        if (cursor.getCount()==0){
            return true;
        }
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZBM));
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZMC));
            String ggxh = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_GGXH));
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_JLDW));
            String sl = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_SL));
            map.put("wzbm",wzbm);
            map.put("wzmc",wzmc);
            map.put("ggxh","规格型号："+ggxh);
            map.put("zkc","总库存："+sl+" "+jldw);
            datalist.add(map);
        }
        db.close();
        return false;
    }

    /***
     * 确定按钮响应事件
     * @param view
     */
    public void onYes(View view){
        int nums[] = simp_adapter.getNums();
        ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0){
                HashMap<String,String> map=(HashMap<String,String>)listView.getItemAtPosition(i);
                map.put("num",String.valueOf(nums[i]));
                list.add(map);
            }
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        ArrayList bundlelist = new ArrayList();
        bundlelist.add(list);
        bundle.putParcelableArrayList("result",bundlelist);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();

    }

    /**
     * 清除按钮相应事件
     * @param view
     */
    public void onDelete(View view){
        simp_adapter.initNums();
        simp_adapter.notifyDataSetChanged();
        selectInfo.setText("");
    }



    /**
     * 接口方法，响应ListView按钮点击事件
     * @param view
     */
    @Override
    public void click(View view) {
        String  info =  "已选"+simp_adapter.getSelect()+"种共计"+simp_adapter.getSelects()+"件";
        selectInfo.setText(info);
    }

}
