package com.gufra.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.gufra.UiList.ui.PracticeOne;
import com.gufra.gufra.R;

import static android.widget.Toast.LENGTH_SHORT;

public class HoneyActivity extends Activity {
    private Button mTurnToolBtn;
    private Button mTurnDrawerBtn;
    private Button mTurnWebBtn;
    private Button mTurnSearchBtn;
    private Button mTurnMediaBtn;
    private Button mTurnPracticeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey);
        initView();

        mTurnToolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"click turn to toolbar", LENGTH_SHORT).show();
                final AlertDialog.Builder dialog = new AlertDialog.Builder(HoneyActivity.this);
                dialog.setTitle("对话框");
                dialog.setIcon(R.drawable.ying);
                dialog.setMessage("跳转到应用设置");
                dialog.setPositiveButton("系统设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //根据包名打开对应的应用权限设置页面
                        dialogInterface.dismiss();
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri intentData = Uri.fromParts("package",getPackageName(),null);
                        intent.setData(intentData);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.create().show();

            }
        });
        mTurnDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoneyActivity.this,DrawerLayoutActivity.class));
            }
        });
        mTurnWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoneyActivity.this,MyWebActivity.class));
            }
        });
        mTurnSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoneyActivity.this,SearchActivity.class));
            }
        });
        mTurnMediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoneyActivity.this,MediaActivity.class));
            }
        });
        mTurnPracticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoneyActivity.this, PracticeOne.class));
            }
        });
    }

    private void initView(){
        mTurnToolBtn = (Button)findViewById(R.id.turn_toolbar);
        mTurnDrawerBtn = (Button)findViewById(R.id.turn_drawer);
        mTurnWebBtn = (Button)findViewById(R.id.turn_web);
        mTurnSearchBtn = (Button)findViewById(R.id.turn_search);
        mTurnMediaBtn = (Button)findViewById(R.id.turn_media);
        mTurnPracticeBtn = (Button)findViewById(R.id.turn_practice);
    }

}
