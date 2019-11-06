package com.gufra.OkHttp3;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyOkHttp {

    private static String TAG = "gufra.MyOkHttp";
    private static OkHttpClient client = null;
    private static Request request = null;

    public MyOkHttp(){
    }

    public static void GET(String url){

        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        //异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"Error"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG,"response:"+response.body().string());

            }
        });
    }
    /*
    public static String Post(String url){
        try {
            //创建表单
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("","");


            client = new OkHttpClient();
            request = new Request.Builder()
                    .url(new URL(url))
                    .post()
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }*/
}
