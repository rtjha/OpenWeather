package com.openweather

import android.text.TextUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * This is presenter class which is responsible our business logic.
 * This class will have reference of view class which is responsible for showing UI to user.
 */
class CityWeatherPresenter: CityWeatherContractor.ICityWeatherPresenter{

    //CityWeather view instance
    var mCityWeatherView: CityWeatherContractor.ICityWeatherView? = null;


    /**
     * Get access to CityWeather View class
     */
    open fun setCityWeatherView(cityWeatherView: CityWeatherContractor.ICityWeatherView){
        this.mCityWeatherView = cityWeatherView
    }

    /**
     * Call API for getting city weather
     */
    override fun getCityWeather(cityName: String, appID: String) {
        if(TextUtils.isEmpty(cityName)){
            mCityWeatherView?.onErrorEmptyCityName()
        }else if(!checkForNetwork()){
            mCityWeatherView?.onErrorNoInternet(mCityWeatherView!!.getBaseContext()?.getString(R.string.txtNoInternet))
        } else{

            mCityWeatherView?.showProgress()


            var apiService = CityWeatherApp.getRetrofit().create(CityWeatherAPIService::class.java);
            var getWeatherCall = apiService.getCityWeather(cityName,appID);


            getWeatherCall.enqueue(object: Callback<CityData> {
                override fun onFailure(call: Call<CityData>?, t: Throwable?) {
                    mCityWeatherView?.hideProgress()
                    mCityWeatherView?.onErrorGetWeather(t?.message!!)
                }

                override fun onResponse(call: Call<CityData>?, response: Response<CityData>?) {
                    var city = response?.body();
                    mCityWeatherView?.hideProgress()
                    mCityWeatherView?.onSuccessGetWeather(city!!)
                }

            })

        }
    }

    /**
     * Check for internet connection
     */
    override fun checkForNetwork(): Boolean {
        return Utils.isNetworkConnected(mCityWeatherView?.getBaseContext()!!)
    }

}