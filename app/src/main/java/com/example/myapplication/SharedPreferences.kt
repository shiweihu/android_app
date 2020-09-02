package com.example.myapplication
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SharedPreferencesUtils(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences( context.getString(R.string.shareFile),Context.MODE_PRIVATE)

    var Firstflag by SharedPreferenceDelegates.boolean()

    private object SharedPreferenceDelegates {
        fun boolean(defaultValue: Boolean = false) =
            object : ReadWriteProperty<SharedPreferencesUtils, Boolean> {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ): Boolean {
                    return thisRef.preferences.getBoolean(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: Boolean
                ) {
                    thisRef.preferences.edit().putBoolean(property.name, value).apply()
                }
            }
    }
}