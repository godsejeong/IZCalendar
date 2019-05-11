package com.jjmin.izcalender.utils

import com.jjmin.izcalender.data.AllPlan
import com.jjmin.izcalender.data.DetailLinkData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitServices{

    @POST("/allPlanList")
    fun allPlanList() : Call<AllPlan>



    @GET("/allPlanList/{plan_name}")
    fun DetailLink(@Path("plan_name") plan_name : String) : Call<DetailLinkData>

}