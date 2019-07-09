package com.gufra.UiList.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PieChartViw extends View {
    Paint mPaint;
    public PieChartViw(Context context) {
        super(context);
    }

    public PieChartViw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartViw(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
//        mPaint.setStrokeWidth(40);
        canvas.drawText("PieChartViw",100,100,mPaint);
    }

}
