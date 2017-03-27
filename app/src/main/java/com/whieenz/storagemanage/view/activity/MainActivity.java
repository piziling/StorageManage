package com.whieenz.storagemanage.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.whieenz.storagemanage.R;
import com.whieenz.storagemanage.view.myView.ChangeColorInconWithText;
import com.whieenz.storagemanage.view.fragment.MeFragment;
import com.whieenz.storagemanage.view.myView.NoScrollViewPager;
import com.whieenz.storagemanage.view.fragment.SettingFragment;
import com.whieenz.storagemanage.view.fragment.StorageFragment;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "FragmentActivity";
    private NoScrollViewPager mainViewPage;
    private List<Fragment> mainTabs = new ArrayList<Fragment>();

    private FragmentPagerAdapter mAdater;
    private List<ChangeColorInconWithText> mTabIndicators = new ArrayList<ChangeColorInconWithText>();

    private ChangeColorInconWithText me;
    private ChangeColorInconWithText storage;
    //private ChangeColorInconWithText pic;
    private ChangeColorInconWithText setting;


    MeFragment meFragment = new MeFragment();
    StorageFragment storageFragment = new StorageFragment();
    //        PicFragment picFragment = new PicFragment();
    SettingFragment settingFragment =new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
        mainViewPage.setAdapter(mAdater);
        mainViewPage.setCurrentItem(1);

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mAdater = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mainTabs.get(position);
            }

            @Override
            public int getCount() {
                return mainTabs.size();
            }
        };

        //加载登录信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //UserInfo userInfo = (UserInfo) bundle.getSerializable("userInfo");
        settingFragment.setArguments(bundle);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mainViewPage = (NoScrollViewPager) findViewById(R.id.main_viewPage);

        mainTabs.add(meFragment);
        mainTabs.add(storageFragment);
//        mainTabs.add(picFragment);
        mainTabs.add(settingFragment);

        initIndicator();

    }

    /**
     * 初始化导航栏
     */
    private void initIndicator() {
        me = (ChangeColorInconWithText) findViewById(R.id.indicator_me);
        storage = (ChangeColorInconWithText) findViewById(R.id.indicator_storage);
       // pic = (ChangeColorInconWithText) findViewById(R.id.indicator_pic);
        setting = (ChangeColorInconWithText) findViewById(R.id.indicator_setting);
        mTabIndicators.add(me);
        mTabIndicators.add(storage);
       // mTabIndicators.add(pic);
        mTabIndicators.add(setting);
        me.setOnClickListener(this);
        storage.setOnClickListener(this);
       // pic.setOnClickListener(this);
        setting.setOnClickListener(this);
        storage.setIconAlpha(1.0f);



    }


    public void cancel(View view){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("注销当前用户？");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        resetOtherTabs();
        switch (view.getId()){
            case R.id.indicator_me:
                whenIndicatorChange(0);
                addBadgeView(me,8);
                break;
            case R.id.indicator_storage:
                whenIndicatorChange(1);
                break;
//            case R.id.indicator_pic:
//                whenIndicatorChange(2);
//                break;
            case R.id.indicator_setting:
                whenIndicatorChange(2);

                break;
        }
    }

    /**
     * 底部导航栏变化时
     */
    private void whenIndicatorChange(int i) {
        mTabIndicators.get(i).setIconAlpha(1.0f);
        mainViewPage.setCurrentItem(i);
    }

    /**
     * 重置其他的颜色
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    /**
     * 添加红色消息提示 BadgeView
     * @param view
     * @param num
     */
    public  void addBadgeView(View view,int num){//.setBadgeGravity(Gravity.CENTER | Gravity.TOP)

        int x = me.getWidth()/2-30;
        new QBadgeView(this).bindTarget(view).
                setBadgeNumber(num).setBadgeTextSize(8,true).setBadgePadding(4f,true).
                setGravityOffset(x,2,false).setShowShadow(false).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {

            }
        });


    }
}

