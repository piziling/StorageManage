package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.GoodsVO;
import com.whieenz.storagemanage.base.KcmxVO;
import com.whieenz.storagemanage.base.KctzVO;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.utls.DBManger;
import com.whieenz.storagemanage.utls.MyUntls;
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
import static com.whieenz.storagemanage.R.id.time;
import static com.whieenz.storagemanage.utls.DBManger.QueryDataBySql;

/**
 * Created by heziwen on 2017/310.
 */

public class AddInStorageActivity extends Activity {
    private MyApp myApp;
    private TextView djrq;
    private Button djlx;
    private Button wldw;
    private Button add;
    private Button ck;
    private EditText djbh;
    private Button jbr;
    private Button dclr;
    private EditText bz;
    private View firstBottom;
    private View resultBottom;
    private TextView  resultTv;
    private List<Map<String, String>> resultlists;
    private ArrayList userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_storage);
        myApp = (MyApp)getApplication();
        initView();
    }

    private void initView() {
        djrq =(TextView) findViewById(R.id.in_value_djrq);
        resultTv =(TextView) findViewById(R.id.tv_result);
        djbh =(EditText) findViewById(R.id.in_value_djbh);
        jbr =(Button) findViewById(R.id.in_value_jbr);
        dclr =(Button) findViewById(R.id.in_value_dclr);
        bz =(EditText) findViewById(R.id.in_value_bz);
        ck = (Button) findViewById(R.id.in_value_ck);
        wldw = (Button) findViewById(R.id.in_value_wldw);
        djlx = (Button) findViewById(R.id.in_value_djlx);
        add = (Button) findViewById(R.id.in_add);
        firstBottom =  findViewById(R.id.bottom_first_layout);
        resultBottom = findViewById(R.id.bottom_result_layout);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        djrq.setText(str);

        jbr.setText(myApp.getUserInfo().getName());
        ck.setText(myApp.getmStorage().getCkmc());

        userInfos = new ArrayList();
        for (int i = 0; i < myApp.getUserArray().size(); i++) {
            UserInfo user = (UserInfo)myApp.getUserArray().get(i);
            userInfos.add(user.getName());
        }
    }

    /**
     * 检查输入内容是否为空
     * @return
     */
    private boolean checkIsNull() {

        if ( djlx.getText().toString().equals("")) {
            Toast.makeText(this,"类型不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( djrq.getText().toString().equals("")) {
            Toast.makeText(this,"日期不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( ck.getText().toString().equals("")) {
            Toast.makeText(this,"仓库不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( jbr.getText().toString().equals("")) {
            Toast.makeText(this,"经办人不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( dclr.getText().toString().equals("")) {
            Toast.makeText(this,"审核人不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( wldw.getText().toString().equals("")) {
            Toast.makeText(this,"往来单位不能为空！",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 经办人信息选择  // 默认登录用户
     */
    public void onJbrPicker(View view){
        OptionPicker picker = new OptionPicker(this, userInfos);
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                jbr.setText(item);
            }
        });
        picker.show();
    }
    /**
     * 待处理人选择
     */
    public void onDclrPicker(View view){
        OptionPicker picker = new OptionPicker(this, userInfos);
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                dclr.setText(item);
            }
        });
        picker.show();
    }

    /**
     * 单据类型选择器
     */
    public void onDjlxPicker(View view){
        OptionPicker picker = new OptionPicker(this, myApp.getRklxArray());
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                djlx.setText(item);
            }
        });
        picker.show();
    }
    /**
     * 仓库选择器
     */
    public void onCkPicker(View view){
        OptionPicker picker = new OptionPicker(this, myApp.getCkmcArray());
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int index, String item) {
                ck.setText(item);
            }
        });
        picker.show();
    }


    /**
     *  往来单位选择器
     */
    public void onWldwPicker(View view){
        OptionPicker picker = new OptionPicker(this, myApp.getWldwArray());
        picker.setCycleDisable(true);
        picker.setLineVisible(false);
        //picker.setShadowVisible(true);
        picker.setTextSize(18);
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
//        SQLiteDatabase db = DBManger.getIntance(this).getWritableDatabase();
//        Cursor cursor = db.query(SQLitConstant.TABLE_GOODS,null,null,null,null,null,null);
//        if (cursor.getCount()==0){
//            Toast.makeText(this,"当前系统没有物资信息，请先新增！",Toast.LENGTH_LONG).show();
//            db.close();
//            return;
//        }
//        db.close();
        if ( ck.getText().toString().equals("")) {
            Toast.makeText(this,"请选择仓库！",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this,SelectGoodsActivity.class);
        String ckName = ck.getText().toString();
        intent.putExtra("ck",ckName);
        intent.putExtra("tag","RK");
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
        if(!checkIsNull()){
            return;
        }
        //判断单据编号是否存在
        if(DBManger.isUniqueExist(SQLitConstant.TABLE_KCDJ,"DJBM =?",
                new String[]{djbh.getText().toString()})){
            Toast.makeText(this, "单据编号已存在！", Toast.LENGTH_SHORT).show();
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
            Log.d(TAG, "doIn: wzbm"+wzbm);
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
            kctzVO.setSl(Integer.valueOf(resultlists.get(i).get("num")));
            kctzVO.setZje(Integer.valueOf(resultlists.get(i).get("num"))*goodsList.get(0).getDj());
            kctzVO.setBz(bz.getText().toString());
            kctzVO.setJbr(jbr.getText().toString());
            kctzVO.setCk(ck.getText().toString());
            kctzVO.setYwrq(djrq.getText().toString());
            kctzVO.setYwmc(goodsList.get(0).getWzmc()+djlx.getText().toString());
            kctzVO.setTzbm("RK_"+ywid+i);
            kctzVO.setYwid(ywid);
            kctzVO.setYwfx("入库");
            kctzVO.setDjzt("待审核");
            kctzVO.setTime(time);
            zje += Integer.valueOf(kctzVO.getSl())*kctzVO.getDj();  //计算总金额
            kctzList.add(kctzVO);
        }

        //库存单据入库  插入数据
        ContentValues kcdjValues = getKcdjContentValues(ywid, zje);
        long kcdjResult = db.insert(SQLitConstant.TABLE_KCDJ,null,kcdjValues);

        Boolean tag = true;
        if (kcdjResult == -1){
            tag = false;
        }
        for (int i = 0; i < kctzList.size(); i++) {
            long kctzResult = db.insert(SQLitConstant.TABLE_KCTZ,null,kctzList.get(i).getContentValues());
//            //更新库从明细信息
//            updateKcmxInfo(kctzList, db, i);

            if (kctzResult == -1 ){
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
     * 更新库从明细信息
     * @param kctzList  库从台账
     * @param db  数据库连接
     * @param i
     */
    private void updateKcmxInfo(List<KctzVO> kctzList, SQLiteDatabase db, int i) {
        //更新库从明细
        String sql = "SELECT * FROM KCMX WHERE WZBM = ? AND CK = ?";
        Cursor cursor = DBManger.QueryDataBySql(db,sql,
                new String[]{kctzList.get(i).getWzbm(), kctzList.get(i).getCk()});
        KcmxVO kcmxVO = new KcmxVO();
        Log.e(TAG, "updateKcmxInfo: cursor : " +cursor.getCount());
        boolean isExist = false;
        if (cursor.moveToNext()){
            Log.e(TAG, "cursor.moveToNext() : " );
            isExist = true;
            kcmxVO.getKcmxVOfromCursor(cursor);
        }

        if (isExist&&kcmxVO!=null&&!kcmxVO.getWzbm().equals("")){ //判断是否取到值(同一仓库存在该物资 -->更新)
            ContentValues kcmxValues = new ContentValues();
            kcmxValues.put(SQLitConstant.KCMX_SL,kcmxVO.getSl()+kctzList.get(i).getSl());
            kcmxValues.put(SQLitConstant.KCMX_ZJE,kcmxVO.getZje()+kctzList.get(i).getSl()*kctzList.get(i).getDj());
            kcmxValues.put(SQLitConstant.KCMX_SIZE,kcmxVO.getSize()+kctzList.get(i).getSize());
            db.update(SQLitConstant.TABLE_KCMX,kcmxValues,
                    " WZBM =?  AND  CK =? ",new String[]{kcmxVO.getWzbm(),kcmxVO.getCk()});
        }else {   //同一仓库不存在该物资 -->新增
            ContentValues values = getKcmxContentValues(kctzList, i);
            long kcmxResult = db.insert(SQLitConstant.TABLE_KCMX,null,values);
            if(kcmxResult == -1){
                Toast.makeText(this,"新增库存明细失败！",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 获取库存明细插入数据格式  ContentValues
     * @param kctzList
     * @param i
     * @return
     */
    @NonNull
    private ContentValues getKcmxContentValues(List<KctzVO> kctzList, int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String time = formatter.format(curDate);

        ContentValues values = new ContentValues();
        values.put(SQLitConstant.KCMX_WZBM,kctzList.get(i).getWzbm());
        values.put(SQLitConstant.KCMX_KCBM,kctzList.get(i).getCk()+kctzList.get(i).getWzbm());
        values.put(SQLitConstant.KCMX_WZMC,kctzList.get(i).getWzmc());
        values.put(SQLitConstant.KCMX_WZLX,kctzList.get(i).getWzlx());
        values.put(SQLitConstant.KCMX_GGXH,kctzList.get(i).getGgxh());
        values.put(SQLitConstant.KCMX_JLDW,kctzList.get(i).getJldw());
        values.put(SQLitConstant.KCMX_SCRQ,kctzList.get(i).getScrq());
        values.put(SQLitConstant.KCMX_BZQ,kctzList.get(i).getBzq());
        values.put(SQLitConstant.KCMX_CD,kctzList.get(i).getCd());
        values.put(SQLitConstant.KCMX_DJ,kctzList.get(i).getDj());
        values.put(SQLitConstant.KCMX_SL,kctzList.get(i).getSl());
        values.put(SQLitConstant.KCMX_ZJE,kctzList.get(i).getSl()*kctzList.get(i).getDj());
        values.put(SQLitConstant.KCMX_CK,kctzList.get(i).getCk());
        values.put(SQLitConstant.KCMX_BZ,kctzList.get(i).getBz());
        values.put(SQLitConstant.KCMX_SIZE,kctzList.get(i).getSize());
        values.put(SQLitConstant.KCMX_TIME,time);
        return values;
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
        values.put(SQLitConstant.KCDJ_DJBM,"RK_"+getDjbm());
        values.put(SQLitConstant.KCDJ_DJLX,djlx.getText().toString());
        values.put(SQLitConstant.KCDJ_WLDW,wldw.getText().toString());
        values.put(SQLitConstant.KCDJ_DJZT,"待审核");
        values.put(SQLitConstant.KCDJ_CK,ck.getText().toString());
        values.put(SQLitConstant.KCDJ_DCLR,dclr.getText().toString());
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
     * 获取单据编码
     * @return
     */
    private String getDjbm(){
        return  djbh.getText().toString().equals("")? MyUntls.getUniqueFromTime(""):djbh.getText().toString();
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
        jbr.setText("");
        bz.setText("");
        ck.setText("");
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
