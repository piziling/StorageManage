package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
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


/**
 * Created by heziwen on 2017/3/21.
 */

public class KcdjActivity extends Activity implements AbsListView.OnScrollListener,AdapterView.OnItemClickListener {

    private LoadListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcdj);

        listView = (LoadListView) findViewById(R.id.kcdj_list);
        datalist = new ArrayList<Map<String,Object>>();
        getData();
        if(datalist.size()>0){
            simp_adapter = new SimpleAdapter(this,datalist,R.layout.kcdj_item,
                    new String[]{"djlx","djbm","djrq","djzt"},
                    new int[]{R.id.tv_kcdj_djlx,R.id.tv_kcdj_djbm,R.id.tv_kcdj_djrq,R.id.tv_kcdj_djzt});
            //3.视图（ListView）加载适配器
            listView.setAdapter(simp_adapter);
            //加载监听器
            listView.setOnItemClickListener(this);
            listView.setOnScrollListener(this);
        }

    }

    private List<Map<String,Object>> getData(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        //ContentValues values = new ContentValues();
        Cursor cursor = db.query(SQLitConstant.TABLE_KCDJ,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            return null;
        }
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String djlx = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJLX));
            String djbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJBM));
            String djrq = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_ZDRQ));
            String djzt = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJZT));
            map.put("djlx",djlx);
            map.put("djbm","单据编号："+djbm);
            map.put("djrq","制单日期："+djrq);
            map.put("djzt","单据状态："+djzt);
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

    }
}
