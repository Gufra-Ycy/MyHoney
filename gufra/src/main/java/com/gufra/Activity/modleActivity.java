package com.gufra.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gufra.Activity.ui.modle.ModleFragment;
import com.gufra.gufra.R;

public class modleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modle_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ModleFragment.newInstance())
                    .commitNow();
        }
    }
}
