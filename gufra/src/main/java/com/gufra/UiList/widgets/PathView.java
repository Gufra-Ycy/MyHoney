package com.gufra.UiList.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.view.View;

import com.gufra.gufra.R;

public class PathView extends View {
    Paint mPaint;
    public PathView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        canvas.drawText("PathView",100,100,mPaint);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.bit);
        //第一个 Shader：头像的 Bitmap
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //ComposeShader-混合着色器
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.wa_close);
        //第二个 Shader从上到下的线性渐变（由透明到黑色）
        Shader shader2 = new BitmapShader(bitmap2,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        // ComposeShader：结合两个 Shader
        Shader composeShader  = new ComposeShader(shader1,shader2, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(shader1);
//        mPaint.setShader(composeShader);
        canvas.drawCircle(200,200,100,mPaint);
    }
}
