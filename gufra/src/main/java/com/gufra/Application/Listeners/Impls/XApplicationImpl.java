package com.gufra.Application.Listeners.Impls;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

/**
 * @author gufra
 * 实现XApplication代理接口
 * */
public class XApplicationImpl extends Application implements XApplicationListener {
    String TAG ="gufra.XApplicationImpl";
    @Override
    public void appOnCreate() {
        Log.d(TAG,"X->onCreate");
    }

    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG,"X->attachBaseContext");
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG,"X->onConfigurationChanged");
    }
}
