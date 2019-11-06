package com.gufra.Application.Listeners.Impls;

import android.content.Context;
import android.content.res.Configuration;

/**
 * @author gufra
 * YApplication代理接口
 * */
public interface YApplicationListener {
    void appOnCreate();
    void appAttachBaseContext(Context context);
    void appOnConfigurationChanged(Configuration configuration);
}
