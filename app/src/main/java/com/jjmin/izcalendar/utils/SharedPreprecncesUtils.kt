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
    lateinit var TutorialPreferences : SharedPreferences
    lateinit var context : Context

    fun init(context: Context){
        this.context = context
        setThemeShared()
        setSpinnerShared()
        setTuorialShared()
    }

    fun setTuorialShared(){
        TutorialPreferences = context.getSharedPreferences("Tutorial",MODE_PRIVATE)
    }

    fun setTutorialCheck(bl : Boolean){
        var editor = TutorialPreferences.edit()
        editor.putBoolean("settutorial",bl)
        editor.commit()
    }

    fun getTutorialCheck() : Boolean{
        return TutorialPreferences.getBoolean("settutorial",false)
    }

    fun setThemeShared(){
        themePreferences = context.getSharedPreferences("theme",MODE_PRIVATE)
    }

    fun setTheme(name : String,color : Int,backgroundcolor : Int){
        var editor = themePreferences.edit()
        editor.putString("setThemeName",name)
        editor.putInt("setThemeColor",color)
        if(!getDarkTheme())
            editor.putInt("setThemeBackgroundColor",backgroundcolor)
        editor.commit()
    }

    fun setDarkTheme(check : Boolean){
        var editor = themePreferences.edit()
        editor.putBoolean("setDarkMode",check)
        editor.putInt("setThemeBackgroundColor",R.color.colorDarkMode)
        editor.commit()
    }

    fun getDarkTheme() : Boolean{
        return themePreferences.getBoolean("setDarkMode",false)
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
        var color = sharedPreferences.getInt("color$position",SetTheme().themecolor.value!!)
        return if(color == 0){
            getThemeColor()
        }else{
            color
        }
    }
}