package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;


/**
 * Created by heziwen on 2017/3/29.
 */

public class KcxxActivity extends Activity {
    private TextView wzbmTv;
    private TextView wzmcTv;
    private TextView ggxhTv;
    private TextView wzflTv;
    private TextView sltjTv;
    private TextView ckxxTv;
    private TextView djTv;
    private TextView zjeTv;
    private TextView bzqTv;
    private TextView scrqTv;
    private TextView cdTv;
    private TextView sizeTv;
    private TextView titleTv;
    private String wzbm;
    private String ywid;
    private String ck;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcxx);
        wzbmTv = (TextView) findViewById(R.id.tv_kcxx_wzbm);
        wzmcTv = (TextView) findViewById(R.id.tv_kcxx_wzmc);
        ggxhTv = (TextView) findViewById(R.id.tv_kcxx_ggxh);
        wzflTv = (TextView) findViewById(R.id.tv_kcxx_wzlx);
        sltjTv = (TextView) findViewById(R.id.tv_kcxx_sltj);
        ckxxTv = (TextView) findViewById(R.id.tv_kcxx_ckxx);
        djTv = (TextView) findViewById(R.id.tv_kcxx_dj);
        zjeTv = (TextView) findViewById(R.id.tv_kcxx_zje);
        bzqTv = (TextView) findViewById(R.id.tv_kcxx_bzq);
        scrqTv = (TextView) findViewById(R.id.tv_kcxx_scrq);
        cdTv = (TextView) findViewById(R.id.tv_kcxx_cd);
        sizeTv = (TextView) findViewById(R.id.tv_kcxx_size);
        titleTv = (TextView) findViewById(R.id.tv_kcxx_title);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        wzbm = intent.getStringExtra("WZBM");
        ywid = intent.getStringExtra("YWID");
        tag = intent.getStringExtra("TAG");
        ck = intent.getStringExtra("CK");

        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = null;
        if(tag.equals("KCTZ")){
            titleTv.setText("台账明细");
            cursor = db.query(SQLitConstant.TABLE_KCTZ,null,
                    SQLitConstant.KCTZ_WZBM+"=? AND "+SQLitConstant.KCTZ_YWID+"=?",new String[]{wzbm,ywid},null,null,null);
        }else if(tag.equals("KCMX")){
            titleTv.setText("库存明细");
            cursor = db.query(SQLitConstant.TABLE_KCMX,null,
                    SQLitConstant.KCMX_WZBM+"=? AND "+SQLitConstant.KCMX_CK+"=?",new String[]{wzbm,ck},null,null,null);
        }

        while (cursor.moveToNext()){
            String wzbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZBM));
            String wzmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZMC));
            String ggxh = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_GGXH));
            String wzlx = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZLX));
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_JLDW));
            String sl = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SL));
            String size = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SIZE));
            String zje = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_ZJE));
            String dj = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_DJ));
            String ck = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_CK));
            String bzq = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_BZQ));
            String scrq = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SCRQ));
            String cd = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_CD));

            setText(wzbmTv,"物资编码： "+wzbm);
            setText(wzmcTv,"物资名称： "+wzmc);
            setText(wzflTv,"物资类型： "+wzlx);
            setText(ggxhTv,"规格型号： "+ggxh);
            setText(sltjTv,"数量统计： "+sl + " "+jldw);
            setText(ckxxTv,"所在仓库： "+ck);
            setText(zjeTv, "合计金额： "+zje+" RMB");
            setText(djTv,  "物资单价： "+dj+" RMB");
            setText(bzqTv, "保质期： "+bzq+" 天");
            setText(scrqTv,"生产日期： "+scrq);
            setText(cdTv,  "产地： "+cd);
            setText(sizeTv, "物资体积： "+size+" m³");
        }


    }

    private void setText(TextView view,String text){
        if(view != null&& text!=null ){
            view.setText(text);
        }
    }

}
