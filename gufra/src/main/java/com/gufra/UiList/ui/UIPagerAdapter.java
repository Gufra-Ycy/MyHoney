package com.gufra.UiList.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

public class UIPagerAdapter extends FragmentPagerAdapter {
    private static String TAG = "UIPagerAdapter";
    List<String>titles;
    List<com.example.yuanping.uilist.ui.UIFragment>fragments;
    public UIPagerAdapter(List<String>titles, List<com.example.yuanping.uilist.ui.UIFragment>fragments, FragmentManager fm) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        Log.d(TAG,"Item"+i);
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        Log.d(TAG,"count"+titles.size());
        return titles.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
