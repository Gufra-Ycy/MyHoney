package com.gufra;

import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.facebook.stetho.Stetho;
import com.gufra.Application.Listeners.Impls.ProxyApplication;

public class MyApplication extends ProxyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
