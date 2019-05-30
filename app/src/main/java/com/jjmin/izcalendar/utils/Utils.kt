package com.jjmin.izcalendar.utils

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

object Utils{
    var localhost = "10.0.2.2:5000"
    var url = "http://root.hyunwoo.kim:15000"
    var retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var postservice = retrofit.create(NetworkApi::class.java!!)


}