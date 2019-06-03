package com.jjmin.izcalendar.utils

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetTheme{
    var _themecolor = MutableLiveData<Int>()
    var _themebackgroundcolor = MutableLiveData<Int>()
    var _themename = MutableLiveData<String>()
    val themecolor : LiveData<Int> get() = _themecolor
    val themebackgroundcolor : LiveData<Int> get() = _themebackgroundcolor
    val themename : LiveData<String> get() = _themename

    init {
        _themebackgroundcolor.value = SharedPreprecncesUtils.getThemeBackgroundColor()
        _themecolor.value = SharedPreprecncesUtils.getThemeColor()
        _themename.value = SharedPreprecncesUtils.getThemeName()
    }

    fun update(){
        _themebackgroundcolor.value = SharedPreprecncesUtils.getThemeBackgroundColor()
        _themecolor.value = SharedPreprecncesUtils.getThemeColor()
        _themename.value = SharedPreprecncesUtils.getThemeName()
    }
}