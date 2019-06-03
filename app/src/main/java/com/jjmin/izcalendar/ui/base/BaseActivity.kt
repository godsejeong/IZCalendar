package com.jjmin.izcalendar.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SetThemeToolbar

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewDataBinding: T

    abstract val layoutResourceId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(SetThemeToolbar.settheme(SetTheme().themename.value!!))

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        viewDataBinding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.notifyChange()
    }
}
