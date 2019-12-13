package com.gufra.honey;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BubbleActivity extends AppCompatActivity {

    Button btn_Bubble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble);
        btn_Bubble = (Button)findViewById(R.id.btn_bubble);
        btn_Bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BubbleActivity.this, "点击了气泡", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
