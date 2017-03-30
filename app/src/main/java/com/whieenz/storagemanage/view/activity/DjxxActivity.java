package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.KcmxVO;
import com.whieenz.storagemanage.base.KctzVO;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.MyUntls;
import com.whieenz.storagemanage.utls.SQLitConstant;
import com.whieenz.storagemanage.view.myView.LoadListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/28.
 */

public class DjxxActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
    private LoadListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;
    private TextView djbh;
    private TextView djrq;
    private TextView time;
    private TextView djlx;
    private TextView ck;
    private TextView zje;
    private TextView zsl;
    private TextView zdr;
    private TextView dclr;
    private TextView bz;
    private TextView wldw;
    private String  djbm;
    private String  ywid;
    private String  ywfx;
    private int  sl;
    private List<KctzVO> kctzList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djxx);
        initView();
        getData();
        if (datalist.size() > 0) {
            simp_adapter = new SimpleAdapter(this, datalist, R.layout.djzbxx_item,
                    new String[]{"wzmc", "wzbm", "ggxh", "sltj","ywid"},
                    new int[]{R.id.tv_kcmx_wzmc, R.id.tv_kcmx_wzbm, R.id.tv_kcmx_ggxh, R.id.tv_kcmx_sltj,R.id.tv_kcmx_ywid});
            //3.视图（ListView）加载适配器
            listView.setAdapter(simp_adapter);
            //加载监听器
            listView.setOnItemClickListener(this);
            listView.setOnScrollListener(this);
        }
    }

    private void initView() {
        listView = (LoadListView) findViewById(R.id.djxx_list);
        datalist = new ArrayList<Map<String, Object>>();
        bz = (TextView) findViewById(R.id.djxx_layout_bz);
        djbh = (TextView) findViewById(R.id.djxx_layout_djbh);
        djrq = (TextView) findViewById(R.id.djxx_layout_djrq);
        djlx = (TextView) findViewById(R.id.djxx_layout_djlx);
        ck = (TextView) findViewById(R.id.djxx_layout_ck);
        zje = (TextView) findViewById(R.id.djxx_layout_zje);
        zdr = (TextView) findViewById(R.id.djxx_layout_zdr);
        dclr = (TextView) findViewById(R.id.djxx_layout_dclr);
        wldw = (TextView) findViewById(R.id.djxx_layout_wldw);
        zsl = (TextView) findViewById(R.id.djxx_layout_zsl);
        time = (TextView) findViewById(R.id.djxx_layout_time);

        Intent intent = getIntent();
        djbm = intent.getStringExtra("djbh");
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_KCDJ,null,SQLitConstant.KCDJ_DJBM+"=?",new String[]{djbm},null,null,null);
        while (cursor.moveToNext()){
            String djlx = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJLX));
            String djbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJBM));
            String djrq = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_ZDRQ));
            String djzt = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DJZT));
            ywid = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_YWID));
            String time = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_TIME));
            String zdr = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_ZDR));
            String zje = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_ZJE));
            String wldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_WLDW));
            String ck = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_CK));
            String dclr = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_DCLR));
            String bz = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_BZ));
            ywfx = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCDJ_YWFX));
            this.djbh.setText("单据编号："+djbm);
            this.djlx.setText("单据类型："+djlx);
            this.djrq.setText("单据日期："+djrq);
            this.time.setText("制单时间："+time);
            this.zje.setText("总金额："+zje+" RMB");
            this.zdr.setText("制单人："+zdr);
            this.dclr.setText("待处理人:"+dclr);
            this.wldw.setText("往来单位："+wldw);
            this.ck.setText("仓库："+ck);
            this.bz.setText("备注："+bz);

        }

    }

    private List<Map<String,Object>> getData(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        //ContentValues values = new ContentValues();
        Cursor cursor = db.query(SQLitConstant.TABLE_KCTZ,null,SQLitConstant.KCTZ_YWID+"=?",new String[]{ywid},null,null," TIME DESC");
        if (cursor.getCount()==0){
            return null;
        }
        kctzList = new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String,Object> map = new HashMap<String,Object>();
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZMC));
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZBM));
            String ggxh = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_GGXH));
            String sl   = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SL));
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_JLDW));
            map.put("wzmc",wzmc);
            map.put("wzbm",wzbm);
            map.put("ggxh","规格型号："+ggxh);
            map.put("sltj","数量： "+sl+" "+jldw);
            map.put("ywid",ywid);
            datalist.add(map);
            this.sl += Integer.valueOf(sl);
        }
        this.zsl.setText("总数量：  "+String.valueOf(sl));
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
