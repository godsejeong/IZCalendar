package com.jjmin.izcalendar.ui.main

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.data.PlanningItem
import com.jjmin.izcalendar.data.TodayItem
import com.jjmin.izcalendar.ui.calendar.CalendarModel
import java.util.ArrayList
import android.view.View.OnTouchListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.R.id.main_recycler
import kotlinx.android.synthetic.main.activity_main.*


class MainViewModel(useCase: MainUserCase) : ViewModel() {

    val _alllist = MutableLiveData<ArrayList<PlanningItem>>()
    val alllist: LiveData<ArrayList<PlanningItem>> get() = _alllist

    var scrollbar : ConstraintLayout? = null
    var mainRecycler : RecyclerView? = null

    val _todaylist = MutableLiveData<ArrayList<TodayItem>>()
    val todaylist : LiveData<ArrayList<TodayItem>> get() = _todaylist

    var clandardayList = ArrayList<String>()
    var planningInfo = MainModel()
    var today = ObservableField<String>()

    init {
        today.set(CalendarModel().today)
        planInfo()
    }

    fun planInfo() {
        planningInfo.start()
        planningInfo.join()
        _alllist.value = planningInfo.infoList
        clandardayList.addAll(planningInfo.clandardayList)
        _todaylist.value = planningInfo.todayList
    }

    fun monthChange(): String {
        var month = CalendarModel().thisMonth
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

    private lateinit var onTouchListener: OnTouchListener
}