package com.gufra.Utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

/**
 * @author yinchaoyin
 * 通知管理
 */
public class NotificationHelper {
    Activity activity;
    NotificationManager notificationManager;
    Notification notification;
    public NotificationHelper (Activity activity){
        this.activity = activity;
    }
    /**
     * 创建通知渠道
     * @param channelId 渠道id
     * @param channelName 渠道name
     * @param level 通知渠道的重要级别
     * tatgetVerison 23及以上
     */
    public void createNotificationChannel(String channelId, String channelName, int level){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,channelName,level);
            /*显示角标*/
            channel.setShowBadge(true);
            notificationManager = (NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

    }

    /**
     * 显示通知
     *
     */
    public void showNotification(String channelId, String text, String title){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(activity,channelId)
                    .setContentText(text)
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setNumber(2)//角标数量
                    .build();
            notificationManager.notify(1,notification);
        }
    }
}
