package com.whieenz.storagemanage.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

import static android.content.ContentValues.TAG;


/**
 * Created by heziwen on 2017/3/9.
 */

public class AddGoodsActivity extends Activity{

    private EditText wzbm;
    private EditText wzmc;
    private EditText ggxh;
    private EditText bzq;
    private EditText cd;
    private EditText dj;
    private EditText bz;
    private Button   jldw;
    private Button   wzlx;
    private Button   scrq;
    private Bitmap   mbitmap;
    private static final String QRTAG = "WHIEENZ";
    private CheckBox createQB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        initView();
    }

    private void initView() {
        wzbm = (EditText) findViewById(R.id.goods_value_wzbm);
        wzmc = (EditText) findViewById(R.id.goods_value_wzmc);
        ggxh = (EditText) findViewById(R.id.goods_value_ggxh);
        jldw = (Button) findViewById(R.id.goods_value_jldw);
        wzlx = (Button) findViewById(R.id.goods_value_wzlx);
        bzq = (EditText) findViewById(R.id.goods_value_bzq);
        scrq = (Button) findViewById(R.id.goods_value_scrq);
        cd = (EditText) findViewById(R.id.goods_value_cd);
        dj = (EditText) findViewById(R.id.goods_value_dj);
        bz = (EditText) findViewById(R.id.goods_value_bz);
        createQB = (CheckBox) findViewById(R.id.cb_createQB);

    }

    /**
     * 年月日时间学择器
     * @param view
     */
    public void onYearMonthDayPicker(View view) {
        final DatePicker picker = new DatePicker(this);
        picker.setTopPadding(2);
        picker.setRangeStart(2016, 8, 29);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(2017, 03, 10);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                scrq.setText(year + "-" + month + "-" + day);

            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    /**
     * 物资类型选择
     * @param view
     */
    public void onWzlxPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "产成品", "原材料", "其他"
        });
        picker.setCycleDisable(false);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                wzlx.setText(item);
            }
        });
        picker.show();
    }

    /**
     * 物资类型选择
     * @param view
     */
    public void onJldwPicker(View view) {
        OptionPicker picker = new OptionPicker(this, new String[]{
                "个", "件", "箱","千克","克","吨","条","件","瓶","打"
        });
        picker.setCycleDisable(false);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                jldw.setText(item);
            }
        });
        picker.show();
    }

    public void click(View view) {
        switch (view.getId()){

        }
    }

    public Map<String,Object> getEdittext() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("wzbm",wzbm.getText().toString());
        map.put("wzmc",wzmc.getText().toString());
        map.put("ggxh",ggxh.getText().toString());
        map.put("wzlx",wzlx.getText().toString());
        map.put("jldw",jldw.getText().toString());
        map.put("bzq", bzq.getText().toString());
        map.put("scrq",scrq.getText().toString());
        map.put("cd",  cd.getText().toString());
        map.put("dj",  dj.getText().toString());
        map.put("bz",  bz.getText().toString());
        return  map;
    }

    public void onSave(View view) {
        //1.判断是否有空
        //2.判断物资编码是否重复
        //3.判断是否选择生成二维码
        if (!checkIsNull()) {
            return;
        }
        if (createQB.isChecked()){
            mbitmap = createQRcode();
            saveQRcode(mbitmap);
        }




    }

    /**
     * 检查输入内容是否为空
     * @return
     */
    private boolean checkIsNull() {
        if ( wzbm.getText().toString().equals("")) {
            Toast.makeText(this,"物资编码不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( wzmc.getText().toString().equals("")) {
            Toast.makeText(this,"物资名称不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( ggxh.getText().toString().equals("")) {
            Toast.makeText(this,"规格型号不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( wzlx.getText().toString().equals("")) {
            Toast.makeText(this,"物资类型不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( jldw.getText().toString().equals("")) {
            Toast.makeText(this,"计量单位不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( bzq.getText().toString().equals("")) {
            Toast.makeText(this,"保质期不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( scrq.getText().toString().equals("")) {
            Toast.makeText(this,"生产日期不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( cd.getText().toString().equals("")) {
            Toast.makeText(this,"产地不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( dj.getText().toString().equals("")) {
            Toast.makeText(this,"单价不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 生成二维码
     * @return
     */
    private Bitmap createQRcode(){
        Bitmap bitmap = null;
        if (getQRcodeText().equals("")) {
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
        }else{
            //返回二维码bitmap
            //bitmap = EncodingUtils.createQRCode(getQRcodeText(),600,600,null);
            bitmap = EncodingUtils.createQRCode(getQRcodeText(),600,600, BitmapFactory.decodeResource(getResources(),R.drawable.yg));
        }
        return bitmap;
    }

    /**
     * 获取要二维码的字符串
     * @return
     */
    private String getQRcodeText() {
        String input = QRTAG;
        input = input +";"+getEdittext().get("wzbm");
        input = input +";"+getEdittext().get("wzmc");
        input = input +";"+getEdittext().get("ggxh");
        input = input +";"+getEdittext().get("wzlx");
        input = input +";"+getEdittext().get("jldw");
        input = input +";"+getEdittext().get("bzq");
        input = input +";"+getEdittext().get("scrq");
        input = input +";"+getEdittext().get("cd");
        input = input +";"+getEdittext().get("dj");
        input = input +";"+getEdittext().get("bz");
        Log.d(TAG, "getQRcodeText: 二维码的字符串"+input);
        return input;
    }

    /**
     * 保存生成的二维码
     */
    private void saveQRcode(Bitmap bitmap){
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/storage/image/";
        File dirFirstFolder = new File(dir);// 方法一：直接使用字符串，如果是安装在存储卡上面，则需要使用sdcard2，但是需要确认是否有存储卡
        if(!dirFirstFolder.exists()) { //
            // 如果该文件夹不存在，则进行创建
            Log.d(TAG, "文件夹不存在 创建"+dir);
            if(dirFirstFolder.mkdirs()){ //创建文件夹
                Log.d(TAG, " 创建文件夹成功 ");
            }else {
                Log.d(TAG, " 创建文件夹失败 ");
                Toast.makeText(this,"创建文件夹失败",Toast.LENGTH_SHORT).show();
                return;
            }
        }else{
            Log.d(TAG, dir+"文件夹存在不需要新建文件夹，执行下一步!");
        }
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Log.d(TAG, "save: MEDIA_MOUNTED 无法读写");
            Toast.makeText(this," 无法读写",Toast.LENGTH_SHORT).show();
            return;
        }
        //设置生成二维码的文件名称
        String fileName = getEdittext().get("wzmc").toString()+getEdittext().get("wzbm").toString();

        try {
            File file = new File(dir + fileName + ".jpg");
            FileOutputStream out = new FileOutputStream(file);
            Boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            Log.d(TAG, "save:保存成功！");
            if(isSuccess){
                Toast.makeText(this,"生成二维码名称为："+fileName+"\n二维码保存在："+dir+"文件夹下！",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
