package com.gufra.AsyncHttp;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class NetWorkImpl {
    private static String TAG = "NetWorkImpl";
    private static AsyncHttpClient client = null;
    private static JSONObject jsonObject;

    public static JSONObject Get(String path , RequestParams params) {
        client = new AsyncHttpClient();
        client.post(path,params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (response.optString("resultcode").equals("200")){
                    Log.e(TAG,"请求正确&&code="+statusCode+"&&json="+response);
                }else{
                    Log.e(TAG,"请求错误&&code="+statusCode+"&&json="+response);
                }
                jsonObject = response;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG,"failed&&code="+statusCode+"&&json="+errorResponse);
                jsonObject = errorResponse;
            }
        });
        return jsonObject;
    }
}
