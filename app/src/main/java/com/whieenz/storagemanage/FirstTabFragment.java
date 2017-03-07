package com.whieenz.storagemanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by whieenz on 2017/3/4.
 *
 * 第一个标签  聊天
 */
public class FirstTabFragment extends Fragment implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    private ReFlashListView listView;
    private ArrayAdapter<String > arry_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return inflater.inflate(R.layout.tab01,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ReFlashListView)getView().findViewById(R.id.lv_listview);
        datalist = new ArrayList<Map<String,Object>>();
        simp_adapter = new SimpleAdapter(getActivity(),getData(),R.layout.dj_item,new String[]{"djbm","djlx","time","djzt"},new int[]{R.id.tv_item_djbm,R.id.tv_item_djlx,R.id.tv_item_time,R.id.tv_item_djzt});
        //3.视图（ListView）加载适配器
        listView.setAdapter(simp_adapter);
        //加载监听器
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private List<Map<String,Object>> getData(){
        for (int i = 0; i < 20; i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("djbm","单据编号：DJ"+i*1000000+i*i*5);
            map.put("djlx","单据类型：生产出库单");
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sDateFormat.format(new java.util.Date());
            map.put("time",date);
            map.put("djzt","新任务");
            datalist.add(map);
        }
        return  datalist;
    }
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

        switch (i){
            case  SCROLL_STATE_FLING:
                Log.i(TAG, "用户在离开屏幕前，用力滑了一下，视图任然在滑动");

//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("pic",R.mipmap.ic_launcher);
//                map.put("text","新增内容"+num);
//                datalist.add(map);
                simp_adapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                break;
            case  SCROLL_STATE_TOUCH_SCROLL:
                break;
        }

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = listView.getItemAtPosition(i)+"";
        Toast.makeText(getActivity(),"position"+i+"text="+text,Toast.LENGTH_SHORT).show();
    }
}
