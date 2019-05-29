package com.jjmin.izcalendar.ui.main

import android.util.Log
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
import com.jjmin.izcalendar.data.AllPlan
import com.jjmin.izcalendar.ui.base.DisposableViewModel
import com.jjmin.izcalendar.utils.DowChangeUtils
import com.jjmin.izcalendar.utils.Utils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.HttpException


class MainViewModel(val useCase: MainUserCase, val planRepository: PlanRepository) : DisposableViewModel() {


    var infoList = ArrayList<PlanningItem>()
    var todayList = ArrayList<TodayItem>()

    val _alllist = MutableLiveData<ArrayList<PlanningItem>>(arrayListOf())
    val alllist: LiveData<ArrayList<PlanningItem>> get() = _alllist

    val _todaylist = MutableLiveData<ArrayList<TodayItem>>(arrayListOf())
    val todaylist: LiveData<ArrayList<TodayItem>> get() = _todaylist

    var clandardayList = ArrayList<String>()
    var today = ObservableField<String>()

    init {
        Plan()
        today.set(CalendarModel().today)
    }

    fun Plan() {
        planRepository.allPlanList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                addList(it)
                _todaylist.value = todayList
                _alllist.value = infoList
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
            clandardayList.add(it.day)
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
        val mDay = Utils.today()
        Log.e("mTime", mDay)
        Log.e("Time", day)
        if (day in mDay) {
            Log.e("Ads", "a")
            todayList.add(TodayItem(title, subTitle, timeCheck(time), "TODAY", dow))
        } else {
            infoList.add(PlanningItem(title, subTitle, timeCheck(time), day, dow))
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
}