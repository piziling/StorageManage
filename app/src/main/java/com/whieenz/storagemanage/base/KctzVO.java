package com.whieenz.storagemanage.base;

import android.content.ContentValues;

import com.whieenz.storagemanage.utls.SQLitConstant;

import static android.R.attr.name;

/**
 * Created by heziwen on 2017/3/20.
 * 库从台账  VO
 */

public class KctzVO {
    private String _id;
    private String wzbm;

    private String tzbm;
    private String wzmc;
    private String wzlx;
    private String ggxh;
    private String jldw;
    private String scrq;
    private String bzq;
    private double dj;
    private String cd;
    private int sl;
    private String bz;
    private String ck;
    private String jbr;
    private double size; //体积
    private String ywid;
    private String ywmc;
    private String ywrq;
    private String ywfx;
    private String time;




    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCTZ_WZBM,wzbm);
        values.put(SQLitConstant.KCTZ_TZBM,tzbm);
        values.put(SQLitConstant.KCTZ_WZMC,wzmc);
        values.put(SQLitConstant.KCTZ_WZLX,wzlx);
        values.put(SQLitConstant.KCTZ_GGXH,ggxh);
        values.put(SQLitConstant.KCTZ_JLDW,jldw);
        values.put(SQLitConstant.KCTZ_SCRQ,scrq);
        values.put(SQLitConstant.KCTZ_BZQ,bzq);
        values.put(SQLitConstant.KCTZ_CD,cd);
        values.put(SQLitConstant.KCTZ_DJ,dj);
        values.put(SQLitConstant.KCTZ_SL,sl);
        values.put(SQLitConstant.KCTZ_CK,ck);
        values.put(SQLitConstant.KCTZ_BZ,bz);
        values.put(SQLitConstant.KCTZ_SIZE,size);
        values.put(SQLitConstant.KCTZ_JBR,jbr);
        values.put(SQLitConstant.KCTZ_YWFX,ywfx);
        values.put(SQLitConstant.KCTZ_YWID,ywid);
        values.put(SQLitConstant.KCTZ_YWMC,ywmc);
        values.put(SQLitConstant.KCTZ_YWRQ,ywrq);
        values.put(SQLitConstant.KCTZ_TIME,time);
        return  values;
    }


    public String getTzbm() {
        return tzbm;
    }

    public void setTzbm(String tzbm) {
        this.tzbm = tzbm;
    }


    public double getDj() {
        return dj;
    }
    public void setDj(double dj) {
        this.dj = dj;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getYwid() {
        return ywid;
    }

    public void setYwid(String ywid) {
        this.ywid = ywid;
    }

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getYwrq() {
        return ywrq;
    }

    public void setYwrq(String ywrq) {
        this.ywrq = ywrq;
    }

    public String getYwfx() {
        return ywfx;
    }

    public void setYwfx(String ywfx) {
        this.ywfx = ywfx;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
