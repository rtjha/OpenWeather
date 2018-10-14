package com.openweather

import android.content.Context


/**
 * Contractor class for CityWeather view and presenter
 */
class CityWeatherContractor {

    /**
     * Contractor interface for CityWeather view class
     */
    interface ICityWeatherView {
        fun showProgress()          //To show progress while any time consuming task is going on
        fun hideProgress()          //Hide progress after time consuming task is completed
        fun showMessage(message: String)           //To show any message to user
        fun onErrorEmptyCityName()    //To show error message when city name is not entered
        fun onErrorGetWeather(message: String)    //To show error message when city name is not entered
        fun onErrorNoInternet(message: String?)    //To show error when there is no internet
        fun onSuccessGetWeather(cityData: CityData)    //To show error message when city name is not entered
        fun getBaseContext(): Context?    //Get context of this view
    }


    /**
     * Contractor interface for CityWeather presenter class
     */
    interface ICityWeatherPresenter {
        fun checkForNetwork(): Boolean;
        fun getCityWeather(cityName: String, appID: String)     //To get city weather by calling API
    }
}