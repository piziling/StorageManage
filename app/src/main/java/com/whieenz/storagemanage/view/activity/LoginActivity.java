package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.MySqlitHelper;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.whieenz.storagemanage.utls.DBManger.getIntance;

/**
 * Created by heziwen on 2017/3/14.
 */

public class LoginActivity extends Activity {
    private EditText uesrName;
    private EditText uesrKey;
    private MySqlitHelper helper;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myApp = (MyApp) getApplication();
        uesrName = (EditText) findViewById(R.id.user_name);
        uesrKey = (EditText) findViewById(R.id.user_key);
        uesrKey.setText("111");
        uesrName.setText("whieenz");
        //创建数据库
        createSQLite();
        //初始化管理对象
        initGldx();
        //获取基本信息
        initApp();
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


    public void initApp(){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        ArrayList wldwArray = getWldw(db);
        ArrayList jldwArray = getJldw(db);
        ArrayList rklxArray = getRklx(db);
        ArrayList cklxArray = getCKlx(db);
        ArrayList wzflArray = getWzfl(db);
        myApp.setCklxArray(cklxArray);
        myApp.setJldwArray(jldwArray);
        myApp.setRklxArray(rklxArray);
        myApp.setWldwArray(wldwArray);
        myApp.setWzflArray(wzflArray);
        db.close();
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


    /**
     * 初始化数据库
     */
    private void createSQLite() {
        helper = getIntance(this);
        SQLiteDatabase db = helper.getWritableDatabase();
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

    public void login(View view){
        String num = uesrName.getText().toString();
        String key = uesrKey.getText().toString();
        if (uesrName.getText().toString().equals("")){
            Toast.makeText(this,"请输入账号！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (uesrKey.getText().toString().equals("")){
            Toast.makeText(this,"请输入密码！",Toast.LENGTH_SHORT).show();
            return;
        }
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM "+SQLitConstant.TABLE_USER+" WHERE NUM = ? ";

        //Cursor cursor = DBManger.QueryDataBySql(db,sql,new String[]{num});
        Cursor cursor = db.query(SQLitConstant.TABLE_USER,null," NUM = ? ",new String[]{num},null,null,null);
        List<UserInfo> list  = DBManger.cursorToUserList(cursor);


       if(list ==null||list.size()==0){
           Toast.makeText(this,"密码错误！",Toast.LENGTH_SHORT).show();
       } else if (list.get(0).getKey().equals(key)){ //判断密码是否正确
            myApp.setUserInfo(list.get(0));  //获取登录用户信息
            Intent intent = new Intent(this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("userInfo",list.get(0));
            intent.putExtras(bundle);
            startActivity(intent);
            db.close();
            finish();
        }else {
            Toast.makeText(this,"密码错误！",Toast.LENGTH_SHORT).show();
        }
    }

    public void doRegister(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
       // startActivity(intent);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    uesrName.setText(data.getStringExtra("num"));
                }
                break;
            default:
        }
    }

    /**
     * 点击忘记密码事件
     * @param view
     */
    public void findKey(View view){
        Toast.makeText(this,"请重新注册账户！",Toast.LENGTH_LONG).show();
    }
}
