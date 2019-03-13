package com.gufra.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 *SharedPreference管理类
 *SharedPreferences的本质是基于XML文件存储key-value键值对数据，通常用来存储一些简单的配置信息
 *（1）使用Activity类的getSharedPreferences方法获得SharedPreferences对象，其中存储key-value的文件的名称由getSharedPreferences方法的第一个参数指定，第二个参数指定访问应用程序私有文件的权限。
 *（2）使用SharedPreferences接口的edit获得SharedPreferences.Editor对象。
 *（3）通过SharedPreferences.Editor接口的putXxx方法保存key-value对。其中Xxx表示不同的数据类型。例如：字符串类型的value需要用putString方法。
 *（4）通过SharedPreferences.Editor接口的commit方法保存key-value对。commit方法相当于数据库事务中的提交（commit）操作。
 *
 * */
public class MySharedPreference {
    private static final String UPDATE_TIME = "update_time";
    private Context mContext = null;
    private String mName;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEditor;

    public MySharedPreference(Context context ,String name){
        this.mContext = context;
        this.mName = name;
        sp = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        long locolTime = sp.getLong(UPDATE_TIME, 0);

    }

    public void putString(String key, String value){
        mEditor = sp.edit();
        mEditor.putString(key,value);
    }

    public  void getString(String key ,String value){

    }

    //删除指定数据
    public void removeKey(String key){

    }

    //清空数据
    public void clearSP(){

    }
}
