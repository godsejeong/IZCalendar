package com.jjmin.izcalendar.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.jjmin.izcalendar.R
import java.security.PrivateKey

@SuppressLint("StaticFieldLeak")
object SharedPreprecncesUtils {
    lateinit var sharedPreferences : SharedPreferences
    lateinit var themePreferences : SharedPreferences
    lateinit var context : Context

    fun init(context: Context){
        this.context = context
        setThemeShared()
        setSpinnerShared()
    }

    fun setThemeShared(){
        themePreferences = context.getSharedPreferences("theme",MODE_PRIVATE)
    }

    fun setTheme(name : String,color : Int,backgroundcolor : Int){
        var editor = themePreferences.edit()
        editor.putString("setThemeName",name)
        editor.putInt("setThemeColor",color)
        editor.putInt("setThemeBackgroundColor",backgroundcolor)
        editor.commit()
    }

    fun getThemeName() : String{
        return themePreferences.getString("setThemeName","")
    }

    fun getThemeColor() : Int{
        return themePreferences.getInt("setThemeColor",0)
    }

    fun getThemeBackgroundColor() : Int{
        return themePreferences.getInt("setThemeBackgroundColor",0)
    }

    fun setSpinnerShared(){
        sharedPreferences = context.getSharedPreferences("tagspinner",MODE_PRIVATE)
    }

    fun setTag(position : String, color: Int) {
        var editor = sharedPreferences.edit()
        editor.putInt("color$position", color)
        editor.commit()
    }

    fun setSpinnerPostion(Layoutposition: String,pos : Int){
        var editor = sharedPreferences.edit()
        editor.putInt("position$Layoutposition",pos)
        editor.commit()
    }

    fun getSpinnerPosition(position: String) : Int {
        return sharedPreferences.getInt("position$position",0)
    }

    fun getColorTag(position: String): Int {
        Log.e("position1111", sharedPreferences.getInt("color$position",0).toString())
        return sharedPreferences.getInt("color$position",0)
    }
}