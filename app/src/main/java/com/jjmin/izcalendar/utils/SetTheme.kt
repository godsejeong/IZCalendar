package com.jjmin.izcalendar.utils

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.R

class SetTheme{
    var basecolor = R.color.colorWhite
    var _themecolor = MutableLiveData<Int>()
    var _themebackgroundcolor = MutableLiveData<Int>()
    var _themename = MutableLiveData<String>()
    var _themedarkmode = MutableLiveData<Boolean>()
    val themecolor : LiveData<Int> get() = _themecolor
    val themebackgroundcolor : LiveData<Int> get() = _themebackgroundcolor
    val themename : LiveData<String> get() = _themename
    val themedarkmode : LiveData<Boolean> get() = _themedarkmode

    init {
        _themebackgroundcolor.value = SharedPreprecncesUtils.getThemeBackgroundColor()
        _themecolor.value = SharedPreprecncesUtils.getThemeColor()
        _themename.value = SharedPreprecncesUtils.getThemeName()
        _themedarkmode.value = SharedPreprecncesUtils.getDarkTheme()
    }

    fun update(){
        _themebackgroundcolor.value = SharedPreprecncesUtils.getThemeBackgroundColor()
        _themecolor.value = SharedPreprecncesUtils.getThemeColor()
        _themename.value = SharedPreprecncesUtils.getThemeName()
        _themedarkmode.value = SharedPreprecncesUtils.getDarkTheme()
    }
}