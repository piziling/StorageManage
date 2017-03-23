package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.MyUntls;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by heziwen on 2017/3/23.
 */

public class AddCkActivity extends Activity {
    private EditText name;
    private EditText size;
    private EditText address;
    private EditText bz;
    private Button cgy;
    private MyApp myApp;
    private ArrayList userInfos;
    private ArrayList resultList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ck);
        myApp = (MyApp) getApplication();
        initView();
        resultList = new ArrayList();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.add_name_value);
        size = (EditText) findViewById(R.id.add_size_value);
        address = (EditText) findViewById(R.id.add_address_value);
        bz = (EditText) findViewById(R.id.add_bz_value);
        cgy = (Button) findViewById(R.id.add_cgy_value);

        userInfos = new ArrayList();
        for (int i = 0; i < myApp.getUserArray().size(); i++) {
            UserInfo user = (UserInfo)myApp.getUserArray().get(i);
            userInfos.add(user.getName());
        }
    }

    public void onSave(View view){
        if(!checkInput()){
            return;
        }
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_CK,null,SQLitConstant.CK_CKMC+"=?",
                new String[]{name.getText().toString()},null,null,null);
        if (cursor.moveToFirst()){
            Toast.makeText(this,"该仓库已存在，请修改仓库名称！",Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(SQLitConstant.CK_CKBM,MyUntls.getUniqueFromTime("CK"));
        values.put(SQLitConstant.CK_CKMC,name.getText().toString());
        values.put(SQLitConstant.CK_SIZE,size.getText().toString());
        values.put(SQLitConstant.CK_ADDRESS,address.getText().toString());
        values.put(SQLitConstant.CK_CGY,cgy.getText().toString());
        values.put(SQLitConstant.CK_BZ,bz.getText().toString());
        values.put(SQLitConstant.CK_TIME,MyUntls.getNowTime());

        long result = db.insert(SQLitConstant.TABLE_CK,null,values);
        if(result == -1){
            Toast.makeText(this,"新增失败！",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this,"新增成功！",Toast.LENGTH_SHORT).show();

        resultList.add(name.getText().toString()+" | "+size.getText().toString()+" m³");
        myApp.initDataByType("CK");
    }

    @Override
    public void onBackPressed() {
        if (resultList!=null&&resultList.size()>0){
            Intent intent =new Intent(this,CkInfoActivity.class);
            intent.putStringArrayListExtra("new",resultList);
            setResult(RESULT_OK,intent);
        }
        super.onBackPressed();
    }

    /**
     *
     */
    public void onUserPicker(View view){
        OptionPicker picker = new OptionPicker(this, userInfos);
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                cgy.setText(item);
            }
        });
        picker.show();
    }
    private boolean checkInput() {
        if (name.getText().toString().equals("")){
            Toast.makeText(this,"请输入仓库名称！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (size.getText().toString().equals("")){
            Toast.makeText(this,"请输入仓库容量！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (address.getText().toString().equals("")){
            Toast.makeText(this,"请输入仓库地址！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cgy.getText().toString().equals("")){
            Toast.makeText(this,"请选择仓管员！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
