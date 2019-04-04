package com.jjmin.izcalender.calendar

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.jjmin.izcalender.R
import java.util.*
import kotlinx.android.synthetic.main.calendar_view.*
import java.text.SimpleDateFormat


class CalendarView : Activity() {
    lateinit var gridAdapter: CalendarAdapter
    var cal = Calendar.getInstance()
    var list = ArrayList<String>()
    var strmon = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_view)
        val year = cal.get(Calendar.YEAR)
        val mon = cal.get(Calendar.MONTH)


        cal.set(Calendar.MONTH,mon)

        strmon = if(mon < 10){
            "0$mon"
        }else{
            mon.toString()
        }

        Log.e(year.toString(),"$year$strmon${setMonthEnd(mon-1)}")
        Log.e("dayday", getDateDay("$year$strmon${setMonthEnd(mon-1)}").toString())

        list.add("S")
        list.add("M")
        list.add("T")
        list.add("W")
        list.add("T")
        list.add("F")
        list.add("S")

//        var dayNum = cal.get(Calendar.DAY_OF_WEEK)
//
//        //1일 - 요일 매칭 시키기 위해 공백 add
//        (1..dayNum).forEach {
//            list.add("")
//        }


//        getDateDay("")
//        val dayOfWeek =
//            cal.get(Calendar.DAY_OF_WEEK)
//        var korDayOfWeek = 0

        Log.e("mon", mon.toString())
        setCalendarDate(mon)



        gridAdapter = CalendarAdapter(applicationContext, list)
        calendarGridView.numColumns = 7
        calendarGridView.adapter = gridAdapter
    }

    fun getDateDay(dateType: String): Int {
        var day = 0
        val formatter = SimpleDateFormat("yyyyMMdd")

        val date = formatter.parse(dateType)
//        var nDate = dateFormat?.parse(dateType)

        var cal = Calendar.getInstance()
        cal.time = date

        var dayNum = cal.get(Calendar.DAY_OF_WEEK)

        when (dayNum) {
            1 -> day = 1
            2 -> day = 2
            3 -> day = 3
            4 -> day = 4
            5 -> day = 5
            6 -> day = 6
            7 -> day = 7
        }

        return day
    }

    fun setCalendarDate(month: Int) {
        cal.set(Calendar.MONTH, month)
        (1..cal.getActualMaximum(Calendar.DAY_OF_MONTH)).forEach {
            list.add(it.toString())
        }
    }

    fun setMonthEnd(month: Int) : Int{
        cal.set(Calendar.MONTH, month)
       return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

}