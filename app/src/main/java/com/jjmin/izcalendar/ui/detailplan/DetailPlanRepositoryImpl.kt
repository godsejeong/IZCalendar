package com.jjmin.izcalendar.ui.detailplan

import com.jjmin.izcalendar.data.DetailLinkData
import com.jjmin.izcalendar.data.DetailPlanItem
import com.jjmin.izcalendar.utils.NetworkApi
import io.reactivex.Single

class DetailPlanRepositoryImpl(private val api : NetworkApi) : DetailPlanRepository{
    override fun DetailLink(name: String): Single<DetailLinkData> {
        return api.DetailLink(name).map { it }
    }
}