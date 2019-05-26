package com.jjmin.izcalendar.ui.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.jjmin.izcalendar.R
import kotlinx.android.synthetic.main.calendar_view.view.*
import kotlin.collections.ArrayList


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

    lateinit var gridAdapter: CalendarAdapter
    var list = ArrayList<ClandarData>()
    var strmon = ""
    var clandardayList = ArrayList<String>()
    var model = CalendarViewModel()

    fun setPlan(list: ArrayList<String>) {
        clandardayList.addAll(list)
    }

    fun init(view: View) {
        gridAdapter = CalendarAdapter(context, model.Calendarlist, clandardayList)
        view.calendarGridView.numColumns = 7
        view.calendarGridView.adapter = gridAdapter
    }

    fun initView(context: Context) {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = li!!.inflate(R.layout.calendar_view,this@CalendarView, false) as View
        addView(view)
        init(view)
    }
}