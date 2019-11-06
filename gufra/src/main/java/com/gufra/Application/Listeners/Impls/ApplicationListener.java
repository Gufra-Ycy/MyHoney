package com.gufra.Application.Listeners.Impls;

import android.content.Context;
import android.content.res.Configuration;
/**@author gufra
 * 代理接口
 * */
public interface ApplicationListener {
    void appOnCreate();
    void appAttachBaseContext(Context context);
    void appOnConfigurationChanged(Configuration configuration);
}
