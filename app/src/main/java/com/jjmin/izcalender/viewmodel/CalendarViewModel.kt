package com.jjmin.izcalender.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.util.Log
import android.widget.CalendarView
import com.jjmin.izcalender.calendar.ClandarData
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.data.TodayData
import com.jjmin.izcalender.model.CalendarModel
import java.text.SimpleDateFormat
import java.util.*

class CalendarViewModel : ViewModel{
    var Calendarlist = ObservableArrayList<ClandarData>()

    var model = CalendarModel()
    var today = ObservableField<String>()

    init {
        addClaendarList()
    }

    override fun onCreate() {
        today.set(model.today) }

    override fun onPause() {}
    override fun onResume() {}
    override fun onDestroy() {}

    fun monthChange(): String {
        var month = model.thisMonth
        return when (month) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            12 -> "December"
            else -> ""
        }
    }

    fun addClaendarList(){
        Calendarlist.add(ClandarData("S"))
        Calendarlist.add(ClandarData("M"))
        Calendarlist.add(ClandarData("T"))
        Calendarlist.add(ClandarData("W"))
        Calendarlist.add(ClandarData("T"))
        Calendarlist.add(ClandarData("F"))
        Calendarlist.add(ClandarData("S"))

        var beforemonth = model.getDateDay("${model.year}${model.getstrmon()}01")

        //1일 - 전달 마지막날
        (1 until (beforemonth)).forEach {
            Calendarlist.add(ClandarData(""))
        }
        setCalendarDate(model.mon)

        (0 until Calendarlist.size).forEach {
            Log.e("list", Calendarlist[it].day)
        }
    }

    fun setCalendarDate(month: Int) {
        model.cal.set(Calendar.MONTH, month)
        (1..model.cal.getActualMaximum(Calendar.DAY_OF_MONTH)).forEach {
            Calendarlist.add(ClandarData(it.toString()))
        }
    }

}