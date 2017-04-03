package com.whieenz.storagemanage.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.base.UserInfo;
import com.whieenz.storagemanage.view.activity.CkInfoActivity;
import com.whieenz.storagemanage.view.activity.GldxActivity;
import com.whieenz.storagemanage.view.activity.PassWordActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by heziwen on 2017/3/7.
 */

public class SettingFragment extends Fragment implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{

    private ListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> datalist;
    private TextView userName;
    private TextView userJob;

    private int[] imageList = {R.drawable.setting_password, R.drawable.setting_manage,
            R.drawable.setting_user_add,R.drawable.setting_parameter,R.drawable.setting_parameter,
            R.drawable.setting_manage,R.drawable.setting_help};
    private String[] textList = {"密码管理","物资类型","往来单位","计量单位","入库类型","出库类型","关于"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting,container,false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView)getView().findViewById(R.id.setting_list);
        userName =(TextView)getView().findViewById(R.id.tv_user_name);
        userJob =(TextView) getView().findViewById(R.id.tv_user_job);
        datalist = new ArrayList<Map<String,Object>>();
        simp_adapter = new SimpleAdapter(getActivity(),getData(),R.layout.setting_item,new String[]{"image","text"},new int[]{R.id.setting_image,R.id.setting_text});
        //3.视图（ListView）加载适配器
        listView.setAdapter(simp_adapter);
        //加载监听器
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);

        //加载用户信息
        Bundle bundle = getArguments();
        UserInfo userInfo = (UserInfo) bundle.getSerializable("userInfo");
        userName.setText(userInfo.getName());
        userJob.setText(userInfo.getJob());

    }

    private List<Map<String,Object>> getData(){
        for (int i = 0; i < imageList.length; i++) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",imageList[i]);
            map.put("text",textList[i]);
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
        switch (i){
            case 0:
                Intent intent0 = new Intent(getActivity(), PassWordActivity.class);
                startActivity(intent0);
                break;
            case 1:
                startGldxActivity("WZFL");
                break;
            case 2:
                startGldxActivity("WLDW");
                break;
            case 3:
                startGldxActivity("JLDW");
                break;
            case 4:
                startGldxActivity("RKLX");
                break;
            case 5:
                startGldxActivity("CKLX");
                break;
        }
    }

    private void startGldxActivity(String tag) {
        Intent intent = new Intent(getActivity(), GldxActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }
}
