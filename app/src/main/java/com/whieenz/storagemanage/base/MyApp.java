package com.whieenz.storagemanage.base;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by heziwen on 2017/3/21.
 */

public class MyApp extends Application {


    private UserInfo userInfo; //用户信息

    private ArrayList wldwArray; //往来单位信息
    private ArrayList jldwArray; //计量单位信息
    private ArrayList rklxArray; //入库类型信息
    private ArrayList cklxArray; //出库类型信息
    private ArrayList wzflArray; //物资分类信息


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




    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
