package com.whieenz.storagemanage.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.MySqlitHelper;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/14.
 */

public class LoginActivity extends Activity {
    private EditText uesrName;
    private EditText uesrKey;
    private MySqlitHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uesrName = (EditText) findViewById(R.id.user_name);
        uesrKey = (EditText) findViewById(R.id.user_key);
        //创建数据库
        createSQLite();


    }

    /**
     * 初始化数据库
     */
    private void createSQLite() {
        helper = DBManger.getIntance(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //创建User表
        db.execSQL(SQLitConstant.INSERT_USER);
        db.close();
    }

    public void login(View view){
        String num = uesrName.getText().toString();
        String key = uesrKey.getText().toString();
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM "+SQLitConstant.TABLE_USER+" WHERE NUM = ? ";

        Cursor cursor = DBManger.QueryDataBySql(db,sql,new String[]{num});
        //Cursor cursor = db.query(SQLitConstant.TABLE_USER,null," num =? ",new String[]{num},null,null,null);
        List<UserInfo> list  = DBManger.cursorToUserList(cursor);
        Log.d(TAG, "login: List<UserInfo> list  :"+list.get(0).toString());
        if (list.get(0).getKey().equals(key)){ //判断密码是否正确
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            db.close();
            finish();
        }else {
            Toast.makeText(this,"密码错误！",Toast.LENGTH_SHORT).show();
        }

    }

    public void doRegister(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 点击忘记密码事件
     * @param view
     */
    public void findKey(View view){
        Toast.makeText(this,"请重新注册账户！",Toast.LENGTH_LONG).show();
    }
}
