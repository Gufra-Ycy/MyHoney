package com.gufra.Volley;


import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.android.volley.Request.*;

public class MyVolley {
    private static String TAG = "NetWorkImpl";
    private static RequestQueue mQueue;
    private Context mContext;
    private static String result;
    public MyVolley(Context context){
        this.mContext = context;
        mQueue = Volley.newRequestQueue(mContext);
    }
    public static String volleyGet(String url){
        final StringRequest request = new StringRequest(Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG,"success:"+s);
                result = s;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,"failed："+volleyError.toString());
                result = volleyError.toString();
            }
        });
        //设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        request.setTag("testGet");
        //将请求加入队列中
        mQueue.add(request);
        return result;
    }
}
