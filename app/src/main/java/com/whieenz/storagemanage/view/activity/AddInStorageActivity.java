package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.GoodsVO;
import com.whieenz.storagemanage.base.KctzVO;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.SQLitConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.OptionPicker;

import static android.R.attr.tag;
import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.media.CamcorderProfile.get;

/**
 * Created by heziwen on 2017/310.
 */

public class AddInStorageActivity extends Activity {
    private TextView djrq;
    private Button djlx;
    private Button wldw;
    private Button add;
    private Button ck;
    private Button kw;
    private EditText djbh;
    private EditText jbr;
    private EditText bz;
    private View firstBottom;
    private View resultBottom;
    private TextView  resultTv;
    private List<Map<String, String>> resultlists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_storage);
        initView();
    }

    private void initView() {
        djrq =(TextView) findViewById(R.id.in_value_djrq);
        resultTv =(TextView) findViewById(R.id.tv_result);
        djbh =(EditText) findViewById(R.id.in_value_djbh);
        jbr =(EditText) findViewById(R.id.in_value_jbr);
        bz =(EditText) findViewById(R.id.in_value_bz);
        ck = (Button) findViewById(R.id.in_value_ck);
        kw = (Button) findViewById(R.id.in_value_kw);
        wldw = (Button) findViewById(R.id.in_value_wldw);
        djlx = (Button) findViewById(R.id.in_value_djlx);
        add = (Button) findViewById(R.id.in_add);
        firstBottom =  findViewById(R.id.bottom_first_layout);
        resultBottom = findViewById(R.id.bottom_result_layout);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        djrq.setText(str);
    }

    /**
     *
     */
    public void onDjlxPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "采购入库", "产成品入库", "其它入库"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                djlx.setText(item);
            }
        });
        picker.show();
    }
    /**
     *
     */
    public void onCkPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "武汉仓库", "北京仓库", "上海仓库"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                ck.setText(item);
            }
        });
        picker.show();
    }

    /**
     *
     */
    public void onKwPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "一号库位", "二号库位", "三号库位"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                kw.setText(item);
            }
        });
        picker.show();
    }
    /**
     *
     */
    public void onWldwPicker(View view){
        OptionPicker picker = new OptionPicker(this, new String[]{
                "远光软件", "中国移动", "中国航天"
        });
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                wldw.setText(item);
            }
        });
        picker.show();
    }

    /**
     *添加物资
     */
    public void onAdd(View view){
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        Cursor cursor = db.query(SQLitConstant.TABLE_GOODS,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            Toast.makeText(this,"当前系统没有物资信息，请先新增！",Toast.LENGTH_LONG).show();
            db.close();
            return;
        }
        db.close();
        Intent intent = new Intent(this,SelectGoodsActivity.class);
        startActivityForResult(intent,1);
    }

    /**
     * 提交入库
     * @param view
     */
    public void doIn(View view){
        /**
         * 1.获取返回的选择结果
         * 2.判断选择结果 如果为空就   return
         * 3.根据选择结果 物资编码 字段从数据库取到对应物资信息  （物资信息表）
         * 4.构建库从台账 然后逐个插入
         * 5.构建库存单据 逐个插入
         */
        if(resultlists==null||resultlists.size()<1){
            Toast.makeText(this, "请添加要入库的物资信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        List<KctzVO> kctzList = new ArrayList<>();
        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
        String ywid = getYwid();
        double zje = 0;
        for (int i = 0; i < resultlists.size(); i++) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String time = formatter.format(curDate);

            String wzbm = resultlists.get(i).get("wzbm");
            Log.d(TAG, "doIn: wzbm");
            Cursor cursor = db.query(SQLitConstant.TABLE_GOODS,null," WZBM = ? ",new String[]{wzbm},null,null,null);
            List<GoodsVO> goodsList = DBManger.cursorToGoodsList(cursor);

            if ( goodsList==null||goodsList.size()==0){
                Toast.makeText(this, "新增入库失败！", Toast.LENGTH_SHORT).show();
                return;
            }
            KctzVO kctzVO = new KctzVO();
            kctzVO.setWzbm(goodsList.get(0).getWzbm());
            kctzVO.setWzmc(goodsList.get(0).getWzmc());
            kctzVO.setWzlx(goodsList.get(0).getWzlx());
            kctzVO.setJldw(goodsList.get(0).getJldw());
            kctzVO.setGgxh(goodsList.get(0).getGgxh());
            kctzVO.setScrq(goodsList.get(0).getScrq());
            kctzVO.setBzq(goodsList.get(0).getBzq());
            kctzVO.setCd(goodsList.get(0).getCd());
            kctzVO.setDj(goodsList.get(0).getDj());
            kctzVO.setSize(goodsList.get(0).getSize()*Integer.valueOf(resultlists.get(i).get("num")));
            kctzVO.setSl(resultlists.get(i).get("num"));
            kctzVO.setBz(bz.getText().toString());
            kctzVO.setJbr(jbr.getText().toString());
            kctzVO.setCk(ck.getText().toString());
            kctzVO.setKw(kw.getText().toString());
            kctzVO.setYwrq(djrq.getText().toString());
            kctzVO.setYwmc(goodsList.get(0).getWzmc()+djlx.getText().toString());
            kctzVO.setYwid(ywid);
            kctzVO.setYwfx("入库");
            kctzVO.setTime(time);
            zje += Integer.valueOf(kctzVO.getSl())*kctzVO.getDj();  //计算总金额
            kctzList.add(kctzVO);
        }

        //库存单据入库  插入数据
        ContentValues kcdjValues = getKcdjContentValues(ywid, zje);
        long kcdjResult = db.insert(SQLitConstant.TABLE_KCDJ,null,kcdjValues);


        Boolean tag = true;
        for (int i = 0; i < kctzList.size(); i++) {
            long kctzResult = db.insert(SQLitConstant.TABLE_KCTZ,null,kctzList.get(i).getContentValues());
//            //更新库从明细
//            Cursor cursor = db.rawQuery("SELECT * FROM KCMX WHERE WZBM = ?",
//                    new String[]{(String)kctzList.get(i).getContentValues().get("WZBM")});
//            boolean isExist = false;
//            while (cursor.moveToLast()){
//                isExist = true;
//
//            }

            if (kcdjResult == -1||kctzResult == -1){
                Log.d(TAG, "doIn: kcdjResult : "+ kcdjResult+"  kctzResult: "+kctzResult);
                tag = false;
            }
       }
        if (tag){
            initInput();
            Toast.makeText(this,"提交入库成功！",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"提交入库失败！",Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

    /**
     * 获取库存单据插入数据格式  ContentValues
     * @param ywid
     * @param zje
     */
    private ContentValues getKcdjContentValues(String ywid, double zje) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);

        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCDJ_DJBM,djbh.getText().toString());
        values.put(SQLitConstant.KCDJ_DJLX,djlx.getText().toString());
        values.put(SQLitConstant.KCDJ_WLDW,wldw.getText().toString());
        values.put(SQLitConstant.KCDJ_DJZT,"待审核");
        values.put(SQLitConstant.KCDJ_CK,ck.getText().toString());
        values.put(SQLitConstant.KCDJ_KW,kw.getText().toString());
        values.put(SQLitConstant.KCDJ_DCLR,"whieenz");
        values.put(SQLitConstant.KCDJ_ZDR,jbr.getText().toString());
        values.put(SQLitConstant.KCDJ_BZ,bz.getText().toString());
        values.put(SQLitConstant.KCDJ_YWID,ywid);
        values.put(SQLitConstant.KCDJ_YWMC,djlx.getText().toString());
        values.put(SQLitConstant.KCDJ_YWFX,"入库");
        values.put(SQLitConstant.KCDJ_YWRQ,djrq.getText().toString());
        values.put(SQLitConstant.KCDJ_ZDRQ,djrq.getText().toString());
        values.put(SQLitConstant.KCDJ_TIME,time);
        values.put(SQLitConstant.KCDJ_ZJE,zje);


        return  values;
    }

    /**
     * 根据当前时间获得ywid
     * @return
     */
    private String getYwid(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);
        return time;

    }

    /***
     * 初始化输入
     */
    private void initInput(){
        djbh.setText("");
        djlx.setText("");
        djrq.setText("");
        jbr.setText("");
        bz.setText("");
        ck.setText("");
        kw.setText("");
        resultBottom.setVisibility(View.GONE);
        firstBottom.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    List list = bundle.getParcelableArrayList("result");
                    if(list==null){
                        return;
                    }
                    resultlists = (List<Map<String, String>>)list.get(0);
                    if (resultlists == null){
                        return;
                    }
                    int sum = 0;
                    for (int i = 0; i < resultlists.size() ; i++) {
                        String str = resultlists.get(i).get("num");
                        sum += Integer.valueOf(str);
                    }
                    if (resultlists.size()>0){
                        resultBottom.setVisibility(View.VISIBLE);
                        firstBottom.setVisibility(View.GONE);
                        resultTv.setText("合计：已选 "+resultlists.size()+" 种，总数量 " + sum );
                    }
                }
                break;
            default:
        }
    }
}
