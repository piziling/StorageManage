package com.whieenz.storagemanage.base;

import android.app.Application;

/**
 * Created by heziwen on 2017/3/21.
 */

public class MyApp extends Application {


    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
