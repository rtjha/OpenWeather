package com.openweather;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface CityWeatherAPIService {

    @GET("forecast/")
    Call<CityData> getCityWeather(@Query("q") String query, @Query("appid") String appid);
}
