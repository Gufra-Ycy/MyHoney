package com.gufra;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

public class LiveWallPaper extends WallpaperService {
    private static String TAG = "LiveWallPaper";
    public final static String VIDEO_ACTION = "com.gufra.livewallpaper";
    public final static String ACTION_KEY = "action";
    public final static int FLAG_SILENCE = 10;
    public final static int FLAG_NORMAL = 11;
    @Override
    public Engine onCreateEngine() {
        Log.d(TAG,"onCreateEngine");
        return new VideoEngin();
    }

    //静音
    public static void voiceSilence(Context context){
        Log.d(TAG,"voiceSilence");
        Intent intent = new Intent(VIDEO_ACTION);
        intent.putExtra(ACTION_KEY,FLAG_SILENCE);
        context.sendBroadcast(intent);
    }

    public static void voiceNormal(Context context){
        Log.d(TAG,"voiceNormal");
        Intent intent = new Intent(VIDEO_ACTION);
        intent.putExtra(ACTION_KEY,FLAG_NORMAL);
        context.sendBroadcast(intent);
    }

    public static void setToWallpaper(Context context){
        Log.d(TAG,"setToWallpaper");
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,new ComponentName(context,LiveWallPaper.class));
        context.startActivity(intent);
    }

    class VideoEngin extends Engine{
        private MediaPlayer player;
        private BroadcastReceiver receiver;
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            Log.d(TAG,"VideoEngin onCreate");
            IntentFilter filter = new IntentFilter(VIDEO_ACTION);
            registerReceiver(receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(TAG,"onRecive");
                    int action = intent.getIntExtra(ACTION_KEY,-1);
                    switch (action){
                        case FLAG_NORMAL:
                            player.setVolume(1.0f,1.0f);
                            break;
                        case FLAG_SILENCE:
                            player.setVolume(0,0);
                            break;
                            default:break;
                    }
                }
            },filter);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG,"onDestroy");
            unregisterReceiver(receiver);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);
            Log.d(TAG,"onVisibilityChanged:"+visible);
            if (visible){
                player.start();
            }else {
                player.pause();
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            Log.d(TAG,"onSurfaceChanged");
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            Log.d(TAG,"onSurfaceCreated");
            player = new MediaPlayer();
            player.setSurface(holder.getSurface());
            AssetManager assetManager = getApplicationContext().getAssets();
            try {
                AssetFileDescriptor descriptor = assetManager.openFd("test1.mp4");
                player.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
                player.setLooping(true);
                player.setVolume(0,0);
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            Log.d(TAG,"onSurfaceDestroyed");
            player.release();
            player = null;
        }
    }
}
