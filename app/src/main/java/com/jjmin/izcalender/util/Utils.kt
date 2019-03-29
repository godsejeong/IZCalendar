package com.jjmin.izcalender.util

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



object Utils{
    var url = "http://aws.soylatte.kr:5000"
    var retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var postservice = retrofit.create(RetrofitServices::class.java!!)
}