package com.whieenz.storagemanage.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.whieenz.storagemanage.R;

/**
 * Created by heziwen on 2017/3/14.
 */

public class LoginActivity extends Activity {
    private EditText uesrName;
    private EditText uesrKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uesrName = (EditText) findViewById(R.id.user_name);
        uesrKey = (EditText) findViewById(R.id.user_key);
    }

    public void login(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void doRegister(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void findKey(View view){

    }
}
