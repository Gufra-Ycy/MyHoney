package com.gufra.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gufra.AsyncHttp.NetWorkImpl;
import com.gufra.Volley.MyVolley;
import com.gufra.gufra.R;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;

public class LoginActivity extends Activity {

    private static int MIN_LENGTH = 6;
    private static int MAX_LENGTH = 15;
    private TextInputLayout mPassTextInput;
    private TextInputLayout mNameTextInput;
    private EditText mNameEdit;
    private EditText mPassEdit;
    private Button mLoginBtn;
    private TextView mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
//        setWeather();
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyVolley volley =new MyVolley(LoginActivity.this);
//                volley.volleyGet("http://apis.juhe.cn/simpleWeather/query");
                String s = volley.volleyGet("https://api.seniverse.com/v3/weather/now.json?key=3bfkkpzne3jubseb&location=beijing&language=zh-Hans&unit=c");
                Log.e("NetWorkImpl","1223456:"+s);
                startActivity(new Intent(LoginActivity.this,HoneyActivity.class));
                if (isAccess()){

                }
            }
        });
        ByteArrayInputStream bais;

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void initView(){
        mNameEdit = (EditText)findViewById(R.id.et_user);
        mPassEdit = (EditText)findViewById(R.id.et_pass);
        mPassTextInput = (TextInputLayout)findViewById(R.id.textinput_pass);
        mLoginBtn = (Button)findViewById(R.id.btn_login);
        mRegister = (TextView)findViewById(R.id.register);
        mNameTextInput = (TextInputLayout)findViewById(R.id.textinput_user);
        //启用计数功能
        mNameTextInput.setCounterEnabled(true);
        //设置计数功能的阈值
        mNameTextInput.setCounterMaxLength(MAX_LENGTH);
        //显示/隐藏密码的眼睛
        mPassTextInput.setPasswordVisibilityToggleEnabled(true);

        //输入检查
        mPassEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() < MIN_LENGTH){
                    mPassTextInput.setError("密码最小长度为"+MIN_LENGTH);
                }else if (editable.length() > MAX_LENGTH){
                    mPassTextInput.setError("密码最大长度不能超过"+MAX_LENGTH);
                }else{
                    mPassTextInput.setErrorEnabled(false);
                }
            }
        });
    }
    private boolean isAccess(){
        if (mNameEdit.getText().equals("ycy")&&mPassEdit.getText().equals("ycy")){
            return true;
        }
        return false;
    }

    public void setWeather(){
        JSONObject jsonObject;
        jsonObject = NetWorkImpl.Get("https://api.seniverse.com/v3/weather/now.json?key=3bfkkpzne3jubseb&location=beijing&language=zh-Hans&unit=c",null);
        Log.e("NetWorkImpl","123"+jsonObject);
    }
}
