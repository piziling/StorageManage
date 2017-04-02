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
 * Created by heziwen on 2017/4/1.
 */

public class WzxxActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private LoadListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wzxx);
        listView = (LoadListView) findViewById(R.id.wzxx_loadlist);
        datalist = new ArrayList<Map<String,Object>>();
        getData();
        if (datalist.size() > 0) {
            simp_adapter = new SimpleAdapter(this,datalist,R.layout.goods_item,new String[]{"wzmc","wzbm","ggxh","wzlx","time"},
                    new int[]{R.id.tv_goods_wzmc,R.id.tv_goods_wzbm,R.id.tv_goods_ggxh,R.id.tv_goods_wzlx,R.id.tv_goods_time});
            //3.视图（ListView）加载适配器
            listView.setAdapter(simp_adapter);
            //加载监听器
            listView.setOnItemClickListener(this);
            listView.setOnScrollListener(this);
        }

    }


    private boolean getData() {
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_GOODS,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            return true;
        }
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_WZBM));
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_WZMC));
            String ggxh = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_GGXH));
            String time = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_TIME));
            String wzlx = cursor.getString(cursor.getColumnIndex(SQLitConstant.GOODS_WZLX));

            map.put("wzbm",wzbm);
            map.put("wzmc",wzmc);
            map.put("ggxh","规格型号："+ggxh);
            map.put("wzlx","物资分类："+wzlx);
            map.put("time",time);
            datalist.add(map);
        }
        db.close();
        return false;
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
