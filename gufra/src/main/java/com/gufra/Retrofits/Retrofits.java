package com.gufra.Retrofits;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofits {
    private static String tag = "Retrofits";
    public Retrofits(){

    }

    public static void get(){
        Retrofit retrofit = new Retrofit
                .Builder()
//                .baseUrl("https://api.github.com/")
                .baseUrl("https://api.seniverse.com/v3/weather/")
                //https://api.seniverse.com/v3/weather/now.json?key=3bfkkpzne3jubseb&location=beijing&language=zh-Hans&unit=c
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<ResponseBody> call = service.listRepos("now.json?key=3bfkkpzne3jubseb&location=beijing&language=zh-Hans&unit=c");
//        Call<ResponseBody> call = service.listRepos("octocat");
        try {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //                        Log.d(tag,"123123"+response.body().string());
                    Log.d(tag,"123123"+response);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                    Log.d(tag,"123123"+throwable.getMessage());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
