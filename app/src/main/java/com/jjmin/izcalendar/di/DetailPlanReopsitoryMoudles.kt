package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.ui.detailplan.DetailPlanRepository
import com.jjmin.izcalendar.ui.detailplan.DetailPlanRepositoryImpl
import org.koin.dsl.module

object DetailPlanReopsitoryMoudles
{


    var DetailPlanModules = module{
        factory {
            DetailPlanRepositoryImpl(get()) as DetailPlanRepository
        }
    }
}