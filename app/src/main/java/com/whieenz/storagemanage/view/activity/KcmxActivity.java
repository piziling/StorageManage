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


/**
 * Created by heziwen on 2017/3/21.
 */

public class KcmxActivity extends Activity implements AbsListView.OnScrollListener,AdapterView.OnItemClickListener {

    private LoadListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcmx);

        listView = (LoadListView) findViewById(R.id.kcmx_list);
        datalist = new ArrayList<Map<String,Object>>();
        getData();
        if (datalist.size()>0){
            simp_adapter = new SimpleAdapter(this,datalist,R.layout.kcmx_item,
                    new String[]{"wzmc","wzbm","ggxh","sltj","zje","ckxx"},
                    new int[]{R.id.tv_kcmx_wzmc,R.id.tv_kcmx_wzbm,R.id.tv_kcmx_ggxh,R.id.tv_kcmx_sltj,R.id.tv_kcmx_zje,R.id.tv_kcmx_ckxx});
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
        Cursor cursor = db.query(SQLitConstant.TABLE_KCMX,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            return null;
        }
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZMC));
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZBM));
            String ggxh = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_GGXH));
            String sl = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_SL));
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_JLDW));
            String ck = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_CK));
            String zje = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_ZJE));
            map.put("wzmc",wzmc);
            map.put("wzbm",wzbm);
            map.put("ggxh","规格型号："+ggxh);
            map.put("sltj","库存： "+sl+" "+jldw);
            map.put("zje","总金额： "+zje+"RMB");
            map.put("ckxx",ck);
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
        String ck= map.get("ckxx");
        Intent intent = new Intent(this, KcxxActivity.class);
        intent.putExtra("WZBM",wzbm);
        intent.putExtra("CK",ck);
        intent.putExtra("TAG","KCMX");
        startActivity(intent);
    }
}
