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
 * Created by heziwen on 2017/3/27.
 */

public class DjshActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
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
    private View shenpiLayout;
    private boolean isYes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_djsp);
        initView();
        getData();
        if (datalist.size() > 0) {
            simp_adapter = new SimpleAdapter(this, datalist, R.layout.djzbxx_item,
                    new String[]{"wzmc", "wzbm", "ggxh", "sltj","ywid"},
                    new int[]{R.id.tv_kcmx_wzmc, R.id.tv_kcmx_wzbm, R.id.tv_kcmx_ggxh, R.id.tv_kcmx_sltj, R.id.tv_kcmx_ywid});
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
        shenpiLayout = findViewById(R.id.djxx_shenpi_layout);

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
        Cursor cursor = db.query(SQLitConstant.TABLE_KCTZ,null,SQLitConstant.KCTZ_YWID+"=?",new String[]{ywid},null,null,null);
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
            String ck   = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_CK));
            String bzq  = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_BZQ));
            String scrq = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SCRQ));
            String cd   = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_CD));
            String size = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_SIZE));
            String dj   = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_DJ));
            String wzlx = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_WZLX));
            String bz   = cursor.getString(cursor.getColumnIndex(SQLitConstant.KCTZ_BZ));
            map.put("wzmc",wzmc);
            map.put("wzbm",wzbm);
            map.put("ggxh","规格型号："+ggxh);
            map.put("sltj","数量： "+sl+" "+jldw);
            map.put("ywid",ywid);
            datalist.add(map);
            KctzVO kctzVO = new KctzVO();
            kctzVO.setWzmc(wzmc);
            kctzVO.setWzbm(wzbm);
            kctzVO.setWzlx(wzlx);
            kctzVO.setGgxh(ggxh);
            kctzVO.setSl(Integer.valueOf(sl));
            kctzVO.setJldw(jldw);
            kctzVO.setCk(ck);
            kctzVO.setCd(cd);
            kctzVO.setBzq(bzq);
            kctzVO.setBz(bz);
            kctzVO.setScrq(scrq);
            kctzVO.setSize(Double.valueOf(size));
            kctzVO.setDj(Double.valueOf(dj));
            kctzList.add(kctzVO);
            this.sl += Integer.valueOf(sl);
        }
        this.zsl.setText("总数量：  "+String.valueOf(sl));
        db.close();
        return  datalist;
    }

    /**
     * 审核通过
     * @param view
     */
    public void doYes(View view){
        /**
         * 1.更新KCDJ  DJZT 已完成  更新 Time  DJBM  YWID
         * 2.更新KCTZ  DJZT 已完成  更新 time  YWID
         * 3.更新KCMX  WZBM
         */
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCDJ_DJZT,"已完成");
        values.put(SQLitConstant.KCDJ_TIME, MyUntls.getNowTime());
        // 1.更新KCDJ  DJZT 已完成  更新 Time  DJBM  YWID
        int djResult = db.update(SQLitConstant.TABLE_KCDJ,values,SQLitConstant.KCDJ_DJBM+"=?",new String[]{djbm});
        if (djResult<=0){
            Toast.makeText(this,"更新库存单据失败！",Toast.LENGTH_SHORT).show();
            return;
        }
        values.clear();
        values.put(SQLitConstant.KCTZ_DJZT,"已完成");
        values.put(SQLitConstant.KCTZ_TIME, MyUntls.getNowTime());
        // 2.更新KCTZ  DJZT 已完成  更新 time  YWID
        int tzResult = db.update(SQLitConstant.TABLE_KCTZ,values,SQLitConstant.KCTZ_YWID+"=?",new String[]{ywid});
        if (tzResult <= 0){
            Toast.makeText(this,"更新库存台账失败！",Toast.LENGTH_SHORT).show();
            return;
        }
        // 3.更新KCMX  WZBM

        if (ywfx.equals("入库")){
            boolean isRk = updateKcmxInfoWhenRK(db);
            if (isRk){
                Toast.makeText(this,"审批完成！",Toast.LENGTH_SHORT).show();
                shenpiLayout.setVisibility(View.GONE);
                isYes = true;
            }
        }
        if (ywfx.equals("出库")){
            boolean isCk = updateKcmxInfoWhenCK(db);
            if (isCk){
                Toast.makeText(this,"审批完成！",Toast.LENGTH_SHORT).show();
                shenpiLayout.setVisibility(View.GONE);
                isYes = true;

            }
        }
    db.close();

    }

    /**
     * 回退
     * @param view
     */
    public void doBack(View view){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCDJ_DJZT,"未通过");
        values.put(SQLitConstant.KCDJ_TIME, MyUntls.getNowTime());
        // 1.更新KCDJ  DJZT 已完成  更新 Time  DJBM  YWID
        int djResult = db.update(SQLitConstant.TABLE_KCDJ,values,SQLitConstant.KCDJ_DJBM+"=?",new String[]{djbm});
        if (djResult<=0){
            Toast.makeText(this,"更新库存单据失败！",Toast.LENGTH_SHORT).show();
            return;
        }
        values.clear();
        values.put(SQLitConstant.KCTZ_DJZT,"未通过");
        values.put(SQLitConstant.KCTZ_TIME, MyUntls.getNowTime());
        // 2.更新KCTZ  DJZT 已完成  更新 time  YWID
        int tzResult = db.update(SQLitConstant.TABLE_KCTZ,values,SQLitConstant.KCTZ_YWID+"=?",new String[]{ywid});
        if (tzResult <= 0){
            Toast.makeText(this,"更新库存台账失败！",Toast.LENGTH_SHORT).show();
            return;
        }
        db.close();
        isYes = true;
        Toast.makeText(this,"操作成功！",Toast.LENGTH_SHORT).show();
    }

    /**
     * 更新库从明细信息
     *
     * @param db  数据库连接
     *
     */
    private boolean updateKcmxInfoWhenRK(SQLiteDatabase db) {
        //更新库从明细
        for (int i = 0; i < kctzList.size(); i++) {

            String sql = "SELECT * FROM KCMX WHERE WZBM = ? AND CK = ?";
            Cursor cursor = DBManger.QueryDataBySql(db,sql,
                    new String[]{kctzList.get(i).getWzbm(), kctzList.get(i).getCk()});
            KcmxVO kcmxVO = new KcmxVO();
            Log.e(TAG, "updateKcmxInfo: cursor : " +cursor.getCount());
            boolean isExist = false;
            if (cursor.moveToNext()){
                Log.e(TAG, "cursor.moveToNext() : " );
                isExist = true;
                kcmxVO.getKcmxVOfromCursor(cursor);
            }

            if (isExist&&kcmxVO!=null&&!kcmxVO.getWzbm().equals("")){ //判断是否取到值(同一仓库存在该物资 -->更新)
                ContentValues kcmxValues = new ContentValues();
                kcmxValues.put(SQLitConstant.KCMX_SL,kcmxVO.getSl()+kctzList.get(i).getSl());
                kcmxValues.put(SQLitConstant.KCMX_ZJE,kcmxVO.getZje()+kctzList.get(i).getSl()*kctzList.get(i).getDj());
                kcmxValues.put(SQLitConstant.KCMX_SIZE,kcmxVO.getSize()+kctzList.get(i).getSize());
                int result = db.update(SQLitConstant.TABLE_KCMX,kcmxValues,
                        " WZBM =?  AND  CK =? ",new String[]{kcmxVO.getWzbm(),kcmxVO.getCk()});
                if (result<=0){
                    Toast.makeText(this,"更新库存明细:"+kctzList.get(i).getWzmc()+"失败！",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {   //同一仓库不存在该物资 -->新增
                ContentValues values = getKcmxContentValues(i);
                long kcmxResult = db.insert(SQLitConstant.TABLE_KCMX,null,values);
                if(kcmxResult == -1){
                    Toast.makeText(this,"新增库存明细:"+kctzList.get(i).getWzmc()+"失败！",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 更新库从明细信息
     * @param db  数据库连接
     */
    private boolean updateKcmxInfoWhenCK(SQLiteDatabase db) {
        for (int i = 0; i < kctzList.size(); i++) {
            //更新库从明细
            String sql = "SELECT * FROM KCMX WHERE WZBM = ? AND CK = ?";
            Cursor cursor = DBManger.QueryDataBySql(db,sql,
                    new String[]{kctzList.get(i).getWzbm(),kctzList.get(i).getCk()});

            KcmxVO kcmxVO = new KcmxVO();
            boolean isExist = false;
            if (cursor.moveToNext()){
                Log.e(TAG, "cursor.moveToNext() : " );
                isExist = true;
                kcmxVO.getKcmxVOfromCursor(cursor);
            }
            int count = kcmxVO.getSl();
            Log.e(TAG, "updateKcmxInfo: count :   " +count);
            if (isExist&&kcmxVO!=null&&!kcmxVO.getWzbm().equals("")){ //判断是否取到值(同一仓库存在该物资 -->更新)
               if (count>kctzList.get(i).getSl()){
                   ContentValues kcmxValues = new ContentValues();
                   kcmxValues.put(SQLitConstant.KCMX_SL,kcmxVO.getSl()-kctzList.get(i).getSl());
                   kcmxValues.put(SQLitConstant.KCMX_ZJE,kcmxVO.getZje()-kctzList.get(i).getSl()*kctzList.get(i).getDj());
                   kcmxValues.put(SQLitConstant.KCMX_SIZE,kcmxVO.getSize()-kctzList.get(i).getSize());
                   db.update(SQLitConstant.TABLE_KCMX,kcmxValues,
                           " WZBM =?  AND  CK =? ",new String[]{kcmxVO.getWzbm(),kcmxVO.getCk()});
               }else if(count == kctzList.get(i).getSl()){
                   db.delete(SQLitConstant.TABLE_KCMX,
                           " WZBM =?  AND  CK =? ",new String[]{kcmxVO.getWzbm(),kcmxVO.getCk()});
               }else{
                   Toast.makeText(this,"提交出库失败，所选物资库存不足！",Toast.LENGTH_SHORT).show();
                   return false;
               }
            }else {   //同一仓库不存在该物资 -->报错
                Toast.makeText(this,"提交出库失败，所选物资库存不足！",Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        db.close();
        return  true;
    }


    /**
     * 获取库存明细插入数据格式  ContentValues
     * @param i
     * @return
     */
    @NonNull
    private ContentValues getKcmxContentValues(int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);

        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCMX_WZBM,kctzList.get(i).getWzbm());
        values.put(SQLitConstant.KCMX_KCBM,kctzList.get(i).getCk()+kctzList.get(i).getWzbm());
        values.put(SQLitConstant.KCMX_WZMC,kctzList.get(i).getWzmc());
        values.put(SQLitConstant.KCMX_WZLX,kctzList.get(i).getWzlx());
        values.put(SQLitConstant.KCMX_GGXH,kctzList.get(i).getGgxh());
        values.put(SQLitConstant.KCMX_JLDW,kctzList.get(i).getJldw());
        values.put(SQLitConstant.KCMX_SCRQ,kctzList.get(i).getScrq());
        values.put(SQLitConstant.KCMX_BZQ,kctzList.get(i).getBzq());
        values.put(SQLitConstant.KCMX_CD,kctzList.get(i).getCd());
        values.put(SQLitConstant.KCMX_DJ,kctzList.get(i).getDj());
        values.put(SQLitConstant.KCMX_SL,kctzList.get(i).getSl());
        values.put(SQLitConstant.KCMX_ZJE,kctzList.get(i).getSl()*kctzList.get(i).getDj());
        values.put(SQLitConstant.KCMX_CK,kctzList.get(i).getCk());
        values.put(SQLitConstant.KCMX_BZ,kctzList.get(i).getBz());
        values.put(SQLitConstant.KCMX_SIZE,kctzList.get(i).getSize());
        values.put(SQLitConstant.KCMX_TIME,time);
        return values;
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

    @Override
    public void onBackPressed() {
        if (isYes){
            Intent intent =new Intent(this,CkInfoActivity.class);
            intent.putExtra("TAG","YES");
            setResult(RESULT_OK,intent);
        }
        super.onBackPressed();
    }
}
