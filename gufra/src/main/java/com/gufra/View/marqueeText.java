package com.gufra.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class marqueeText extends TextView {
    public marqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public marqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public marqueeText(Context context) {
        super(context);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
}
