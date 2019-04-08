package com.jjmin.izcalender.utils

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

object Utils{
    var url = "http://aws.soylatte.kr:5000"
    var retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var postservice = retrofit.create(RetrofitServices::class.java!!)

    fun today(): String {
        val mSimpleDateFormat = SimpleDateFormat("MM/dd", Locale.KOREA)
        val date = Date()
        return mSimpleDateFormat.format(date)
    }
}