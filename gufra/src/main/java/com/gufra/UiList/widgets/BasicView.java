package com.gufra.UiList.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class BasicView extends View {

    Paint mPaint;

    public BasicView(Context context) {
        super(context);

    }

    public BasicView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public BasicView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
//        mPaint.setARGB(100,255,0,0);//透明度
        //LinearGradient-线性渐变
        //RadialGradient-辐射渐变
        //SweepGradient -扫描渐变
        Shader shader = new LinearGradient(100,100,500,500,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"),Shader.TileMode.CLAMP);
        mPaint.setShader(shader);


        //        canvas.drawPath();
        canvas.drawLine(0,0,500,500,mPaint);

        canvas.drawCircle(500,500,200,mPaint);
    }
}
