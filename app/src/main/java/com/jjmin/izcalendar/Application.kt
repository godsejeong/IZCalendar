package com.jjmin.izcalendar

import android.app.Application
import android.content.Context
import com.jjmin.izcalendar.di.*
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class Application : Application() {
    var context : Context? = null
    override fun onCreate() {
        super.onCreate()

        startKoin{
            this@Application
            modules(
                Modules.detialModule,
                Modules.mainModules,
                PlanRepositoryMoudles.planRepositoryMoudles,
                NetworkModules.networkModules,
                DetailPlanReopsitoryMoudles.DetailPlanModules,
                Modules.SettingModules,
                Modules.themeModules,
                Modules.splashModules,
                Modules.tutorialModules
            )
        }

        context = this
        SharedPreprecncesUtils.init(this)
    }
}