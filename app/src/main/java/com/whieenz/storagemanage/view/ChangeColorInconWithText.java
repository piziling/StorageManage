package com.whieenz.storagemanage.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.whieenz.storagemanage.R;

/**
 * Created by heziwen on 2017/3/6.
 * 自定义View
 */

public class ChangeColorInconWithText extends View {

    private int mColor = 0xFF1E90FF;
    private Bitmap mIconBitmap;
    private String mText = "微信";
    private int mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,
            getResources().getDisplayMetrics());
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;

    private float mAlpha;

    private Rect mIconRect;
    private Rect mTextBound;

    private Paint mTextPaint;
    public ChangeColorInconWithText(Context context) {
        this(context,null);
    }

    public ChangeColorInconWithText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 获取自定义属性的值
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ChangeColorInconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorInconWithText);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch(attr){
                case R.styleable.ChangeColorInconWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(attr);
                    mIconBitmap = drawable.getBitmap();
                 break;
                case  R.styleable.ChangeColorInconWithText_color:
                    mColor = a.getColor(attr,0xFF1E90FF);
                break;
                case  R.styleable.ChangeColorInconWithText_text:
                    mText = a.getString(attr);
                break;
                case  R.styleable.ChangeColorInconWithText_text_Size:
                    mTextSize = (int) a.getDimension(attr,TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics()));
                break;

            }

        }
        a.recycle();

        mTextPaint = new Paint();
        mTextBound = new Rect();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xFF555555);
        mTextPaint.getTextBounds(mText,0,mText.length(),mTextBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(),
             getMeasuredHeight()-getPaddingTop()-getPaddingBottom()-mTextBound.height());
        int left = getMeasuredWidth()/2-iconWidth/2;
        int top = (getMeasuredHeight()-mTextBound.height())/2-iconWidth/2;

        mIconRect =new Rect(left,top,left+iconWidth,top+iconWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(mIconBitmap,null,mIconRect,null);
        //内存中准备mBitmap，setAlpha，纯色，xfermode，图标
        int alpha = (int) Math.ceil(255*mAlpha);
        setupTargetBitmap(alpha);


        //1.绘制文本  2.绘制原文本

        drawSourceText(canvas,alpha);
        drawTargetText(canvas,alpha);

        canvas.drawBitmap(mBitmap,0,0,null);
    }

    /**
     * 绘制原文本
     * @param canvas
     * @param alpha
     */
    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(0Xff555555);
        mTextPaint.setAlpha(255-alpha);
        int x = getMeasuredWidth()/2-mTextBound.width()/2;
        int y = mIconRect.bottom+mTextBound.height();
        canvas.drawText(mText,x,y,mTextPaint);
    }

    /**
     * 绘制变色文本
     * @param canvas
     * @param alpha
     */

    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        int x = getMeasuredWidth()/2-mTextBound.width()/2;
        int y = mIconRect.bottom+mTextBound.height();
        canvas.drawText(mText,x,y,mTextPaint);
    }

    private void setupTargetBitmap(int alpha){
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mIconBitmap,null,mIconRect,mPaint);
    }

    public void setIconAlpha(float alpha){
        this.mAlpha = alpha;
        invalidataView();
    }

    /**
     * 重绘
     */
    private void invalidataView() {
        if(Looper.getMainLooper() == Looper.myLooper()){
            invalidate();
        }else{
            postInvalidate();
        }
    }
}
