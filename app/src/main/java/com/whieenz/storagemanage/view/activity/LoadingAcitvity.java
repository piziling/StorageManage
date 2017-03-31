package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static android.content.ContentValues.TAG;


/**
 * Created by heziwen on 2017/3/31.
 */

public class LoadingAcitvity extends Activity {
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);

        myApp = (MyApp) getApplication();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //创建数据库
                createSQLite();
                //初始化管理对象
                initGldx();
                //获取基本信息
                myApp.initApp();
                selectStart();
            }
        }, 2000); //延迟2秒跳转


    }


    public void initGldx(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_APPINFO,null,"NAME =? AND VALUE =?",
                new String[]{"初始化管理对象","是"},null,null,null);
        if (!cursor.moveToFirst()){
            if(!DBManger.initInfo()){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);
                ContentValues values  = new ContentValues();
                values.put(SQLitConstant.APPINFO_NAME,"初始化管理对象");
                values.put(SQLitConstant.APPINFO_VALUE,"否");
                values.put(SQLitConstant.APPINFO_RESULT,"出错");
                values.put(SQLitConstant.APPINFO_TIME,time);
                values.put(SQLitConstant.APPINFO_BZ,"初始化管理对象出错");
                db.insert(SQLitConstant.TABLE_APPINFO,null,values);
                Toast.makeText(this,"初始化管理对象出错！",Toast.LENGTH_LONG).show();
            }else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);
                ContentValues values  = new ContentValues();
                values.put(SQLitConstant.APPINFO_NAME,"初始化管理对象");
                values.put(SQLitConstant.APPINFO_VALUE,"是");
                values.put(SQLitConstant.APPINFO_RESULT,"成功");
                values.put(SQLitConstant.APPINFO_TIME,time);
                values.put(SQLitConstant.APPINFO_BZ,"初始化管理对象成功");
                db.insert(SQLitConstant.TABLE_APPINFO,null,values);
                Toast.makeText(this,"初始化管理对象成功！",Toast.LENGTH_LONG).show();
            }
        }
        db.close();
    }
    /**
     * 初始化数据库
     */
    private void createSQLite() {

        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        //创建User表
        db.execSQL(SQLitConstant.CREATE_USER);
        //创建GOODS
        db.execSQL(SQLitConstant.CREATE_GOODS);
        //创建KCTZ
        db.execSQL(SQLitConstant.CREATE_KCTZ);
        //创建KCDJ
        db.execSQL(SQLitConstant.CREATE_KCDJ);
        //创建KCMX
        db.execSQL(SQLitConstant.CREATE_KCMX);
        //创建CK
        db.execSQL(SQLitConstant.CREATE_CK);
        //创建GLDX
        db.execSQL(SQLitConstant.CREATE_GLDX);
        //创建APPINFO
        db.execSQL(SQLitConstant.CREATE_APPINFO);

        db.close();
    }

    private void selectStart(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_APPINFO,null,
                SQLitConstant.APPINFO_NAME+"=?",new String[]{"记住密码"},null,null,SQLitConstant.APPINFO_TIME+" DESC");
        String num = "";
        String key = "";
        boolean tag = false;
        if (cursor.moveToFirst()){
             num = cursor.getString(cursor.getColumnIndex(SQLitConstant.APPINFO_VALUE));
             key = cursor.getString(cursor.getColumnIndex(SQLitConstant.APPINFO_RESULT));
             tag = true;
        }
        Cursor cursor0 = db.query(SQLitConstant.TABLE_USER,null," NUM = ? ",new String[]{num},null,null,null);
        List<UserInfo> list  = DBManger.cursorToUserList(cursor0);
        if (tag){
            Intent intent = new Intent(this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo",list.get(0));
            myApp.setUserInfo(list.get(0));
            intent.putExtras(bundle);
            startActivity(intent);
            db.close();
            finish();
        }else {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
