package functions
import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.R

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SharedPreferencesUtils(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences( context.getString(R.string.shareFile),Context.MODE_PRIVATE)

    var Firstflag by SharedPreferenceDelegates.boolean()
    var MenuListVersion by SharedPreferenceDelegates.int()
    var MenuListData by SharedPreferenceDelegates.string()
    var UUID by  SharedPreferenceDelegates.string()
    
    
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
        fun int(defaultValue: Int = 0) =
            object : ReadWriteProperty<SharedPreferencesUtils, Int>
            {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ): Int {
                    return thisRef.preferences.getInt(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: Int
                ) {
                    thisRef.preferences.edit().putInt(property.name, value).apply()
                }
            }
        fun string(defaultValue:String = "") =
            object : ReadWriteProperty<SharedPreferencesUtils, String>
            {
                override fun getValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>
                ):String {
                    return thisRef.preferences.getString(property.name,defaultValue).toString()
                }

                override fun setValue(
                    thisRef: SharedPreferencesUtils,
                    property: KProperty<*>,
                    value: String
                ) {
                    thisRef.preferences.edit().putString(property.name, value).apply()
                }
            }
    }
}