package com.whieenz.storagemanage.utls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by heziwen on 2017/3/23.
 */

public class MyUntls {

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        return time;
    }

    /**
     * 根据当前时间随机获取编码
     * @param str
     * @return
     */
    public static String getUniqueFromTime(String str){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        return str+"_"+time;
    }
}
