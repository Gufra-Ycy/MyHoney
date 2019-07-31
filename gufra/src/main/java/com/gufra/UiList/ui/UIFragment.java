package com.gufra.UiList.ui;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gufra.gufra.R;
@SuppressLint("ValidFragment")
public class UIFragment extends Fragment {

    private View mView;

    public UIFragment(View view) {
        this.mView = view;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        RelativeLayout root = view.findViewById(R.id.fragment_root);
        if (mView.getParent() != null) {
            ((ViewGroup) mView.getParent()).removeView(mView);
        }
        root.addView(mView);
        return view;
    }
}