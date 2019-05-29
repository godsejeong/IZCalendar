package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.utils.NetworkApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModules{
    var url = "http://root.hyunwoo.kim:15000"

    val networkModules = module {
        single {
            Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
                .create(NetworkApi::class.java)
        }
    }
}