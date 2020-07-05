package ir.jaShakouri.data.local.sharedPref

import android.content.Context
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.domain.AppKeys

class Pref constructor(appClass: AppClass) {

    private val pref =
        appClass.baseContext.getSharedPreferences(AppKeys.PrefName, Context.MODE_PRIVATE)
    private val editor = pref.edit()

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun get(key: String): String {
        return pref!!.getString(key, "")!!
    }

}