package com.example.myapplication

import android.app.Application
import functions.SharedPreferencesUtils
import mode.Event
import mode.menuItem
import java.util.*
import kotlin.collections.ArrayList


class MyApplication: Application() {
    var menuListL:MutableList<menuItem>?=null
    public val preferences by lazy { SharedPreferencesUtils(this.baseContext) }
    var eventList:MutableList<Event>?=null
    override fun onCreate()
    {
        super.onCreate();
        menuListL  = ArrayList<menuItem>()
        if(preferences.UUID.isEmpty())
        {
            preferences.UUID = UUID.randomUUID().toString()
        }
    }


}