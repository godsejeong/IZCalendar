package com.jjmin.izcalendar.ui.detailplan

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jjmin.izcalendar.ui.calendar.CalendarUtils
import com.jjmin.izcalendar.utils.DowChangeUtils

class DetailUseCase(val activity: AppCompatActivity){
    var position = activity.intent.getStringExtra("position")
    var date_ = activity.intent.getStringExtra("date")
    var dow = activity.intent.getStringExtra("dow")
    var date = "${date_!!.substring(date_!!.lastIndexOf("/") + 1)} ${DowChangeUtils.toKr(dow!!)}"
    var toolbarDate = "${CalendarUtils.thisMonth}월 ${ChangeDay(date_!!.substring(date_!!.lastIndexOf("/") + 1))}일 ${DowChangeUtils.toKr(dow!!)}"
    var title = activity.intent.getStringArrayListExtra("title")
    var subtitle = activity.intent.getStringArrayListExtra("subtitle")

    fun ChangeDay(day : String): String {
        Log.e("changeday",day)
        return if(day.indexOf("0") == 0){
            day.substring(1)
        }else{
            day
        }
    }
}