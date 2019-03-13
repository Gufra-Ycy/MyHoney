package com.gufra.Utils;

public class MyActivityManager {
    private static MyActivityManager instance = null;

    public static MyActivityManager getIntance(){
        if (instance == null){
            instance = new MyActivityManager();
        }
        return instance;
    }
}
