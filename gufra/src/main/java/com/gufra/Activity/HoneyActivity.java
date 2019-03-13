package com.gufra.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.gufra.gufra.R;
import static android.widget.Toast.LENGTH_SHORT;

public class HoneyActivity extends Activity {

    private Button mTurnToolBtn;
    private Button mTurnDrawerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey);
        initView();

        mTurnToolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"click turn to toolbar", LENGTH_SHORT).show();
            }
        });
        mTurnDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HoneyActivity.this,DrawerLayoutActivity.class));
            }
        });
    }

    private void initView(){
        mTurnToolBtn = (Button)findViewById(R.id.turn_toolbar);
        mTurnDrawerBtn = (Button)findViewById(R.id.turn_drawer);

    }

}
