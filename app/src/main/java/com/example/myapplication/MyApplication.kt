package com.example.myapplication

import android.app.Application
import functions.SharedPreferencesUtils
import mode.menuItem


class MyApplication: Application() {

    public val preferences by lazy { SharedPreferencesUtils(this.baseContext) }



    override fun onCreate()
    {
        super.onCreate();
        menuListL  = ArrayList<menuItem>()
    }
    var menuListL:MutableList<menuItem>?=null

}