package com.gufra.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author yinchaoyin
 * SharedPreference工具类
 * */
public class SPUtil {

    private static String configName = "Honey";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static Context mContext;
    private boolean isExtern = false;
    public SPUtil(){
    }

    public static void init(Activity activity, String configName){
        SPUtil.configName = configName;
        mContext = activity;
        sp = mContext.getSharedPreferences(configName,Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value){
        if (sp == null){
            return ;
        }
        editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getString(String key){
        if (sp == null){
            return "";
        }
        return sp.getString(key,"");
    }

    public static void clear(){
        if (sp == null ){
            return;
        }
        editor = sp.edit();
        editor.clear();
    }
}
