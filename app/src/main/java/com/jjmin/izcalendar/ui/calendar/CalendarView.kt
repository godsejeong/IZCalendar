package com.jjmin.izcalendar.ui.calendar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.PlanningItem
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.util.*
import kotlin.collections.ArrayList
import com.jjmin.izcalendar.utils.AnimationUtils
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils


class CalendarView : LinearLayout {
    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    var Calendarlist = ArrayList<ClandarData>()
    lateinit var gridAdapter: CalendarAdapter
    private var calndardayList: ArrayList<String> = arrayListOf()
    private lateinit var recyclerview: RecyclerView
    private lateinit var recyclerviewList: ArrayList<PlanningItem>
    private lateinit var todayView: ConstraintLayout
    private lateinit var scrollbarLayout: ConstraintLayout
    var isclick = true

    fun setTodayView(todayview: ConstraintLayout) {
        this.todayView = todayview
    }
    fun setScrollbarLayout(scrollbarLayout : ConstraintLayout){
        this.scrollbarLayout = scrollbarLayout
    }

    fun setRecyclerviewList(recyclerlist: ArrayList<PlanningItem>) {
        this.recyclerviewList = recyclerlist
    }

    fun setRecyclerview(recyclerview: RecyclerView) {
        this.recyclerview = recyclerview
    }

    fun setCalndardayList(list: ArrayList<String>) {
        calndardayList.addAll(list)
        gridAdapter.notifyDataSetChanged()
        Log.e("setplanlist", calndardayList.toString())
    }

    fun changeList(){
        Calendarlist.clear()
        addClaendarList()
        gridAdapter.notifyDataSetChanged()
    }

    fun init(view: View) {
        gridAdapter = CalendarAdapter(Calendarlist, calndardayList)
        view.calendarGridView.adapter = gridAdapter

        calendarGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                var datePosition = Calendarlist.get(position)?.day
                try {
                    datePosition = CalendarUtils.returnDate(datePosition)
                    Log.e("dataPosition", datePosition)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
                (0 until recyclerviewList.size!!).forEach {
                    var planlist = recyclerviewList!![it]
                    if (datePosition == planlist.day) {
                        recyclerview.smoothScrollToPosition(it)
                        AnimationUtils.slideToTop(todayView,scrollbarLayout)
                        return@forEach
                    } else if (datePosition == "") {

                    } else if (datePosition == CalendarUtils.today()) {
                        AnimationUtils.slideToBottom(todayView)
                        return@forEach
                    }
                }
            }
    }

    fun initView(context: Context) {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = li!!.inflate(R.layout.calendar_view, this@CalendarView, false) as View
        addClaendarList()
        addView(view)
        init(view)
    }

    fun addClaendarList() {
        Calendarlist.add(ClandarData("S",null))
        Calendarlist.add(ClandarData("M",null))
        Calendarlist.add(ClandarData("T",null))
        Calendarlist.add(ClandarData("W",null))
        Calendarlist.add(ClandarData("T",null))
        Calendarlist.add(ClandarData("F",null))
        Calendarlist.add(ClandarData("S",null))

        var beforemonth = CalendarUtils.getDateDay("${CalendarUtils.year}${CalendarUtils.getstrmon()}01")

        //1일 - 전달 마지막날
        (1 until (beforemonth)).forEach {
            Calendarlist.add(ClandarData("",null))
        }
        setCalendarDate(CalendarUtils.mon)

        (0 until Calendarlist.size).forEach {
            Log.e("list", Calendarlist[it].day)
        }
    }
    fun setCalendarDate(month: Int) {
        var Checkdate: String
        CalendarUtils.cal.set(Calendar.MONTH, month)
        (1..CalendarUtils.cal.getActualMaximum(Calendar.DAY_OF_MONTH)).forEach {
            Checkdate = CalendarUtils.returnDate(it.toString())
            Calendarlist.add(ClandarData(it.toString(),SharedPreprecncesUtils.getColorTag(Checkdate)))
        }
    }
}