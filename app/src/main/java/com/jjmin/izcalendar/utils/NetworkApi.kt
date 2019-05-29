package com.jjmin.izcalendar.utils

import com.jjmin.izcalendar.data.AllPlan
import com.jjmin.izcalendar.data.DetailLinkData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi{

    @POST("/allPlanList")
    fun allPlanList() : Single<AllPlan>

    @GET("/allPlanList/{plan_name}")
    fun DetailLink(@Path("plan_name") plan_name : String) : Call<DetailLinkData>
}