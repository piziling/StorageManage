package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;
import com.whieenz.storagemanage.view.myView.LoadListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.y;


/**
 * Created by heziwen on 2017/3/21.
 */

public class KctzActivity extends Activity implements AbsListView.OnScrollListener,AdapterView.OnItemClickListener {

    private LoadListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kctz);

        listView = (LoadListView) findViewById(R.id.kctz_list);
        datalist = new ArrayList<Map<String,Object>>();
        getData();
        if (datalist.size()>0){
            simp_adapter = new SimpleAdapter(this,datalist,R.layout.kctz_item,
                    new String[]{"ywmc","tzbm","wzmc","sltj","ckxx","time","ywid","wzbm"},
                    new int[]{R.id.tv_kctz_ywmc,R.id.tv_kctz_tzbm,R.id.tv_kctz_wzmc,R.id.tv_kctz_sltj,R.id.tv_kctz_ckxx,R.id.tv_kctz_time,R.id.tv_kctz_ywid,R.id.tv_kctz_wzbm});
            //3.视图（ListView）加载适配器
            listView.setAdapter(simp_adapter);
            //加载监听器
            listView.setOnItemClickListener(this);
            listView.setOnScrollListener(this);
        }

    }

    private List<Map<String,Object>> getData(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_KCTZ,null,SQLitConstant.KCTZ_DJZT+"=?",new String[]{"已完成"},null,null,null);
        if (cursor.getCount()==0){
            return null;
        }
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String ywmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_YWMC));
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZMC));
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZBM));
            String tzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_TZBM));
            String sl = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SL));
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_JLDW));
            String ck = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_CK));
            String time = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_TIME));
            String ywid = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_YWID));

            map.put("ywmc",ywmc);
            map.put("tzbm","台账编码："+tzbm);
            map.put("wzmc","物资名称："+wzmc);
            map.put("wzbm",wzbm);
            map.put("sltj","库存： "+sl+" "+jldw);
            map.put("ckxx","定位："+ck);
            map.put("time","时间："+time);
            map.put("ywid",ywid);
            datalist.add(map);
        }
        db.close();
        return  datalist;
    }



    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String,String> map= (HashMap<String,String>)listView.getItemAtPosition(i);
        String wzbm= map.get("wzbm");
        String ywid= map.get("ywid");
        Intent intent = new Intent(this, KcxxActivity.class);
        intent.putExtra("WZBM",wzbm);
        intent.putExtra("YWID",ywid);
        intent.putExtra("TAG","KCTZ");
        startActivity(intent);
    }
}
