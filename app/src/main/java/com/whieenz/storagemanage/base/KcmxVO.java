package com.whieenz.storagemanage.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.whieenz.storagemanage.utls.SQLitConstant;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/21.
 */

public class KcmxVO {

    private String id;
    private String wzbm;
    private String kcbm;
    private String wzmc;
    private String wzlx;
    private String ggxh;
    private String jldw;
    private String scrq;
    private String bzq;
    private String cd;
    private String ck;
    private String kw;
    private double dj;
    private double zje;
    private int sl;
    private double size;
    private String bz;
    private String time;




    public String getKcbm() {
        return kcbm;
    }

    public void setKcbm(String kcbm) {
        this.kcbm = kcbm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWzbm() {
        return wzbm;
    }

    public void setWzbm(String wzbm) {
        this.wzbm = wzbm;
    }

    public String getWzmc() {
        return wzmc;
    }

    public void setWzmc(String wzmc) {
        this.wzmc = wzmc;
    }

    public String getWzlx() {
        return wzlx;
    }

    public void setWzlx(String wzlx) {
        this.wzlx = wzlx;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getScrq() {
        return scrq;
    }

    public void setScrq(String scrq) {
        this.scrq = scrq;
    }

    public String getBzq() {
        return bzq;
    }

    public void setBzq(String bzq) {
        this.bzq = bzq;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public double getDj() {
        return dj;
    }

    public void setDj(double dj) {
        this.dj = dj;
    }

    public double getZje() {
        return zje;
    }

    public void setZje(double zje) {
        this.zje = zje;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCMX_WZBM,wzbm);
        values.put(SQLitConstant.KCMX_KCBM,kcbm);
        values.put(SQLitConstant.KCMX_WZMC,wzmc);
        values.put(SQLitConstant.KCMX_WZLX,wzlx);
        values.put(SQLitConstant.KCMX_GGXH,ggxh);
        values.put(SQLitConstant.KCMX_JLDW,jldw);
        values.put(SQLitConstant.KCMX_SCRQ,scrq);
        values.put(SQLitConstant.KCMX_BZQ,bzq);
        values.put(SQLitConstant.KCMX_CD,cd);
        values.put(SQLitConstant.KCMX_DJ,dj);
        values.put(SQLitConstant.KCMX_SL,sl);
        values.put(SQLitConstant.KCMX_ZJE,zje);
        values.put(SQLitConstant.KCMX_CK,ck);
        values.put(SQLitConstant.KCMX_KW,kw);
        values.put(SQLitConstant.KCMX_BZ,bz);
        values.put(SQLitConstant.KCMX_SIZE,size);
        values.put(SQLitConstant.KCMX_TIME,time);
        return  values;
    }



    public  List<KcmxVO> cursorToKcmxVOList(Cursor cursor){
        List<KcmxVO> list = new ArrayList<>();
        //moveToNext() 返回true 表示下一条记录存在 否则表示游标中数据读取完毕
        Log.d(TAG, "cursorToKcmxVOList: cursor.getCount() :"+cursor.getCount());
        if (cursor.getCount()<=0){
            return null;
        }
        while (cursor.moveToNext()){
            //KcmxVO kcmxVO = getKcmxVOfromCursor(cursor);
            //list.add(kcmxVO);
        }
        return list;
    }



    public  void  getKcmxVOfromCursor(Cursor cursor) {
        if (cursor == null) {
            Log.e(TAG, "getKcmxVOfromCursor:  in  33333 " );
            return;
        }

        Log.e(TAG, "getKcmxVOfromCursor: SQLitConstant.KCMX_WZBM :    "+cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZBM)) );
        setWzbm(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZBM)));
        setKcbm(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_KCBM)));
        setWzmc(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZMC)));
        setWzlx(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_WZLX)));
        setGgxh(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_GGXH)));
        setJldw(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_JLDW)));
        setScrq(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_SCRQ)));
        setCd(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_CD)));
        setCk(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_CK)));
        setKw(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_KW)));
        setBz(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_BZ)));
        setBzq(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_BZQ)));
        setSl(Integer.valueOf(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_SL))));
        setSize(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_SIZE))));
        setDj(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_DJ))));
        setZje(Double.parseDouble(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_ZJE))));
        setTime(cursor.getString(cursor.getColumnIndex(SQLitConstant.KCMX_TIME)));

    }


}
