package com.jjmin.izcalendar.data.remote

import com.jjmin.izcalendar.data.model.DetailLinkData
import io.reactivex.Single

interface DetailPlanRepository{
    fun detailLink(name : String) : Single<DetailLinkData>
}