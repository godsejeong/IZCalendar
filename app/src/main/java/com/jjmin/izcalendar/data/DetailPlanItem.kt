package com.jjmin.izcalendar.data

import android.util.Log

data class DetailPlanItem(val title : String, val subtitle : String, val date : String, val time : String, val tagColor : Int) : ListDataInterface {

    override fun getitem() : Int {
        return TYPE_DETAIL
    }
}