package com.gufra.Glide;

import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class MyGlide {

    private static MyGlide instance;
    static Fragment mFragment;
    public static void loadImg(Context context, String path,ImageView imageView){
        if (path == " "){
            return;
        }
        Glide.with(context)////传入关联的Context，如果是Activity/Fragment，那么它会根据组件当前的状态来控制请求。
             .load(path)//需要加载的图片，大多数情况下就是网络图片的链接
             .into(imageView); //用来展现图片的ImageView.

//        Glide.with(mFragment)////传入关联的Context，如果是Activity/Fragment，那么它会根据组件当前的状态来控制请求。
//                .load(path)//需要加载的图片，大多数情况下就是网络图片的链接
//
//                .into(imageView); //用来展现图片的ImageView.
    }
}
