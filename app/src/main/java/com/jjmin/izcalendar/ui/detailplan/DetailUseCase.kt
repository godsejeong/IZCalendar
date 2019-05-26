package com.jjmin.izcalendar.ui.detailplan

import androidx.appcompat.app.AppCompatActivity
import com.jjmin.izcalendar.utils.DowChangeUtils

class DetailUseCase(private val activity: AppCompatActivity){
    var position = activity.intent.getIntExtra("position", 0)
    var date_ = activity.intent.getStringExtra("date")
    var dow = activity.intent.getStringExtra("dow")
    var date = "${date_!!.substring(date_!!.lastIndexOf("/") + 1)} ${DowChangeUtils.toKr(dow!!)}"
    var title = activity.intent.getStringArrayListExtra("title")
    var subtitle = activity.intent.getStringArrayListExtra("subtitle")
}