package com.gufra.Utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yinchaoyin
 * 读取Assets目录下资源
 */
public class AssetsUtil {
    private static String TAG = "gufra.AssetsUtil";
    private static InputStream inputStream = null;
    public static String getAssets(Context context, String file){
        try {
            //获取输入流
            inputStream = context.getAssets().open(file);
            //获取字节数
            int len = inputStream.available();
            //创建byte数组
            byte[] buffer = new byte[len];
            //将文件中的数据写入字节数组
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Error:"+e.getMessage());
            return "";
        }
    }
}
