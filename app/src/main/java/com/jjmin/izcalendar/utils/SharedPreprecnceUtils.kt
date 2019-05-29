package com.jjmin.izcalendar.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.jjmin.izcalendar.R
import java.security.PrivateKey

object SharedPreprecnceUtils {
    lateinit var sharedPreferences : SharedPreferences

    fun setSpinnerShared(context: Context){
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
        return sharedPreferences.getInt("color$position",0)
    }
}