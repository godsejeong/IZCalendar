package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.data.remote.PlanRepository
import com.jjmin.izcalendar.data.remote.PlanRepositoryImpl
import org.koin.dsl.module

object PlanRepositoryMoudles{

    val planRepositoryMoudles = module{
        factory {
            PlanRepositoryImpl(get()) as PlanRepository
        }
    }
}