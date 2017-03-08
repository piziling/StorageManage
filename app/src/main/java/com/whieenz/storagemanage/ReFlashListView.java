package com.whieenz.storagemanage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by heziwen on 2017/3/1.
 */

class ReFlashListView extends ListView implements AbsListView.OnScrollListener{
    private static final String TAG = "自定义VIEW";
    
    private View header; //顶部布局文件
    private int headerHeigth;//顶部布局文件的高度
    private int firstVisibleItem;//当前第一个可见Item的位置
    private boolean isRemark; //标记 ，当前是在listview的最顶端按下的
    private int startY; //按下时的Y坐标
    private int scrollState;//当前滚动状态
    private int state = 0; //当前状态

    final  int NONE = 0;//正常状态
    final  int PULL = 1;//提示下拉状态
    final  int RELESE = 2;//提示释放状态
    final  int REFLASHING = 3;//刷新状态
    public ReFlashListView(Context context) {
        super(context);
        initView(context);
    }

    public ReFlashListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReFlashListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化界面，添加顶部文件到listView
     * @param context
     */
    private void initView(Context  context){
        LayoutInflater inflater = LayoutInflater.from(context);
        header = inflater.inflate(R.layout.header_layout,null);
        //设置高度
        measureView(header);
        //获取高度
        headerHeigth = header.getMeasuredHeight();
        //隐藏header
        topPadding(-headerHeigth);

        this.addHeaderView(header);
        this.setOnScrollListener(this);

    }

    /**
     * 设置上边距
     * @param topPadding
     */
    private void topPadding(int topPadding){
        header.setPadding(header.getPaddingLeft(),topPadding,
                header.getPaddingRight(),header.getPaddingBottom());
    }

    /**
     * 通知父布局，占用宽 高
     * @param view
     */
    private void measureView(View view){
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight = p.height;
        if(tempHeight>0){
            height = MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }else{
            height = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        view.measure(width,height);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        this.scrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem =firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
       switch (ev.getAction()){
            case  MotionEvent.ACTION_DOWN:  //按下
                if(firstVisibleItem==0){
                    isRemark = true;
                    startY = (int) ev.getY();
                }
                break;
           case  MotionEvent.ACTION_MOVE:
                    onMove(ev);
               break;
           case  MotionEvent.ACTION_UP:
                    if(state == RELESE){
                        state = REFLASHING;
                        reflashViewByState();
                        //此处加载数据
                    }else if(state == PULL){
                        state = NONE;
                        isRemark = false;
                        reflashViewByState();
                    }
               break;
        }
        return super.onTouchEvent(ev);
    }

    private void onMove(MotionEvent ev){
        if(!isRemark){
            return;
        }
        int tempY = (int) ev.getY();
        int space = tempY-startY;
        int topPadding = space-headerHeigth;
        switch (state){
            case NONE:
                if(space>0){
                    state = PULL;
                    reflashViewByState();
                }
                break;
            case PULL:
                if(space>headerHeigth){
                    state = RELESE;
                    reflashViewByState();
                }
                topPadding(topPadding);
                break;
            case RELESE:
                if(space<headerHeigth){
                    state = PULL;
                    reflashViewByState();
                }else if(space<=0){
                    state = NONE;
                    isRemark =false;
                    reflashViewByState();
                }
                topPadding(topPadding);
                break;
        }
        Log.d(TAG, "onMove : state : "+state+"StartY : "+startY+"tempY"+tempY+" space : "+
                space+" headerHeigth : "+headerHeigth+"scrollState : "+scrollState);
    }

    /**
     * 根据状态刷新控件  （headerListView）
     */
    private void reflashViewByState(){
        TextView tip = (TextView) header.findViewById(R.id.tip);
        ImageView arrow = (ImageView) header.findViewById(R.id.arrow);
        ProgressBar progress = (ProgressBar) header.findViewById(R.id.progress);
        RotateAnimation anim = new RotateAnimation(0,180,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
                        anim.setDuration(500);
                        anim.setFillAfter(true);

        RotateAnimation anim1 = new RotateAnimation(0,180,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
                        anim1.setDuration(500);
                        anim1.setFillAfter(true);


        switch (state){
            case NONE:
                topPadding(-headerHeigth);
                break;
            case PULL:
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("下拉刷新！");
                arrow.clearAnimation();
                arrow.setAnimation(anim1);
                break;
            case RELESE:
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("松开立即刷新！");
                arrow.clearAnimation();
                arrow.setAnimation(anim);
                break;
            case REFLASHING:
                topPadding(headerHeigth);
                arrow.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                tip.setText("正在刷新...");
                arrow.clearAnimation();
                break;
        }
    }
}
