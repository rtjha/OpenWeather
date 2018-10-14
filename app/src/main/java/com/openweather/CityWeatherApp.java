package com.openweather;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityWeatherApp extends Application{

    //Base url to call APIs of OpenWeather
    public static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/";

    //Retrofit Instance
    static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * Get instance of Retrofit
     * @return
     */
    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
