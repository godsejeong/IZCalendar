package com.jjmin.izcalendar.ui.detailplan

import com.jjmin.izcalendar.data.DetailLinkData
import com.jjmin.izcalendar.data.DetailPlanItem
import io.reactivex.Single

interface DetailPlanRepository{
    fun DetailLink(name : String) : Single<DetailLinkData>
}