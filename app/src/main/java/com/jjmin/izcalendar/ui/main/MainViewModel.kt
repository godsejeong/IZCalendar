package com.jjmin.izcalendar.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jjmin.izcalendar.data.model.AllPlan
import com.jjmin.izcalendar.data.model.PlanningItem
import com.jjmin.izcalendar.data.model.TodayItem
import com.jjmin.izcalendar.ui.base.DisposableViewModel
import com.jjmin.izcalendar.ui.calendar.CalendarUtils
import com.jjmin.izcalendar.utils.DowChangeUtils
import com.jjmin.izcalendar.utils.MarketVersion
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import com.jjmin.izcalendar.data.remote.PlanRepository


class MainViewModel(val useCase: MainUserCase, val planRepository: PlanRepository) : DisposableViewModel() {
    var infoList = ArrayList<PlanningItem>(arrayListOf())
    var todayArrayList = ArrayList<TodayItem>(arrayListOf())
    var calendarlist = ArrayList<String>(arrayListOf())

    val _alllist = MutableLiveData<ArrayList<PlanningItem>>(arrayListOf())
    val alllist: LiveData<ArrayList<PlanningItem>> get() = _alllist

    val _todaylist = MutableLiveData<ArrayList<TodayItem>>(arrayListOf())
    val todaylist: LiveData<ArrayList<TodayItem>> get() = _todaylist

    val _claendarsetPlenlist = MutableLiveData<ArrayList<String>>(arrayListOf())
    val claendarsetPlenlist: LiveData<ArrayList<String>> get() = _claendarsetPlenlist
    var today = ObservableField<String>()

    init {
        Plan()
        today.set(CalendarUtils.today)
        MarketVersion(useCase.activity).execute()
    }

    fun Plan() {
        todayArrayList.clear()
        infoList.clear()
        calendarlist.clear()
        planRepository.allPlanList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                addList(it)
                _todaylist.value = todayArrayList
                _alllist.value = infoList
                _claendarsetPlenlist.value = calendarlist
            }) { e ->
                //Network 오류 부분
                Log.e("error", e.message)
            }
            .also {
                addDisposable(it)
            }
    }

    fun addList(it: AllPlan) {
        it.plan.forEach {
            Log.e("day", it.day)
            calendarlist.add(it.day)
            if (it.title.size >= 2) {
                for (i in 0 until it.title.size) {
                    searchToday(it.title[i], it.subTitle[i], timeCheck(it.time[i]), it.day, DowChangeUtils.toEn(it.dow))
                }
            } else {
                searchToday(it.title[0], it.subTitle[0], timeCheck(it.time[0]), it.day, DowChangeUtils.toEn(it.dow))
            }
        }
    }

    fun searchToday(title: String, subTitle: String, time: String, day: String, dow: String) {
        val mDay = CalendarUtils.today()
        Log.e("mTime", mDay)
        Log.e("Time", day)
        if (day in mDay) {
            Log.e("Ads", "a")
            todayArrayList.add(
                TodayItem(
                    title, subTitle, timeCheck(time), "TODAY", dow,
                    if (SharedPreprecncesUtils.getColorTag(day) != 0) SharedPreprecncesUtils.getColorTag(
                        day
                    ) else SetTheme().themecolor.value!!
                )
            )
        } else {
            infoList.add(
                PlanningItem(
                    title, subTitle, timeCheck(time), day, dow,
                    if (SharedPreprecncesUtils.getColorTag(day) != 0) SharedPreprecncesUtils.getColorTag(
                        day
                    ) else SetTheme().themecolor.value!!
                )
            )
        }
    }

    fun timeCheck(time: String): String {
        return if (time == "하루종일") {
            "Always"
        } else {
            time
        }
    }

    fun monthChange(): String {
        var month = CalendarUtils.thisMonth
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
}