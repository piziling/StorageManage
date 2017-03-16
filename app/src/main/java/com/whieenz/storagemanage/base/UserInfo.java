package com.whieenz.storagemanage.base;

import java.io.Serializable;

/**
 * Created by heziwen on 2017/3/15.
 */

public class UserInfo implements Serializable {
    private String name;
    private String job;
    private String num;
    private String key;
    private String time;

    public UserInfo(String name, String job, String num, String key) {
        this.name = name;
        this.job = job;
        this.num = num;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
