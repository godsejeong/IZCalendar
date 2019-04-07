package com.jjmin.izcalender.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.LinearLayout
import com.jjmin.izcalender.R
import com.jjmin.izcalender.data.AllPlanData
import com.jjmin.izcalender.data.PlanningData
import kotlinx.android.synthetic.main.activity_main.view.*

import kotlinx.android.synthetic.main.calendar_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalendarView : LinearLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    lateinit var gridAdapter: CalendarAdapter
    var cal = Calendar.getInstance()
    var list = ArrayList<ClandarData>()
    var strmon = ""
    var clandardayList = ArrayList<String>()

    fun init(view: View) {
        val year = cal.get(Calendar.YEAR)
        val mon = cal.get(Calendar.MONTH)
        var isclick = true
        cal.set(Calendar.MONTH, mon)

        strmon = if (mon + 1 < 10) {
            "0${mon + 1}"
        } else {
            (mon + 1).toString()
        }

        Log.e(year.toString(), "$year$strmon${setMonthEnd(mon - 1)}")
        var beforemonth = getDateDay("$year${strmon}01")
        Log.e("beforemonth", beforemonth.toString())
        list.add(ClandarData("S"))
        list.add(ClandarData("M"))
        list.add(ClandarData("T"))
        list.add(ClandarData("W"))
        list.add(ClandarData("T"))
        list.add(ClandarData("F"))
        list.add(ClandarData("S"))

        var dayNum = cal.get(Calendar.DAY_OF_WEEK)

        //1일 - 전달 마지막날
        (1 until (beforemonth)).forEach {
            list.add(ClandarData(""))
        }

        Log.e("mon", mon.toString())
        setCalendarDate(mon)

        gridAdapter = CalendarAdapter(context, list, clandardayList)
        view.calendarGridView.numColumns = 7
        view.calendarGridView.adapter = gridAdapter

    }

    fun setPlan(list: ArrayList<String>) {
        clandardayList.addAll(list)
    }

    fun initView() {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = li!!.inflate(R.layout.calendar_view, this@CalendarView, false) as View
        addView(view)
        init(view)
    }

    fun getDateDay(dateType: String): Int {
        val formatter = SimpleDateFormat("yyyyMMdd")
        val date = formatter.parse(dateType)
        var cal = Calendar.getInstance()
        cal.time = date

        var dayNum = cal.get(Calendar.DAY_OF_WEEK)

        return dayNum
    }

    fun setCalendarDate(month: Int) {
        cal.set(Calendar.MONTH, month)
        (1..cal.getActualMaximum(Calendar.DAY_OF_MONTH)).forEach {
            list.add(ClandarData(it.toString()))
        }
    }

    fun setMonthEnd(month: Int): Int {
        cal.set(Calendar.MONTH, month)
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
}