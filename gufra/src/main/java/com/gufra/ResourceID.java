package com.gufra;

import android.app.Activity;

public class ResourceID {

    private static Activity mActivity;
    private static int getRes(String name , String def){
        return mActivity.getResources().getIdentifier(name,def,mActivity.getPackageName());
    }

    public static class id{
        public static int et_user = getRes("et_user","id");
    }
}
