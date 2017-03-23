package com.whieenz.storagemanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.CkInfoVO;
import com.whieenz.storagemanage.base.MyApp;
import com.whieenz.storagemanage.view.myView.LoadListView;

import java.util.ArrayList;



/**
 * Created by heziwen on 2017/3/23.
 */

public class CkInfoActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private LoadListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList datalist;
    private MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ckxx);
        myApp = (MyApp) getApplication();
        initView();

    }

    /**
     * 初始化界面
     */
    private void initView() {
        listView = (LoadListView) findViewById(R.id.ckxx_list);
        datalist = new ArrayList();
        if(myApp.getCkxxArray()!=null){
            for (int i = 0; i <myApp.getCkxxArray().size() ; i++) {
                CkInfoVO ckinfo = (CkInfoVO)myApp.getCkxxArray().get(i);
                String name = ckinfo.getCkmc();
                String size = ckinfo.getSize();
                datalist.add( name+" | "+size+" m³ ");
            }
        }

        if(datalist.size()>0){
            arrayAdapter = new ArrayAdapter<String>(this,R.layout.base_item,R.id.base_item_text,datalist);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(this);
            listView.setOnScrollListener(this);
        }

    }

    public void onAdd(View view){
        Intent intent = new Intent(this,AddCkActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    ArrayList<String> arrayList =data.getStringArrayListExtra("new");
                    for (int i = 0; i < arrayList.size(); i++) {
                        datalist.add(arrayList.get(i));
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
