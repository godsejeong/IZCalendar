package com.jjmin.izcalendar.ui.calendar

import java.text.SimpleDateFormat
import java.util.*

object CalendarUtils {
    var cal = Calendar.getInstance()
    var today = cal.get(java.util.Calendar.DATE).toString()
    val year = cal.get(Calendar.YEAR)
    var thisMonth = (cal.get(java.util.Calendar.MONTH) + 1)
    val mon = cal.get(Calendar.MONTH)

    fun getstrmon(): String {
        return if (mon + 1 < 10) {
            "0${mon + 1}"
        } else {
            (mon + 1).toString()
        }
    }

    fun getDateDay(dateType: String): Int {
        val formatter = SimpleDateFormat("yyyyMMdd")
        val date = formatter.parse(dateType)
        var cal = Calendar.getInstance()
        cal.time = date
        var dayNum = cal.get(Calendar.DAY_OF_WEEK)

        return dayNum
    }

    fun today(): String {
        val mSimpleDateFormat = SimpleDateFormat("MM/dd", Locale.KOREA)
        val date = Date()
        return mSimpleDateFormat.format(date)
    }
}