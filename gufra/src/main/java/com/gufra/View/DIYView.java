package com.gufra.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DIYView extends View {

    Paint mPaint;


    public DIYView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.parse("#88880000");// 半透明红色
        canvas.drawColor(Color.rgb(88,88,00));// 半透明红色
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);  //设置绘制模式
        mPaint.setColor(Color.RED);         //设置颜色
        mPaint.setStrokeWidth(5);           //设置线条宽度
        mPaint.setTextSize(10);             //设置文字大小
        mPaint.setAntiAlias(true);          //设置抗锯齿开关
    }
}
