package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.ui.main.PlanRepository
import com.jjmin.izcalendar.ui.main.PlanRepositoryImpl
import org.koin.dsl.module

object PlanRepositoryMoudles{

    val planRepositoryMoudles = module{
        factory {
            PlanRepositoryImpl(get()) as PlanRepository
        }
    }
}