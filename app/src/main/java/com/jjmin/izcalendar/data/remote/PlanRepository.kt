package com.jjmin.izcalendar.data.remote

import com.jjmin.izcalendar.data.model.AllPlan
import io.reactivex.Single

interface PlanRepository{
    fun allPlanList() : Single<AllPlan>
}
