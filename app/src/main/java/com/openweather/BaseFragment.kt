package com.openweather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * This is Base fragment class which will contain all basic fragment related operation
 */
open abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getFragmentLayout(),container,false);
    }

    abstract fun getFragmentLayout(): Int

}