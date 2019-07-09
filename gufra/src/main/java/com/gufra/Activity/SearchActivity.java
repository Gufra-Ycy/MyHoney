package com.gufra.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gufra.gufra.R;

import java.io.File;

public class SearchActivity extends AppCompatActivity {

    private EditText keywordText;
    private Button mBSearchtn;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        mBSearchtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = keywordText.getText().toString();
                if (keyword.equals("")) {
                    result.setText("请勿输入空白的关键词!!");
                }else {
                    result.setText(searchFile(keyword));
                }
            }
        });
    }
    //初始化控件
    private void initView() {
        keywordText = (EditText)findViewById(R.id.edt_key);
        result = (TextView)findViewById(R.id.txt_result);
        mBSearchtn = (Button)findViewById(R.id.btn_start);
    }

    private String searchFile(String keyword) {
        String result = "";
        File[] files = new File("/").listFiles();
        for (File file : files) {
            if (file.getName().indexOf(keyword) >= 0) {
                result += file.getPath() + "\n";
            }
        }
        if (result.equals("")){
            result = "找不到文件!!";
        }
        return result;
    }
}
