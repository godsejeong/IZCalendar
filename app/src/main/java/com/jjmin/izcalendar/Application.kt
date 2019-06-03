package com.jjmin.izcalendar

import android.app.Application
import android.content.Context
import com.jjmin.izcalendar.di.*
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import org.koin.core.context.startKoin

class Application : Application() {
    var context : Context? = null
    override fun onCreate() {
        super.onCreate()

        startKoin{
            this@Application
            modules(
                DetailModules.detialModule,
                MainModules.mainModules,
                PlanRepositoryMoudles.planRepositoryMoudles,
                NetworkModules.networkModules,
                DetailPlanReopsitoryMoudles.DetailPlanModules,
                SettingModules.SettingModules,
                ThemeModules.themeModules)
        }

        context = this
        SharedPreprecncesUtils.init(this)
    }
}