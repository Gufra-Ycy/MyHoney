package com.gufra.Application.Listeners.Impls;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * @author gufra
 * 代理类
 * */
public class ProxyApplication extends Application {
    ApplicationListener applicationListener = null;
    @Override
    public void onCreate() {
        super.onCreate();
        if (applicationListener != null) {
            applicationListener.appOnCreate();
        }
    }

    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        applicationListener = getApplicationListener();
        if (applicationListener != null){
            applicationListener.appAttachBaseContext(context);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (applicationListener != null) {
            applicationListener.appOnConfigurationChanged(configuration);
        }
    }

    private ApplicationListener getApplicationListener(){
        Class clazz = null;
        try {
            clazz = Class.forName("com.gufra.Application.Listeners.Impls.ApplicationImpl");
            return (ApplicationListener) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;

    }
}
