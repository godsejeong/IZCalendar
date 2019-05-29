package com.jjmin.izcalendar.ui.main

import com.jjmin.izcalendar.data.AllPlan
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

interface PlanRepository{
    fun allPlanList() : Single<AllPlan>
}
