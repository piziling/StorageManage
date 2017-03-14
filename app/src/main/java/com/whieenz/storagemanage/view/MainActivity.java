package com.whieenz.storagemanage.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;

import com.whieenz.storagemanage.R;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView mainTitle;
    private NoScrollViewPager mainViewPage;
    private List<Fragment> mainTabs = new ArrayList<Fragment>();
    private String[] mTitles = new String[]{
            "我的工作台","仓库主页","设置"
    };

    private FragmentPagerAdapter mAdater;
    private List<ChangeColorInconWithText> mTabIndicators = new ArrayList<ChangeColorInconWithText>();

    private ChangeColorInconWithText me;
    private ChangeColorInconWithText storage;
    //private ChangeColorInconWithText pic;
    private ChangeColorInconWithText setting;

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
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mainViewPage = (NoScrollViewPager) findViewById(R.id.main_viewPage);
        mainTitle = (TextView) findViewById(R.id.tv_main_title);
        MeFragment meFragment = new MeFragment();
        StorageFragment storageFragment = new StorageFragment();
//        PicFragment picFragment = new PicFragment();
        SettingFragment settingFragment =new SettingFragment();
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
        mainTitle.setText(mTitles[i]);
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

