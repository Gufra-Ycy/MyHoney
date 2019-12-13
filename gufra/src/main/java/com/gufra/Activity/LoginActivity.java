package com.gufra.Activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;
import com.gufra.AsyncHttp.NetWorkImpl;
import com.gufra.Glide.MyGlide;
import com.gufra.OkHttp3.MyOkHttp;
import com.gufra.Retrofits.Retrofits;
import com.gufra.Utils.AssetsUtil;
import com.gufra.Utils.NotificationHelper;
import com.gufra.View.marqueeText;
import com.gufra.Volley.MyVolley;
import com.gufra.gufra.R;
import com.ljs.lovelyprogressbar.LovelyProgressBar;

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
    private ImageView mImageView;
    private marqueeText marquee;//跑马灯TextView
    private LovelyProgressBar mbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        Log.d("gufra.LoginActivity","assets.appinfo.json"+AssetsUtil.getAssets(this,"appinfo.json"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationHelper notificationHelper = new NotificationHelper(this);
            notificationHelper.createNotificationChannel("notification test","通知测试", NotificationManager.IMPORTANCE_HIGH);
        }

//        MyBuilder myBuilder = new MyBuilder().setA("a").setB("b");//Builder模式
        String imgPath = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
//        String imgPath = " ";
        MyGlide.loadImg(this,imgPath,mImageView);
//        setWeather();
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyVolley volley =new MyVolley(LoginActivity.this);
//                volley.volleyGet("http://apis.juhe.cn/simpleWeather/query");
                String s = MyVolley.volleyGet("https://api.seniverse.com/v3/weather/now.json?key=3bfkkpzne3jubseb&location=beijing&language=zh-Hans&unit=c");
                Log.e("NetWorkImpl","1223456:"+s);
                startActivity(new Intent(LoginActivity.this,HoneyActivity.class));
                if (isAccess()){

                }
                MyOkHttp.GET("https://api.seniverse.com/v3/weather/now.json?key=3bfkkpzne3jubseb&location=beijing&language=zh-Hans&unit=c");
            }
        });
        ByteArrayInputStream bais;

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofits.get();
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                //加载成功调用即可执行成功动画
            }
        });
    }

    private void initView(){
        mbar = (LovelyProgressBar)findViewById(R.id.loader);
        //设置动画回调
        mbar.setOnLoadListener(new LovelyProgressBar.OnLoadListener() {
            @Override
            public void onAnimSuccess() {
                Toast.makeText(LoginActivity.this,"LovelyProfressBar+onAnimSuccess",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimError() {
                Toast.makeText(LoginActivity.this,"LovelyProfressBar+onAnimError",Toast.LENGTH_LONG).show();
            }
        });
        marquee = (marqueeText)findViewById(R.id.tv2);
        marquee.setSelected(true);
        mNameEdit = (EditText)findViewById(R.id.et_user);
        mbar.startload();//设置progress前先startload（）
        mbar.setProgress(0);//设置进度,100:自动启动succesload动画
//加载失败调用即可失败动画
//        mbar.errorLoad();
        mbar.succesLoad();
        mPassEdit = (EditText)findViewById(R.id.et_pass);
        mPassTextInput = (TextInputLayout)findViewById(R.id.textinput_pass);
        mLoginBtn = (Button)findViewById(R.id.btn_login);
        mRegister = (TextView)findViewById(R.id.register);
        mNameTextInput = (TextInputLayout)findViewById(R.id.textinput_user);
        mImageView = (ImageView)findViewById(R.id.img_exp);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyAlertDialog)
                    .setTitle("退出")
                    .setMessage("确认退出？")
                    .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.create().show();
        }
        return super.onKeyDown(keyCode, event);
    }

}
