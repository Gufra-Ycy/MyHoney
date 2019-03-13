package com.gufra.honey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.gufra.Activity.LoginActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动闪屏动画
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.main_layout);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f,1.0f);
        alphaAnimation.setDuration(1500);//1000毫秒
        layout.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转页面
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
