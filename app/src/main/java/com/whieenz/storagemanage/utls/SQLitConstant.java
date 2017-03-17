package com.whieenz.storagemanage.utls;

/**
 * 数据库的常量
 * Created by heziwen on 2017/3/8.
 */

public class SQLitConstant {

    public static final  String DATABASE_NAME="info.db";//数据库名称
    public static final  int DATABASE_VERSION=1;//数据库版本号
    public static final  String TABLE_USER="USER";//数据库表名
    public static final  String TABLE_GOODS="GOODS";//数据库表名
    public static final  String TABLE_CK="CK";//数据库表名
    public static final  String TABLE_KW="KW";//数据库表名
    public static final  String TABLE_KCTZ="KCTZ";//数据库表名
    public static final  String TABLE_KCDJ="KCDJ";//数据库表名
    public static final  String TABLE_CKMX="CKMX";//数据库表名
    //用户表字段
    public static final  String USER_ID="_ID";//USER 表字段
    public static final  String USER_NAME="NAME";//USER 表字段
    public static final  String USER_JOB="JOB";//USER 表字段
    public static final  String USER_NUM="NUM";//USER 表字段
    public static final  String USER_KEY="KEY";//USER 表字段
    public static final  String USER_TIME="TIME";//USER 表字段

    //物资种类字段
    public static final  String GOODS_ID="_ID";
    public static final  String GOODS_WZBM="WZBM";
    public static final  String GOODS_WZMC="WZMC";
    public static final  String GOODS_WZLX="WZLX";
    public static final  String GOODS_GGXH="GGXH";
    public static final  String GOODS_JLDW="JLDW";
    public static final  String GOODS_SCRQ="SCRQ";
    public static final  String GOODS_BZQ="BZQ";
    public static final  String GOODS_CD="DJ";
    public static final  String GOODS_DJ="CD";
    public static final  String GOODS_BZ="BZ";
    public static final  String GOODS_TIME="TIME"; //物资新增时间

    //仓库表字段
    public static final  String CK_ID="_ID";
    public static final  String CK_CKBM="CKBM";
    public static final  String CK_CKMC="CKMC";
    public static final  String CK_ADDRESS="ADDRESS";
    public static final  String CK_SIZE="SIZE";
    public static final  String CK_CGY="CGY";
    public static final  String CK_BZ="BZ";
    public static final  String CK_TIME="TIME";

    //库位表字段
    public static final  String KW_ID="_ID";
    public static final  String KW_KWMC="KWMC";
    public static final  String KW_KWBM="KWBM";
    public static final  String KW_SIZE="SIZE";
    public static final  String KW_SSCK="SSCK";
    public static final  String KW_BZ="BZ";
    public static final  String KW_TIME="TIME";

    //库从台账
    public static final  String KCTZ_ID="_ID";
    public static final  String KCTZ_WZBM="WZBM";
    public static final  String KCTZ_WZMC="WZMC";
    public static final  String KCTZ_WZLX="WZLX";
    public static final  String KCTZ_GGXH="GGXH";
    public static final  String KCTZ_JLDW="JLDW";
    public static final  String KCTZ_SCRQ="SCRQ";
    public static final  String KCTZ_BZQ="BZQ";
    public static final  String KCTZ_CD="DJ";
    public static final  String KCTZ_DJ="CD";
    public static final  String KCTZ_SL="SL";
    public static final  String KCTZ_BZ="BZ";
    public static final  String KCTZ_CK="CK";
    public static final  String KCTZ_KW="KW";
    public static final  String KCTZ_JBR="JBR";
    public static final  String KCTZ_SIZE="SIZE";
    public static final  String KCTZ_YWID="YWID";
    public static final  String KCTZ_YWMC="YWMC";
    public static final  String KCTZ_YWRQ="YWRQ";
    public static final  String KCTZ_YWFX="YWFX";
    public static final  String KCTZ_TIME="TIME";

    //库存单据
    public static final  String KCDJ_ID="_ID";
    public static final  String KCDJ_DJBM="DJBM";
    public static final  String KCDJ_DJMC="DJMC";
    public static final  String KCDJ_DJLX="DJLX";
    public static final  String KCDJ_ZDRQ="ZDRQ";
    public static final  String KCDJ_WLDW="WLDW";
    public static final  String KCDJ_CK="CK";
    public static final  String KCDJ_ZJR="ZJR";
    public static final  String KCDJ_DJZT="DJZT";
    public static final  String KCDJ_ZDR="ZDR";
    public static final  String KCDJ_DCLR="DCLR";
    public static final  String KCDJ_YWID="YWID";
    public static final  String KCDJ_YWRQ="YWRQ";
    public static final  String KCDJ_YWFX="YWFX";
    public static final  String KCDJ_TIME="TIME";
    public static final  String KCDJ_BZ="BZ";




    public static final  String CREATE_USER = "CREATE TABLE IF NOT EXISTS "+
            TABLE_USER+"("
            +USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            USER_NAME+" TEXT,"+
            USER_JOB+" TEXT,"+
            USER_NUM+" TEXT UNIQUE,"+
            USER_KEY+" TEXT NOT NULL,"+
            USER_TIME+" TEXT NOT NULL)";

    public static final  String CREATE_GOODS = "CREATE TABLE IF NOT EXISTS "+
            TABLE_GOODS+"("+
            GOODS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            GOODS_WZBM+" TEXT UNIQUE,"+
            GOODS_WZMC+" TEXT,"+
            GOODS_GGXH+" TEXT,"+
            GOODS_WZLX+" TEXT NOT NULL,"+
            GOODS_JLDW+" TEXT NOT NULL,"+
            GOODS_BZQ+" TEXT NOT NULL,"+
            GOODS_CD+" TEXT NOT NULL,"+
            GOODS_DJ+" INTEGER,"+
            GOODS_SCRQ+" TEXT NOT NULL,"+
            GOODS_TIME+" TEXT NOT NULL,"+
            GOODS_BZ+" TEXT)";

    //创建仓库
    public static final  String CREATE_CK = "CREATE TABLE IF NOT EXISTS "+
            TABLE_CK+"("+
            CK_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            CK_CKBM+" TEXT UNIQUE,"+
            CK_CKMC+" TEXT,"+
            CK_ADDRESS+" TEXT,"+
            CK_SIZE+" TEXT NOT NULL,"+
            CK_CGY+" TEXT NOT NULL,"+
            CK_TIME+" TEXT NOT NULL,"+
            CK_BZ+" TEXT)";


    //创建库位
    public static final  String CREATE_KW = "CREATE TABLE IF NOT EXISTS "+
            TABLE_KW+"("+
            KW_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KW_KWBM+" TEXT UNIQUE,"+
            KW_KWMC+" TEXT,"+
            KW_SIZE+" TEXT,"+
            KW_SSCK+" TEXT NOT NULL,"+
            KW_TIME+" TEXT NOT NULL,"+
            KW_BZ+" TEXT)";

    //创建库存台账
    public static final  String CREATE_KCTZ = "CREATE TABLE IF NOT EXISTS "+
            TABLE_KCTZ+"("+
            KCTZ_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KCTZ_YWID+" TEXT UNIQUE,"+
            KCTZ_YWMC+" TEXT,"+
            KCTZ_YWFX+" TEXT,"+
            KCTZ_YWRQ+" TEXT,"+
            KCTZ_WZBM+" TEXT UNIQUE,"+
            KCTZ_WZMC+" TEXT,"+
            KCTZ_GGXH+" TEXT,"+
            KCTZ_WZLX+" TEXT NOT NULL,"+
            KCTZ_JLDW+" TEXT NOT NULL,"+
            KCTZ_BZQ+" TEXT NOT NULL,"+
            KCTZ_CD+" TEXT NOT NULL,"+
            KCTZ_SIZE+" TEXT NOT NULL,"+
            KCTZ_DJ+" INTEGER,"+
            KCTZ_SL+" INTEGER,"+
            KCTZ_SCRQ+" TEXT NOT NULL,"+
            KCTZ_CK+" TEXT NOT NULL,"+
            KCTZ_KW+" TEXT NOT NULL,"+
            KCTZ_JBR+" TEXT NOT NULL,"+
            KCTZ_TIME+" TEXT NOT NULL,"+
            KCTZ_BZ+" TEXT)";



    //创建库存单据
    public static final  String CREATE_KCDJ = "CREATE TABLE IF NOT EXISTS "+
            TABLE_KCTZ+"("+
            KCDJ_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KCDJ_DJBM+" TEXT UNIQUE,"+
            KCDJ_DJMC+" TEXT,"+
            KCDJ_DJLX+" TEXT,"+
            KCDJ_ZDRQ+" TEXT,"+
            KCDJ_WLDW+" TEXT,"+
            KCDJ_DJZT+" TEXT,"+
            KCDJ_CK+" TEXT,"+
            KCDJ_ZJR+" INTEGER,"+
            KCDJ_ZDR+" TEXT,"+
            KCDJ_DCLR+" TEXT,"+
            KCDJ_YWID+" TEXT UNIQUE,"+
            KCDJ_YWRQ+" TEXT,"+
            KCDJ_YWFX+" TEXT,"+
            KCDJ_TIME+" TEXT,"+
            KCDJ_BZ+" TEXT)";

}
