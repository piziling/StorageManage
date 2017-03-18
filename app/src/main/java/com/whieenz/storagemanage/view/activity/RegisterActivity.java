package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heziwen on 2017/3/14.
 */

public class RegisterActivity extends Activity {
    private EditText name;
    private EditText job;
    private EditText num;
    private EditText firstKey;
    private EditText secondKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    /**
     * 初始化界面
     */
    private void initView() {
        name = (EditText) findViewById(R.id.et_register_name);
        job = (EditText) findViewById(R.id.et_register_job);
        num = (EditText) findViewById(R.id.et_register_num);
        firstKey = (EditText) findViewById(R.id.et_register_firstkey);
        secondKey = (EditText) findViewById(R.id.et_register_secondkey);
    }



    public void register(View view){
        if(checkInput()){
            if(!insertInfo()){
                Toast.makeText(this,"注册失败！",Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this,"注册成功，请登录！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("num",num.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    private Boolean insertInfo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLitConstant.USER_NAME,name.getText().toString());
        values.put(SQLitConstant.USER_JOB,job.getText().toString());
        values.put(SQLitConstant.USER_NUM,num.getText().toString());
        values.put(SQLitConstant.USER_KEY,secondKey.getText().toString());
        values.put(SQLitConstant.USER_TIME,time);
        long result = db.insert(SQLitConstant.TABLE_USER,null,values);
        db.close();
        if (result ==-1){
            return  false;
        }
        return true;
    }

    /**
     * 检查用户输入
     * @return
     */
    private Boolean checkInput() {
        if (name.getText().toString().equals("")) {
            Toast.makeText(this,"请输入姓名！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (job.getText().toString().equals("")) {
            Toast.makeText(this,"请输入职位！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (num.getText().toString().equals("")) {
            Toast.makeText(this,"请输入账号！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (firstKey.getText().toString().equals("")) {
            Toast.makeText(this,"请输入密码！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (secondKey.getText().toString().equals("")) {
            Toast.makeText(this,"请再次输入密码！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!secondKey.getText().toString().equals(firstKey.getText().toString())){
            Toast.makeText(this,"两次输入密码不一致！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
