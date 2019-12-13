package com.gufra.Activity.ui.modle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gufra.gufra.R;

public class ModleFragment extends Fragment {
    public static String TAG = "gufra.model";
    private ModleViewModel mViewModel;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    public static ModleFragment newInstance() {
        return new ModleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        return inflater.inflate(R.layout.modle_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
        mViewModel = ViewModelProviders.of(this).get(ModleViewModel.class);
        // TODO: Use the ViewModel
        initView();
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initView(){
        mTabLayout = (TabLayout)getActivity().findViewById(R.id.model_tab);
        mViewPager = (ViewPager)getActivity().findViewById(R.id.model_vp);
    }
}
