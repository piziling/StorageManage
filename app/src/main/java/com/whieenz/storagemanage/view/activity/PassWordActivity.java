package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;

import static com.whieenz.storagemanage.utls.DBManger.getIntance;

/**
 * Created by heziwen on 2017/3/24.
 */

public class PassWordActivity extends Activity{
    private EditText oldPw;
    private EditText newPw;
    private EditText secondPw;
    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        myApp = (MyApp) getApplication();
        oldPw = (EditText) findViewById(R.id.et_old_pw);
        newPw = (EditText) findViewById(R.id.et_new_pw);
        secondPw = (EditText) findViewById(R.id.et_second_pw);
    }

    public void onChange(View view){
        if(!checkInput()){
            return;
        }
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.USER_KEY,newPw.getText().toString());
        long result = db.update(SQLitConstant.TABLE_USER,values,SQLitConstant.USER_NUM+"=?",new String[]{myApp.getUserInfo().getNum()});
        if (result == -1){
            Toast.makeText(this,"修改出错！",Toast.LENGTH_SHORT).show();
            return;
        }
        db.close();
        Toast.makeText(this,"修改成功,请重新登录！",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkInput() {
        if (oldPw.getText().toString().equals("")){
            Toast.makeText(this,"请输入旧密码！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (newPw.getText().toString().equals("")){
            Toast.makeText(this,"请输入新密码！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (secondPw.getText().toString().equals("")){
            Toast.makeText(this,"请再次输入新密码！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!newPw.getText().toString().equals(secondPw.getText().toString())){
            Toast.makeText(this,"两次输入密码不一致！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!oldPw.getText().toString().equals(myApp.getUserInfo().getKey())){
            Toast.makeText(this,"旧密码验证失败！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }
}
