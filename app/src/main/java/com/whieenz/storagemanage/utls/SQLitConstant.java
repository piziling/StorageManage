package com.whieenz.storagemanage.utls;

/**
 * 数据库的常量
 * Created by heziwen on 2017/3/8.
 */

public class SQLitConstant {

    public static final  String DATABASE_NAME="info.db";//数据库名称
    public static final  int DATABASE_VERSION=1;//数据库版本号
    public static final  String TABLE_USER="user";//数据库表名
    public static final  String TABLE_GOODS="goods";//数据库表名
    public static final  String TABLE_RKDJ="";//数据库表名
    public static final  String TABLE_CKDJ="";//数据库表名

    public static final  String USER_ID="_id";//USER 表字段
    public static final  String USER_NAME="name";//USER 表字段
    public static final  String USER_JOB="job";//USER 表字段
    public static final  String USER_NUM="num";//USER 表字段
    public static final  String USER_KEY="key";//USER 表字段
    public static final  String USER_TIME="time";//USER 表字段


    public static final  String GOODS_ID="_ID";//USER 表字段
    public static final  String GOODS_WZBM="WZBM";//USER 表字段
    public static final  String GOODS_WZMC="WZMC";//USER 表字段
    public static final  String GOODS_WZLX="WZLX";//USER 表字段
    public static final  String GOODS_GGXH="GGXH";//USER 表字段
//    public static final  String GOODS_ID="_ID";//USER 表字段
//    public static final  String GOODS_ID="_ID";//USER 表字段
//    public static final  String GOODS_ID="_ID";//USER 表字段



    public static final  String INSERT_USER = "create table if not exists "+
            SQLitConstant.TABLE_USER+"("
            +SQLitConstant.USER_ID +" integer primary key autoincrement,"+
            SQLitConstant.USER_NAME+" text,"+
            SQLitConstant.USER_JOB+" text,"+
            SQLitConstant.USER_NUM+" text unique,"+
            SQLitConstant.USER_KEY+" text not null,"+
            SQLitConstant.USER_TIME+" text not null)";



}
