package com.openweather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * This is starting entry point of OpenWeather app
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.openWeather)
        openCityWeatherFragment()
    }


    /**
     * Open City weather fragment
     */
    private fun openCityWeatherFragment() {
        replaceFragment(R.id.frame_container,CityWeatherFragment.newInstance(),CityWeatherFragment.javaClass.simpleName,false)
    }


}
