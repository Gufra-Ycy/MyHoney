package com.gufra.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.gufra.gufra.R;

public class MyService extends Service {
    private static  String tag = "MyService";

    private String CHANNEL_ID = "001";

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //执行耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(tag,"onStartCommand");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag,"onCreate");
        /**8.0以上*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            setForegroundService();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setForegroundService(){
        //设置渠道名称
        String channelName = "MyService";
        //设置重要性
        int importance = NotificationManager.IMPORTANCE_NONE;
        //创建通知渠道
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,channelName,importance);
        channel.setDescription("This is Description");
        //发送通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.yugan)
            .setContentTitle("Title")
            .setContentText("Text")
            .setAutoCancel(true)//触摸关闭
            .setOngoing(true);//处于运行状态

        //向系统注册通知渠道，注册后不能改变重要性以及其他通知行为
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        //将服务置于启动状态
        startForeground(Integer.parseInt(CHANNEL_ID),builder.build());

    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
