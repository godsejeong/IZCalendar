package com.jjmin.izcalender.utils

import com.jjmin.izcalender.data.AllPlan
import retrofit2.Call
import retrofit2.http.POST

interface RetrofitServices{

    @POST("/allPlanList")
    fun allPlanList() : Call<AllPlan>

}