package com.gufra.UiList.widgets;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class HistogramView extends View {
    private Paint mPaint;
    private RectF mRectf = new RectF();
    private int progress=0;
    @SuppressLint("ObjectAnimatorBinding")
    private ObjectAnimator animator = ObjectAnimator.ofInt(this,"progress",0,70);
    public HistogramView(Context context) {
        super(context);
    }

    public HistogramView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public HistogramView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(30);
        mPaint.setFakeBoldText(true);//伪粗体：通过程序在运行时把文字给“描粗”，不是通过选用更高 weight 的字体让文字变粗
//        mPaint.setStrokeWidth(30);
        canvas.drawText("HistogramView",100,100,mPaint);
        animator.setDuration(1000);
        mRectf.bottom = 300;
        mRectf.left = 100;
        mRectf.right = 300;
        mRectf.top = 100;
        canvas.drawArc(mRectf,100,360*progress/100,false,mPaint);
        animator.start();
    }

    public void setProgress(int progress){
        this.progress = progress;
        invalidate();
    }
}
