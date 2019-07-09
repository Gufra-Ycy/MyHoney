package com.gufra.Glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gufra.View.CustomView;

public class MyViewTarget extends ViewTarget<CustomView,Glide>{

    public MyViewTarget(@NonNull CustomView view) {
        super(view);
    }

    @Override
    public void onResourceReady(@NonNull Glide resource, @Nullable Transition<? super Glide> transition) {

    }
}
