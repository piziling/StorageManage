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
    public static final  String TABLE_KCMX="KCMX";//数据库表名
    public static final  String TABLE_GLDX="GLDX";//数据库表名
    public static final  String TABLE_APPINFO="APPINFO";//数据库表名


    public static final  String DXFL_WLDW="WLDW";//对象分类
    public static final  String DXFL_JLDW="JLDW";//对象分类
    public static final  String DXFL_RKLX="RKLX";//对象分类
    public static final  String DXFL_CKLX="CKLX";//对象分类
    public static final  String DXFL_WZFL="WZFL";//对象分类

    //应用信息表字段
    public static final  String APPINFO_ID="_ID";//APPINFO 表字段
    public static final  String APPINFO_NAME="NAME";//APPINFO 表字段
    public static final  String APPINFO_VALUE="VALUE";//APPINFO 表字段
    public static final  String APPINFO_RESULT="RESULT";//APPINFO 表字段
    public static final  String APPINFO_TIME="TIME";//APPINFO 表字段
    public static final  String APPINFO_BZ="BZ";//APPINFO 表字段



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
    public static final  String GOODS_CD="CD";
    public static final  String GOODS_DJ="DJ";
    public static final  String GOODS_BZ="BZ";
    public static final  String GOODS_SIZE="SIZE";
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
    public static final  String GLDX_ID="_ID";
    public static final  String GLDX_DXID="DXID";
    public static final  String GLDX_DXMC="DXMC";
    public static final  String GLDX_DXFL="DXFL";
    public static final  String GLDX_USER="USER";
    public static final  String GLDX_TIME="TIME";
    public static final  String GLDX_BZ="BZ";

    //库从台账
    public static final  String KCTZ_ID="_ID";
    public static final  String KCTZ_WZBM="WZBM";
    public static final  String KCTZ_TZBM="TZBM";
    public static final  String KCTZ_WZMC="WZMC";
    public static final  String KCTZ_WZLX="WZLX";
    public static final  String KCTZ_GGXH="GGXH";
    public static final  String KCTZ_JLDW="JLDW";
    public static final  String KCTZ_SCRQ="SCRQ";
    public static final  String KCTZ_BZQ="BZQ";
    public static final  String KCTZ_CD="CD";
    public static final  String KCTZ_DJ="DJ";
    public static final  String KCTZ_SL="SL";
    public static final  String KCTZ_BZ="BZ";
    public static final  String KCTZ_CK="CK";
    public static final  String KCTZ_JBR="JBR";
    public static final  String KCTZ_SIZE="SIZE";
    public static final  String KCTZ_YWID="YWID";
    public static final  String KCTZ_YWMC="YWMC";
    public static final  String KCTZ_YWRQ="YWRQ";
    public static final  String KCTZ_YWFX="YWFX";
    public static final  String KCTZ_TIME="TIME";
    public static final  String KCTZ_DJZT="DJZT";

    //库存单据
    public static final  String KCDJ_ID="_ID";
    public static final  String KCDJ_DJBM="DJBM";
    public static final  String KCDJ_DJMC="DJMC";
    public static final  String KCDJ_DJLX="DJLX";
    public static final  String KCDJ_ZDRQ="ZDRQ";
    public static final  String KCDJ_WLDW="WLDW";
    public static final  String KCDJ_CK="CK";
    public static final  String KCDJ_ZJE="ZJE";
    public static final  String KCDJ_DJZT="DJZT";
    public static final  String KCDJ_ZDR="ZDR";
    public static final  String KCDJ_DCLR="DCLR";
    public static final  String KCDJ_YWID="YWID";
    public static final  String KCDJ_YWMC="YWMC";
    public static final  String KCDJ_YWRQ="YWRQ";
    public static final  String KCDJ_YWFX="YWFX";
    public static final  String KCDJ_TIME="TIME";
    public static final  String KCDJ_BZ="BZ";


    //库存物资明细表字段
    public static final  String KCMX_ID="_ID";
    public static final  String KCMX_WZBM="WZBM";
    public static final  String KCMX_KCBM="KCBM";
    public static final  String KCMX_WZMC="WZMC";
    public static final  String KCMX_WZLX="WZLX";
    public static final  String KCMX_GGXH="GGXH";
    public static final  String KCMX_JLDW="JLDW";
    public static final  String KCMX_SCRQ="SCRQ";
    public static final  String KCMX_BZQ="BZQ";
    public static final  String KCMX_CD="CD";
    public static final  String KCMX_CK="CK";
    public static final  String KCMX_KW="KW";
    public static final  String KCMX_DJ="DJ";
    public static final  String KCMX_ZJE="ZJE";
    public static final  String KCMX_SL="SL";
    public static final  String KCMX_SIZE="SIZE";
    public static final  String KCMX_BZ="BZ";
    public static final  String KCMX_TIME="TIME"; //物资新增时间


    public static final  String CREATE_USER = "CREATE TABLE IF NOT EXISTS "+
            TABLE_USER+"("
            +USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            USER_NAME+" TEXT,"+
            USER_JOB+" TEXT,"+
            USER_NUM+" TEXT UNIQUE,"+
            USER_KEY+" TEXT ,"+
            USER_TIME+" TEXT )";

    public static final  String CREATE_APPINFO = "CREATE TABLE IF NOT EXISTS "+
            TABLE_APPINFO+"("
            +APPINFO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            APPINFO_NAME+" TEXT,"+
            APPINFO_VALUE+" TEXT,"+
            APPINFO_RESULT+" TEXT,"+
            APPINFO_TIME+" TEXT,"+
            APPINFO_BZ+" TEXT )";

    public static final  String CREATE_GOODS = "CREATE TABLE IF NOT EXISTS "+
            TABLE_GOODS+"("+
            GOODS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            GOODS_WZBM+" TEXT UNIQUE,"+
            GOODS_WZMC+" TEXT,"+
            GOODS_GGXH+" TEXT,"+
            GOODS_WZLX+" TEXT ,"+
            GOODS_JLDW+" TEXT ,"+
            GOODS_BZQ+" TEXT ,"+
            GOODS_CD+" TEXT ,"+
            GOODS_DJ+" TEXT,"+
            GOODS_SIZE+" TEXT,"+
            GOODS_SCRQ+" TEXT ,"+
            GOODS_TIME+" TEXT ,"+
            GOODS_BZ+" TEXT)";

    //创建仓库
    public static final  String CREATE_CK = "CREATE TABLE IF NOT EXISTS "+
            TABLE_CK+"("+
            CK_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            CK_CKBM+" TEXT UNIQUE,"+
            CK_CKMC+" TEXT,"+
            CK_SIZE+" TEXT ,"+
            CK_ADDRESS+" TEXT,"+
            CK_CGY+" TEXT ,"+
            CK_TIME+" TEXT ,"+
            CK_BZ+" TEXT)";


    //创建管理对象
    public static final  String CREATE_GLDX = "CREATE TABLE IF NOT EXISTS "+
            TABLE_GLDX+"("+
            GLDX_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            GLDX_DXID+" TEXT UNIQUE,"+
            GLDX_DXMC+" TEXT,"+
            GLDX_DXFL+" TEXT,"+
            GLDX_USER+" TEXT,"+
            GLDX_TIME+" TEXT,"+
            GLDX_BZ+" TEXT)";

    //创建库存台账
    public static final  String CREATE_KCTZ = "CREATE TABLE IF NOT EXISTS "+
            TABLE_KCTZ+"("+
            KCTZ_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KCTZ_YWID+" TEXT,"+
            KCTZ_YWMC+" TEXT,"+
            KCTZ_YWFX+" TEXT,"+
            KCTZ_YWRQ+" TEXT,"+
            KCTZ_TZBM+" TEXT UNIQUE,"+
            KCTZ_WZBM+" TEXT,"+
            KCTZ_WZMC+" TEXT,"+
            KCTZ_GGXH+" TEXT,"+
            KCTZ_WZLX+" TEXT ,"+
            KCTZ_DJ+" INTEGER,"+
            KCTZ_SL+" INTEGER,"+
            KCTZ_JLDW+" TEXT ,"+
            KCTZ_SIZE+" TEXT ,"+
            KCTZ_SCRQ+" TEXT ,"+
            KCTZ_CD+" TEXT ,"+
            KCTZ_CK+" TEXT ,"+
            KCTZ_BZQ+" TEXT ,"+
            KCTZ_JBR+" TEXT ,"+
            KCTZ_DJZT+" TEXT ,"+
            KCTZ_TIME+" TEXT ,"+
            KCTZ_BZ+" TEXT)";



    //创建库存单据
    public static final  String CREATE_KCDJ = "CREATE TABLE IF NOT EXISTS "+
            TABLE_KCDJ+"("+
            KCDJ_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KCDJ_DJBM+" TEXT UNIQUE,"+
            KCDJ_DJMC+" TEXT,"+
            KCDJ_DJLX+" TEXT,"+
            KCDJ_WLDW+" TEXT,"+
            KCDJ_CK+" TEXT,"+
            KCDJ_ZJE+" INTEGER,"+
            KCDJ_ZDR+" TEXT,"+
            KCDJ_DCLR+" TEXT,"+
            KCDJ_DJZT+" TEXT,"+
            KCDJ_YWID+" TEXT UNIQUE,"+
            KCDJ_YWFX+" TEXT,"+
            KCDJ_YWMC+" TEXT,"+
            KCDJ_YWRQ+" TEXT,"+
            KCDJ_ZDRQ+" TEXT,"+
            KCDJ_TIME+" TEXT,"+
            KCDJ_BZ+" TEXT)";


    //创建仓库物资明细表
    public static final  String CREATE_KCMX = "CREATE TABLE IF NOT EXISTS "+
            TABLE_KCMX+"("+
            KCMX_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            KCMX_WZBM+" TEXT,"+
            KCMX_KCBM+" TEXT UNIQUE,"+  //库存编码
            KCMX_WZMC+" TEXT,"+
            KCMX_GGXH+" TEXT,"+
            KCMX_WZLX+" TEXT,"+
            KCMX_JLDW+" TEXT,"+
            KCMX_SL+" TEXT,"+
            KCMX_SIZE+" TEXT,"+
            KCMX_DJ+" TEXT,"+
            KCMX_ZJE+" TEXT,"+
            KCMX_CK+" TEXT,"+
            KCMX_BZQ+" TEXT,"+
            KCMX_CD+" TEXT,"+
            KCMX_SCRQ+" TEXT,"+
            KCMX_TIME+" TEXT ,"+
            KCMX_BZ+" TEXT)";

}
