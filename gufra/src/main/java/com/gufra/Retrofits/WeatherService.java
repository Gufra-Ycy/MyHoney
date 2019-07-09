package com.gufra.Retrofits;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {
//    @GET("users/{user}/repos")
    @GET("{user}")
    Call<ResponseBody> listRepos(@Path("user") String user);
}
