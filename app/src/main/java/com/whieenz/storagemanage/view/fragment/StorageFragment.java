package com.whieenz.storagemanage.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.view.activity.AddGoodsActivity;
import com.whieenz.storagemanage.view.activity.AddInStorageActivity;
import com.whieenz.storagemanage.view.activity.AddOutStorageActivity;
import com.whieenz.storagemanage.view.activity.KcdjActivity;
import com.whieenz.storagemanage.view.activity.KcmxActivity;
import com.whieenz.storagemanage.view.activity.KctzActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heziwen on 2017/3/7.
 */

public class StorageFragment extends Fragment implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.drawable.new_goods_btn,R.drawable.goods_management_btn,
            R.drawable.new_rk_btn,R.drawable.new_ck_btn,R.drawable.new_pd_btn,
            R.drawable.new_db_btn,R.drawable.bddj_btn,R.drawable.djmx_btn,
            R.drawable.server_dj_btn};
    private String[] iconName = {"新增物资","物资管理","新增入库","新增出库","库存盘点","新增调拨","库存台账","库存单据","历史记录"};
    private SimpleAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.stroage,container,false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = (GridView) getView().findViewById(R.id.gv_gridview);
        //1.准备数据源
        //2.新建适配器（SimpleAdepter）
        //3.GridView加载适配器
        //4.配置监听器 （OnItemClickListener）
        dataList = new ArrayList<Map<String,Object>>();
        adapter = new SimpleAdapter(getActivity(),getData(),R.layout.gridview_item,new String[]{"image","text"},
                new int[]{R.id.image,R.id.text});
        gridView.setAdapter(adapter);
        //加载监听器
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }

        return  dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position){
            case 0:
                startActivity(new Intent(getActivity(),AddGoodsActivity.class));
                break;
            case 1:
                startActivity(new Intent(getActivity(),KcmxActivity.class));
                break;
            case 2:
                startActivity(new Intent(getActivity(),AddInStorageActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(),AddOutStorageActivity.class));
                break;
            case 6:
                startActivity(new Intent(getActivity(), KctzActivity.class));
                break;
            case 7:
                startActivity(new Intent(getActivity(),KcdjActivity.class));
                break;
        }
    }
}
