package com.jjmin.izcalendar.data

import android.util.Log
import android.widget.Toast

data class TodayItem(var title : String?,var subtitle : String?,var time : String?,var day : String?,var dow : String?) : ListDataInterface {
    override fun getitem(): Int {
        return TYPE_TODAY
    }
}