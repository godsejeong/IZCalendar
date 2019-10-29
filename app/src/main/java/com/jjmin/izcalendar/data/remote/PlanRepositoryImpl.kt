package com.jjmin.izcalendar.data.remote

import com.jjmin.izcalendar.data.model.AllPlan
import com.jjmin.izcalendar.utils.NetworkApi
import io.reactivex.Single

class PlanRepositoryImpl(private var api : NetworkApi) : PlanRepository {
    override fun allPlanList(): Single<AllPlan> {
        return api.allPlanList().map { it }
    }
}