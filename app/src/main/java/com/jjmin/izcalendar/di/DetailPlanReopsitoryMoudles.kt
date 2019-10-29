package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.data.remote.DetailPlanRepository
import com.jjmin.izcalendar.data.remote.DetailPlanRepositoryImpl
import org.koin.dsl.module

object DetailPlanReopsitoryMoudles
{


    var DetailPlanModules = module{
        factory {
            DetailPlanRepositoryImpl(get()) as DetailPlanRepository
        }
    }
}