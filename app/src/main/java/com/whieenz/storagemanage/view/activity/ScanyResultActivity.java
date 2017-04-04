package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.utls.MyUntls;

import java.util.List;

import static android.R.attr.data;
import static com.whieenz.storagemanage.R.id.goods_value_bz;
import static com.whieenz.storagemanage.R.id.goods_value_bzq;
import static com.whieenz.storagemanage.R.id.goods_value_cd;
import static com.whieenz.storagemanage.R.id.goods_value_ggxh;
import static com.whieenz.storagemanage.R.id.goods_value_jldw;
import static com.whieenz.storagemanage.R.id.goods_value_scrq;
import static com.whieenz.storagemanage.R.id.goods_value_size;
import static com.whieenz.storagemanage.R.id.goods_value_wzbm;
import static com.whieenz.storagemanage.R.id.goods_value_wzlx;
import static com.whieenz.storagemanage.R.id.goods_value_wzmc;

/**
 * Created by whieenz on 2017/4/4.
 */

public class ScanyResultActivity extends Activity {
    private TextView wzbmTV;
    private TextView wzmcTV;
    private TextView ggxhTV;
    private TextView wzlxTV;
    private TextView jldwTV;
    private TextView wzdjTV;
    private TextView sizeTV;
    private TextView scrqTV;
    private TextView bzqTV;
    private TextView cdTV;
    private TextView bzTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scany_result);
        initView();

    }

    private void initView() {
        wzbmTV = (TextView) findViewById(goods_value_wzbm);
        wzmcTV = (TextView) findViewById(goods_value_wzmc);
        wzlxTV = (TextView) findViewById(goods_value_wzlx);
        ggxhTV = (TextView) findViewById(goods_value_ggxh);
        jldwTV = (TextView) findViewById(goods_value_jldw);
        sizeTV = (TextView) findViewById(goods_value_size);
        scrqTV = (TextView) findViewById(goods_value_scrq);
        bzqTV = (TextView) findViewById(goods_value_bzq);
        cdTV = (TextView) findViewById(goods_value_cd);
        bzTV = (TextView) findViewById(goods_value_bz);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String result = bundle.get("result").toString();
        List<String> resultList = MyUntls.splitString(result);
        wzbmTV.setText(resultList.get(1));
        wzmcTV.setText(resultList.get(2));
        wzlxTV.setText(resultList.get(3));
        ggxhTV.setText(resultList.get(4));
        jldwTV.setText(resultList.get(5));
        sizeTV.setText(resultList.get(6));
        scrqTV.setText(resultList.get(7));
        bzqTV.setText(resultList.get(8));
        cdTV.setText(resultList.get(9));
        bzTV.setText(resultList.get(10));


    }


}
