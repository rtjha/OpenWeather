package com.openweather

import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


/**
 * This is Base activity class which will contain all basic Activity related operation
 */
open class BaseActivity: AppCompatActivity() {

    /**
     * Replace Fragment with new fragment and tag
     *
     */
    public fun replaceFragment(container: Int,fragment: Fragment, tag:String, addToBackStack:Boolean){
        title = tag
        var fragmentTransction = supportFragmentManager.beginTransaction()
        fragmentTransction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,android.R.anim.slide_in_left,
                android.R.anim.slide_out_right)
        if(addToBackStack){
            fragmentTransction.addToBackStack(tag);
        }
        fragmentTransction.replace(container,fragment,tag)
        fragmentTransction.commit()

    }

}