package com.gufra.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.gufra.LiveWallPaper;
import com.gufra.gufra.R;

public class WallPaperActivity extends AppCompatActivity {

    CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_paper);
        mCheckBox = (CheckBox)findViewById(R.id.check_voice);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //静音
                    LiveWallPaper.voiceSilence(getApplicationContext());
                }else{
                    //播放声音
                    LiveWallPaper.voiceNormal(getApplicationContext());
                }
            }
        });
    }

    //设置壁纸
    public void onSet(View v){
        LiveWallPaper.setToWallpaper(this);
    }
}
