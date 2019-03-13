package com.gufra.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
/*
* 想要好评，就尽可能让好评变得容易。
* */
public class Evaluation {
    private static Context mContext;
    public Evaluation(Context context){
        this.mContext = context;
    }

    //获取评价
    public void goodEva(){
        String appPackage = mContext.getPackageName();
        //扫描已安装的市场包名
//        ArrayList<String> marketPkgs = MarketUtils
        String appPkg = mContext.getPackageName();
        Uri uri = Uri.parse("market://details?id=" + appPkg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    //分享
    public static void shareFile(Context context) {
//        File apkFile = AppUtils.getApkFile(context);
        File apkFile = null;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(apkFile));
        context.startActivity(intent);
    }


}
