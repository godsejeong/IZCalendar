package com.jjmin.izcalender.utils

import android.app.Application
import android.content.Context

class Application : Application() {
    var context : Context? = null
    override fun onCreate() {
        super.onCreate()
        context = this
        SharedPreprecnceUtils.setSpinnerShared(this)
    }
}