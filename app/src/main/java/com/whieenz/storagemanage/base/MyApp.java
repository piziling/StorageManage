package com.whieenz.storagemanage.base;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.util.ArrayList;

import static android.R.attr.name;
import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/21.
 */

public class MyApp extends Application {
    private static MyApp instance;
    private int infoNums;


    public static MyApp getInstance() {
        return instance;
    }

    private UserInfo userInfo; //用户信息

    private ArrayList wldwArray; //往来单位信息
    private ArrayList jldwArray; //计量单位信息
    private ArrayList rklxArray; //入库类型信息
    private ArrayList cklxArray; //出库类型信息
    private ArrayList wzflArray; //物资分类信息
    private ArrayList ckmcArray; //物资分类信息


    private CkInfoVO mStorage; //物资分类信息
    private ArrayList userArray; //物资分类信息


    private ArrayList<CkInfoVO> ckxxArray; //仓库信息


    public void initApp(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        ArrayList wldwArray = getWldw(db);
        ArrayList jldwArray = getJldw(db);
        ArrayList rklxArray = getRklx(db);
        ArrayList cklxArray = getCKlx(db);
        ArrayList wzflArray = getWzfl(db);
        ArrayList<UserInfo> userArray = getUser(db);
        ArrayList<CkInfoVO> ckxxArray = getCkxx(db);
        
        this.setCklxArray(cklxArray);
        this.setJldwArray(jldwArray);
        this.setRklxArray(rklxArray);
        this.setWldwArray(wldwArray);
        this.setWzflArray(wzflArray);
        this.setCkxxArray(ckxxArray);
        this.setUserArray(userArray);

        db.close();
    }

    public void initDataByType(String tag){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        if (tag.equals(SQLitConstant.DXFL_WZFL)){
            this.setWzflArray(getWzfl(db));
        }
        if (tag.equals(SQLitConstant.DXFL_CKLX)){
            this.setCklxArray(getCKlx(db));
        }
        if (tag.equals(SQLitConstant.DXFL_RKLX)){
            this.setRklxArray(getRklx(db));
        }
        if (tag.equals(SQLitConstant.DXFL_WLDW)){
            this.setWldwArray(getWldw(db));
        }
        if (tag.equals("USER")){
            this.setUserArray(getUser(db));
        }
        if (tag.equals("CK")){
            this.setCkxxArray(getCkxx(db));
        }

        db.close();
    }

    private ArrayList<UserInfo> getUser(SQLiteDatabase db) {
        ArrayList userArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_USER,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(SQLitConstant.USER_NAME));
            String job = cursor.getString(cursor.getColumnIndex(SQLitConstant.USER_JOB));
            String num = cursor.getString(cursor.getColumnIndex(SQLitConstant.USER_NUM));
            UserInfo user = new UserInfo(name,job,num);
            userArray.add(user);
        }
        return  userArray;
    }

    /**
     * 获取仓库信息
     * @param db
     * @return
     */
    private ArrayList<CkInfoVO> getCkxx(SQLiteDatabase db) {
        ckmcArray = new ArrayList();
        ArrayList ckxxArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_CK,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String ckmc = cursor.getString(cursor.getColumnIndex(SQLitConstant.CK_CKMC));
            String ckbm = cursor.getString(cursor.getColumnIndex(SQLitConstant.CK_CKBM));
            String size = cursor.getString(cursor.getColumnIndex(SQLitConstant.CK_SIZE));
            String cgy = cursor.getString(cursor.getColumnIndex(SQLitConstant.CK_CGY));
            String address = cursor.getString(cursor.getColumnIndex(SQLitConstant.CK_ADDRESS));
            CkInfoVO ckInfo = new CkInfoVO(ckbm,ckmc,size,cgy,address);
            ckmcArray.add(ckmc);
            ckxxArray.add(ckInfo);
        }
        return  ckxxArray;
    }


    public ArrayList getCkxxArray() {
        return ckxxArray;
    }



    /**
     * 从数据库获取物资分类信息
     * @param db
     * @return
     */
    private ArrayList getWzfl(SQLiteDatabase db) {
        ArrayList wzflArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_GLDX,null,"DXFL=?",new String[]{"WZFL"},null,null,null);
        while (cursor.moveToNext()){
            String wzfl = cursor.getString(cursor.getColumnIndex(SQLitConstant.GLDX_DXMC));
            wzflArray.add(wzfl);
        }
        return  wzflArray;
    }
    /**
     * 从数据库获取往来单位信息
     * @param db
     * @return
     */
    private ArrayList getWldw(SQLiteDatabase db) {
        ArrayList wldwArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_GLDX,null,"DXFL=?",new String[]{"WLDW"},null,null,null);
        while (cursor.moveToNext()){
            String wldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.GLDX_DXMC));
            wldwArray.add(wldw);
        }
        return  wldwArray;
    }

    /**
     * 从数据库获取计量单位信息
     * @param db
     * @return
     */
    private ArrayList getJldw(SQLiteDatabase db) {
        ArrayList jldwArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_GLDX,null,"DXFL=?",new String[]{"JLDW"},null,null,null);
        while (cursor.moveToNext()){
            String jldw = cursor.getString(cursor.getColumnIndex(SQLitConstant.GLDX_DXMC));
            jldwArray.add(jldw);
        }
        return  jldwArray;
    }
    /**
     * 从数据库获取入库类型信息
     * @param db
     * @return
     */
    private ArrayList getRklx(SQLiteDatabase db) {
        ArrayList rklxArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_GLDX,null,"DXFL=?",new String[]{"RKLX"},null,null,null);
        while (cursor.moveToNext()){
            String rklx = cursor.getString(cursor.getColumnIndex(SQLitConstant.GLDX_DXMC));
            rklxArray.add(rklx);
        }
        return  rklxArray;
    }

    /**
     * 从数据库获取出库类型信息
     * @param db
     * @return
     */
    private ArrayList getCKlx(SQLiteDatabase db) {
        ArrayList cklxArray = new ArrayList();
        Cursor cursor = db.query(SQLitConstant.TABLE_GLDX,null,"DXFL=?",new String[]{"CKLX"},null,null,null);
        while (cursor.moveToNext()){
            String cklx = cursor.getString(cursor.getColumnIndex(SQLitConstant.GLDX_DXMC));
            cklxArray.add(cklx);
        }
        return  cklxArray;
    }


    public CkInfoVO getmStorage() {
        return mStorage;
    }

    public void setmStorage(CkInfoVO mStorage) {
        this.mStorage = mStorage;
    }

    public ArrayList getWzflArray() {
        return wzflArray;
    }

    public void setWzflArray(ArrayList wzflArray) {
        this.wzflArray = wzflArray;
    }

    public ArrayList getCklxArray() {
        return cklxArray;
    }

    public void setCklxArray(ArrayList cklxArray) {
        this.cklxArray = cklxArray;
    }

    public ArrayList getRklxArray() {
        return rklxArray;
    }

    public void setRklxArray(ArrayList rklxArray) {
        this.rklxArray = rklxArray;
    }

    public ArrayList getJldwArray() {
        return jldwArray;
    }

    public void setJldwArray(ArrayList jldwArray) {
        this.jldwArray = jldwArray;
    }

    public ArrayList getWldwArray() {
        return wldwArray;
    }

    public void setWldwArray(ArrayList wldwArray) {
        this.wldwArray = wldwArray;
    }

    public void setCkxxArray(ArrayList<CkInfoVO> ckxxArray) {
        this.ckxxArray = ckxxArray;
    }

    public ArrayList getUserArray() {
        return userArray;
    }

    public void setUserArray(ArrayList userArray) {
        this.userArray = userArray;
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {

        this.userInfo = userInfo;
    }
    public ArrayList getCkmcArray() {
        return ckmcArray;
    }

    public void setCkmcArray(ArrayList ckmcArray) {
        this.ckmcArray = ckmcArray;
    }

    public int getInfoNums() {
        return infoNums;
    }

    public void setInfoNums(int infoNums) {
        this.infoNums = infoNums;
    }

}
