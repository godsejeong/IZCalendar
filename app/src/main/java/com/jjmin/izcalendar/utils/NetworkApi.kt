package com.jjmin.izcalendar.utils

import com.jjmin.izcalendar.data.model.AllPlan
import com.jjmin.izcalendar.data.model.DetailLinkData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi{

    @POST("/allPlanList")
    fun allPlanList() : Single<AllPlan>

    @GET("/allPlanList/{plan_name}")
    fun detailLink(@Path("plan_name") plan_name : String) : Single<DetailLinkData>
}