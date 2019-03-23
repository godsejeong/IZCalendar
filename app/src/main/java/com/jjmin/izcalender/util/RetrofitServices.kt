package com.jjmin.izcalender.util

import com.jjmin.izcalender.data.AllPlan
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitServices{

    @POST("/allPlanList")
    fun allPlanList() : Call<AllPlan>

}