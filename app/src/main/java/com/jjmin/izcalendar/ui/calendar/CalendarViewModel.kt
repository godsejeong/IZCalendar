package com.jjmin.izcalendar.ui.calendar

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.data.TodayItem
import java.util.*
import kotlin.collections.ArrayList

class CalendarViewModel : ViewModel() {
    var Calendarlist = ObservableArrayList<ClandarData>()

    var clandardayList : ArrayList<String>? = arrayListOf()

    var model = CalendarModel()
    var today = ObservableField<String>()

    init {
        addClaendarList()
        today.set(model.today)
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