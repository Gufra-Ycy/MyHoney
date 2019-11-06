package com.gufra.Application.Listeners.Impls;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
/**
 * @author gufra
 * 实现YApplication代理接口
 * */
public class YApplicationImpl extends Application implements YApplicationListener {
    String TAG = "gufra.YApplicationImpl";
    @Override
    public void appOnCreate() {
        Log.d(TAG,"Y->onCreate");
    }

    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG,"Y->attachBaseContext");
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG,"Y->onConfigurationChanged");
    }
}
