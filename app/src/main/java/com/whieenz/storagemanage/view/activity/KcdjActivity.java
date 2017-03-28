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
import android.widget.SimpleAdapter;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;
import com.whieenz.storagemanage.view.myView.LoadListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.imeExtractEnterAnimation;
import static android.R.attr.y;
import static android.content.ContentValues.TAG;


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
                    new String[]{"djlx","djbm","wzxx","zje","djzt","djrq"},
                    new int[]{R.id.tv_kcdj_djlx,R.id.tv_kcdj_djbm,R.id.tv_kcdj_wzxx,
                            R.id.tv_kcdj_zje,R.id.tv_kcdj_djzt,R.id.tv_kcdj_djrq});
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
            String djrq = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_TIME));
            String djzt = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJZT));
            String ywid = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_YWID));
            String zje = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_ZJE));
            String wzxx = "";
            Cursor kctzCursor = db.query(SQLitConstant.TABLE_KCTZ,null,"YWID=?",new String[]{ywid},null,null,null);
            int len = 0;
            while (kctzCursor.moveToNext()){
                if(len++<3){
                    wzxx += kctzCursor.getString(kctzCursor.getColumnIndex(SQLitConstant.KCTZ_WZMC))+"|";
                }
                if(len==3){
                    wzxx += "等";
                }
            }
            map.put("djlx",djlx);
            map.put("djbm",djbm);
            map.put("djrq","制单时间："+djrq);
            map.put("djzt","单据状态："+djzt);
            map.put("zje","总金额："+zje+"RMB");
            map.put("wzxx","物资信息："+wzxx);
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
        String djbm=map.get("djbm");
        Intent intent = new Intent(this, DjxxActivity.class);
        intent.putExtra("djbh",djbm);
        startActivity(intent);
    }
}
