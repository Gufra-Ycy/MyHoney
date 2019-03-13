package com.gufra.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gufra.gufra.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends Activity {

    private ListView mList;
    private List<Map<String,String>> list;
    private String[] arrs;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        list = new ArrayList<Map<String ,String>>();

        arrs = new String[50];
        for (int i= 0;i< arrs.length;i++ ){
            arrs[i] = i+"";
        }
        adapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,arrs);
        mList.setAdapter(adapter);
    }

    private void initView(){
        mList = (ListView)findViewById(R.id.list0);
    }
}
