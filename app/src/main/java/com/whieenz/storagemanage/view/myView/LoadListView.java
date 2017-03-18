package com.whieenz.storagemanage.view.myView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.whieenz.storagemanage.R;

/**
 * Created by heziwen on 2017/3/17.
 */

public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;  //底部布局
    private int totalItemCount; //总数量
    private int lastVisibleItem; //最后一个可见的item
    private boolean isLoading;
    private ILoadListener iLoadListener;


    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 添加底部布局
     * @param context
     */
    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_item,null);
        footer.findViewById(R.id.load_item).setVisibility(View.GONE);  //初次进入为隐藏
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        if(totalItemCount == lastVisibleItem &&scrollState ==SCROLL_STATE_IDLE){
            if(!isLoading){
                isLoading = true;
                footer.findViewById(R.id.load_item).setVisibility(View.VISIBLE);
                //加载更多
                //1.获取数据
                //2.接口回调
                iLoadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        //获取最后一个可见Item的值
        this.lastVisibleItem = firstVisibleItem+visibleItemCount;
        this.totalItemCount = totalItemCount;

    }
    public void loadComplete(){
        isLoading = false;
        footer.findViewById(R.id.load_item).setVisibility(View.GONE);
    }
    /**
     * 设置 给接口处传值
     * @param iLoadListener
     */
    public void setInterface(ILoadListener iLoadListener){
        this.iLoadListener = iLoadListener;
    }
    /**
     * 加载更多数据的回调接口
     */
    public interface  ILoadListener{
        public void onLoad();
    }
}
