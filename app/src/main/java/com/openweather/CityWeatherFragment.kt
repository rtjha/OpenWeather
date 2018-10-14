package com.openweather

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.fragment_city_weather.*


/**
 * This is view class which is responsible for showing UI to user, this won't have any business logic.
 * This class will have reference of presenter class which is responsible for our business logic.
 */
class CityWeatherFragment: BaseFragment(),CityWeatherContractor.ICityWeatherView,View.OnClickListener{

    /**
     * Get base context
     */
    override fun getBaseContext(): Context? {
        return activity?.baseContext
    }

    /**
     * On success of getting city weather
     */
    override fun onSuccessGetWeather(cityData: CityData) {

        txtWind.visibility = View.VISIBLE
        txtCloudiness.visibility = View.VISIBLE
        txtGeoCoords.visibility = View.VISIBLE
        txtHumidity.visibility = View.VISIBLE
        txtPressure.visibility = View.VISIBLE
        txtSunRise.visibility = View.VISIBLE
        txtSunSet.visibility = View.VISIBLE


        //Set default data first
        txtWind.text = TextUtils.concat(getString(R.string.txtWind)," ",getString(R.string.noDataAvailable))
        txtCloudiness.text = TextUtils.concat(getString(R.string.txtCloudiness)," ",getString(R.string.noDataAvailable))
        txtHumidity.text = TextUtils.concat(getString(R.string.txtHumidity)," ",getString(R.string.noDataAvailable))
        txtPressure.text = TextUtils.concat(getString(R.string.txtPressure)," ",getString(R.string.noDataAvailable))
        txtSunRise.text = TextUtils.concat(getString(R.string.txtSunRise)," ",getString(R.string.noDataAvailable))
        txtSunSet.text = TextUtils.concat(getString(R.string.txtSunSet)," ",getString(R.string.noDataAvailable))
        txtGeoCoords.text = TextUtils.concat(getString(R.string.txtGeoCoords)," ",getString(R.string.noDataAvailable))


        //Set API data
        if(cityData?.list[0]?.wind?.speed != 0.0) txtWind.text = TextUtils.concat(getString(R.string.txtWind)," ",cityData?.list[0]?.wind?.speed.toString()," ",getString(R.string.mps))
        if(cityData?.list[0]?.clouds?.all != 0) txtCloudiness.text = TextUtils.concat(getString(R.string.txtCloudiness)," ",cityData?.list[0]?.clouds?.all.toString()," ")
        if(cityData?.list[0]?.main?.pressure != 0.0) txtPressure.text = TextUtils.concat(getString(R.string.txtPressure)," ",cityData?.list[0]?.main?.pressure.toString()," ",getString(R.string.hpa))
        if(cityData?.list[0]?.main?.humidity != 0) txtHumidity.text = TextUtils.concat(getString(R.string.txtHumidity)," ",cityData?.list[0]?.main?.humidity.toString(),"%")
        if(cityData?.city?.coord?.lat != 0.0) txtGeoCoords.text = TextUtils.concat(getString(R.string.txtGeoCoords)," ",getString(R.string.strtBracket),cityData?.city?.coord?.lat.toString(),", ",cityData?.city?.coord?.lon.toString(),getString(R.string.endBracket))


    }

    /**
     * To show messgae to user
     */
    override fun showMessage(message: String) {
        val snackbar = Snackbar
                .make(this.view!!, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    /**
     * on no internet
     */
    override fun onErrorNoInternet(message: String?) {
        showMessage(message!!)
    }

    /**
     * on error while getting city weather
     */
    override fun onErrorGetWeather(message: String) {
        showMessage(message)
    }

    /**
     * To show progress
     */
    override fun showProgress() {
        progBar.visibility = View.VISIBLE
    }

    /**
     * To hide progress
     */
    override fun hideProgress() {
        progBar.visibility = View.GONE
    }

    /**
     * On error of empty city name
     */
    override fun onErrorEmptyCityName() {
        edtCityName.error = getString(R.string.errCityName)
    }

    //CityWeather presenter instance
    var mCityWeatherPresenter: CityWeatherContractor.ICityWeatherPresenter? = null;

    /**
     * All static fun related to this class
     */
    companion object {
         fun newInstance():CityWeatherFragment{
            return CityWeatherFragment();
        }
    }

    /**
     * Called when this fragment is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.title = getString(R.string.openWeather)
        setHasOptionsMenu(false)


        mCityWeatherPresenter = CityWeatherPresenter()  //New instance of CityWeatherPresenter
        (mCityWeatherPresenter as CityWeatherPresenter).setCityWeatherView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGetWeather.setOnClickListener(this)  //Register click listener
    }

    /**
     * Handle click of views
     */
    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnGetWeather -> mCityWeatherPresenter?.getCityWeather(edtCityName.text.toString(),getString(R.string.openWeatherApiKey))
        }
    }

    /**
     * Abstract fun of BaseFragment to get layout for this fragment
     */
    override fun getFragmentLayout(): Int {
        return R.layout.fragment_city_weather
    }


}