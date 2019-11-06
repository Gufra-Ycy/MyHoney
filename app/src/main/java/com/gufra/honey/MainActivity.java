package com.gufra.honey;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.google.android.material.snackbar.Snackbar;
import com.gufra.Activity.FloatActivity;
import com.gufra.Activity.LoginActivity;
import com.gufra.Services.MyService;

public class MainActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private String TAG = "MainActivity";
    private final int PERMISSIONS_REQUEST_STORAGE = 1;
    private View mLayout;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Android 8.0使用startForegroundService在前台启动新服务
        Intent myService = new Intent(this, MyService.class);
        startService(myService);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            startForegroundService(myService);
        }
        else{
            startService(myService);
        }
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        showBubble();
//        bubble(MainActivity.this);
        //启动闪屏动画
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.main_layout);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(1000);//1000毫秒
        layout.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
//                checkPermission(MainActivity.this);
                //跳转页面
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    /**显示气泡*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showBubble(){
        //创建通知渠道
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("通知渠道id","通知渠道名称",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        Icon icon = Icon.createWithResource(this,R.drawable.ying);
        Person chatBot = new Person.Builder()
                .setBot(true)
                .setName("BubbleBot")
                .setIcon(IconCompat.createFromIcon(icon))
                .build();
        PendingIntent contentIntent = PendingIntent.getActivity(this,1,new Intent(this,BubbleActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent bubbleIntent = PendingIntent.getActivity(this,2,new Intent(this,BubbleActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            Notification.BubbleMetadata bubbleMetadata = new Notification.BubbleMetadata.Builder()
                    .setAutoExpandBubble(true)
                    .setIcon(icon)
                    .setDesiredHeight(400)
                    .setIntent(bubbleIntent)
                    .build();

            Notification.Builder builder = new Notification.Builder(this,"通知渠道id")
                    .setContentTitle("通知标题")
                    .setContentText("通知内容")
                    .setContentIntent(contentIntent)
                    .setSmallIcon(icon)
                    .setBubbleMetadata(bubbleMetadata)
//                    .addPerson(String.valueOf(chatBot))
                    .setShowWhen(true);

            notificationManager.notify(0,builder.build());
        }
    }
    /**判断是否允许了气泡*/
    public boolean canBubble(String channelId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(channelId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                return notificationManager.areBubblesAllowed() &&  channel.canBubble();
            }
        }
        return false;
    }
    /**
     * Bubble
     */
    public void bubble(Activity mContext) {
        // Create bubble intent
        Intent target = new Intent(mContext, FloatActivity.class);
        PendingIntent bubbleIntent =
                PendingIntent.getActivity(mContext, 1110, target, 0/* flags */);

// Create bubble metadata
        Notification.BubbleMetadata bubbleData =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            bubbleData = new Notification.BubbleMetadata.Builder()
                    .setDesiredHeight(600)
                    .setIntent(bubbleIntent)
                    .setIcon(Icon.createWithResource(mContext, R.drawable.ying))
                    .setAutoExpandBubble(true)
//                    .setSuppressInitialNotification(true)
                    .build();
        }

//Create notification
        Person chatBot = new Person.Builder()
                .setBot(true)
                .setName("BubbleBot")
                .setImportant(true)
                .build();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            Notification.Builder builder =
                    new Notification.Builder(mContext, "channel001")
//                            .setContentIntent(contentIntent)
                            .setSmallIcon(R.drawable.user)
                            .setBubbleMetadata(bubbleData)
                            .addPerson(String.valueOf(chatBot));
        }
    }

    public void checkPermission(Activity activity) {
        mLayout = findViewById(android.R.id.content);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "permission has not already!!!!");
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Snackbar.make(mLayout, R.string.permission_storage_rationale,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        PERMISSIONS_REQUEST_STORAGE);
                            }
                        })
                        .show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            Log.d(TAG, "permission has already!!!!");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");

        Log.d(TAG, "requestCode" + requestCode);
        Log.d(TAG, "permissions" + permissions);
        Log.d(TAG, "grantResults" + grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Snackbar.make(mLayout, R.string.permission_storage_granted,
                            Snackbar.LENGTH_SHORT).show();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.i(TAG, "Contacts permissions were NOT granted.");
                    Snackbar.make(mLayout, R.string.permission_storage_rationale,
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
            break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
