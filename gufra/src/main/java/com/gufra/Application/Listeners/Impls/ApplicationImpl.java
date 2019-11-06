package com.gufra.Application.Listeners.Impls;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

/**
 * @author gufra
 * 实现代理接口
 * */
public class ApplicationImpl extends Application implements ApplicationListener {
    private String TAG = "gufra.ApplicationImpl";
    XApplicationListener xApplicationListener = null;
    YApplicationListener yApplicationListener= null;

    @Override
    public void appOnCreate() {
        Log.d(TAG, "onCreate");
        if (xApplicationListener != null){
            xApplicationListener.appOnCreate();
        }
        if (yApplicationListener != null){
            yApplicationListener.appOnCreate();
        }
    }

    @Override
    public void appAttachBaseContext(Context context) {
        Log.d(TAG, "attachBaseContext");
        xApplicationListener = getXApplication();
        yApplicationListener = getYApplication();
        if (xApplicationListener != null){
            xApplicationListener.appAttachBaseContext(context);
        }
        if (yApplicationListener != null){
            yApplicationListener.appAttachBaseContext(context);
        }
    }

    @Override
    public void appOnConfigurationChanged(Configuration configuration) {
        Log.d(TAG, "onConfigurationChanged");
        if (xApplicationListener != null){
            xApplicationListener.appOnConfigurationChanged(configuration);
        }
        if (yApplicationListener != null){
            yApplicationListener.appOnConfigurationChanged(configuration);
        }
    }

    private XApplicationListener getXApplication() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.gufra.Application.Listeners.Impls.XApplicationImpl");
            return (XApplicationListener) clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    private YApplicationListener getYApplication(){
        Class clazz = null;
        try {
            clazz = Class.forName("com.gufra.Application.Listeners.Impls.YApplicationImpl");
            return (YApplicationListener) clazz.newInstance();
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
