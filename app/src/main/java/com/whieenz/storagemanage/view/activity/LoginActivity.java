package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.MySqlitHelper;
import com.whieenz.storagemanage.utls.MyUntls;
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
    private CheckBox checkBox;
    private MySqlitHelper helper;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myApp = (MyApp) getApplication();
        helper = new MySqlitHelper(this);
        uesrName = (EditText) findViewById(R.id.user_name);
        uesrKey = (EditText) findViewById(R.id.user_key);
        checkBox = (CheckBox) findViewById(R.id.cb_save_pw);
        uesrKey.setText("111");
        uesrName.setText("whieenz");
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
            saveKey();
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
     */
    public void saveKey(){
        if (checkBox.isChecked()){
            SQLiteDatabase db = helper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLitConstant.APPINFO_NAME,"记住密码");
            values.put(SQLitConstant.APPINFO_VALUE,uesrName.getText().toString());
            values.put(SQLitConstant.APPINFO_RESULT,uesrKey.getText().toString());
            values.put(SQLitConstant.APPINFO_TIME, MyUntls.getNowTime());
            values.put(SQLitConstant.APPINFO_BZ,"记住密码");
            db.insert(SQLitConstant.TABLE_APPINFO,null,values);
            db.close();
        }else {
            SQLiteDatabase db = helper.getReadableDatabase();
            db.delete(SQLitConstant.TABLE_APPINFO,SQLitConstant.APPINFO_NAME+"=?",new String[]{"记住密码"});
            db.close();
        }
    }
}
