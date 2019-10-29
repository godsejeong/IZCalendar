package com.jjmin.izcalendar.data.remote

import com.jjmin.izcalendar.data.model.DetailLinkData
import com.jjmin.izcalendar.utils.NetworkApi
import io.reactivex.Single

class DetailPlanRepositoryImpl(private val api : NetworkApi) : DetailPlanRepository {
    override fun detailLink(name: String): Single<DetailLinkData> {
        return api.detailLink(name).map { it }
    }
}