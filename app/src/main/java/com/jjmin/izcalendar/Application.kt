package com.jjmin.izcalendar

import android.app.Application
import android.content.Context
import com.jjmin.izcalendar.di.DetailModules
import com.jjmin.izcalendar.di.MainModules
import com.jjmin.izcalendar.utils.SharedPreprecnceUtils
import org.koin.core.context.startKoin

class Application : Application() {
    var context : Context? = null
    override fun onCreate() {
        super.onCreate()

        startKoin{
            this@Application
            modules(
                DetailModules.detialModule,
                MainModules.mainModules)
        }

        context = this
        SharedPreprecnceUtils.setSpinnerShared(this)
    }
}