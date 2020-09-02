package com.example.myapplication

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mode.TabOne
import mode.TabTwo
import mode.menuItem


class MyApplication: Application() {
    override fun onCreate()
    {
        super.onCreate();
        menuListL  = ArrayList<menuItem>()
    }
    var menuListL:MutableList<menuItem>?=null

}