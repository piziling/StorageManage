package com.whieenz.storagemanage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

/**
 * Created by heziwen on 2017/3/7.
 */

public class MeFragment extends Fragment {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private TextView mfirstTopTv;
    private TextView msecondTopTv;
    private TextView mthirdTopTv;
    private LinearLayout mfirstTopll;
    private int mScreen1_3;
    private ImageView mTabline;

    private int mCurrentPageIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.me,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTabline();

        initView();

    }

    private void initTabline() {
        mTabline =  (ImageView) getView().findViewById(R.id.iv_top_line);
        Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels/3;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);

    }

    /**
     * 初始化View
     */
    public void initView(){
        mViewPager = (ViewPager) getView().findViewById(R.id.id_viewPage);
        mfirstTopTv = (TextView)getView().findViewById(R.id.tv_top_first);
        msecondTopTv = (TextView)getView().findViewById(R.id.tv_top_secont);
        mthirdTopTv = (TextView)getView().findViewById(R.id.tv_top_third);

        addBadgeView(mfirstTopTv,5);


        mDatas = new ArrayList<Fragment>();
        FirstTabFragment tab01 =new FirstTabFragment();
        SecondTabFragment tab02 = new SecondTabFragment();
        ThirdTabFragment tab03 = new ThirdTabFragment();
        mDatas.add(tab01);
        mDatas.add(tab02);
        mDatas.add(tab03);
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mDatas.get(position);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline.getLayoutParams();

                if(mCurrentPageIndex == 0 && position==0){ //0->1
                    lp.leftMargin = (int) (positionOffset*mScreen1_3 + mCurrentPageIndex*mScreen1_3);
                }else if(mCurrentPageIndex == 1 && position==0){//1->0
                    lp.leftMargin = (int) ((positionOffset-1)*mScreen1_3 + mCurrentPageIndex*mScreen1_3);
                }else if(mCurrentPageIndex == 1 && position==1){//1->2
                    lp.leftMargin = (int) (positionOffset*mScreen1_3 + mCurrentPageIndex*mScreen1_3);
                }else if(mCurrentPageIndex == 2 && position==1){//2->1
                    lp.leftMargin = (int) ((positionOffset-1)*mScreen1_3 + mCurrentPageIndex*mScreen1_3);
                }
                mTabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position){
                    case 0:
                        //改变字体颜色 为绿色
                        mfirstTopTv.setTextColor(Color.parseColor("#0bb3d0"));
                        break;
                    case 1:
                        msecondTopTv.setTextColor(Color.parseColor("#0bb3d0"));
                        break;
                    case 2:
                        mthirdTopTv.setTextColor(Color.parseColor("#0bb3d0"));
                        break;
                }

                mCurrentPageIndex =position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 让顶端topbar的字体颜色恢复黑色
     */
    private void resetTextView() {
        mfirstTopTv.setTextColor(Color.BLACK);
        msecondTopTv.setTextColor(Color.BLACK);
        mthirdTopTv.setTextColor(Color.BLACK);
    }
    private void addBadgeView(View view,int num){
        new QBadgeView(getActivity()).bindTarget(view).setBadgeGravity(Gravity.END | Gravity.TOP).
                setBadgeNumber(num).setBadgeTextSize(8,true).setBadgePadding(3f,true);

    }
}
