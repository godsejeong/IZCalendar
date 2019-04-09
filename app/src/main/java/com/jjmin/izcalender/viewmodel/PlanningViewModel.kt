package com.jjmin.izcalender.viewmodel

import android.databinding.ObservableArrayList
import android.util.Log
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.data.TodayData
import com.jjmin.izcalender.model.PlanningModel
import java.util.ArrayList

class PlanningViewModel : ViewModel{
    var alllist = ObservableArrayList<PlanningData>()
    var todaylist = ObservableArrayList<TodayData>()
    var clandardayList = ArrayList<String>()
    var planningInfo = PlanningModel()

    override fun onCreate() {
        planInfo()
    }

    override fun onPause() {}

    override fun onResume() {}

    override fun onDestroy() {}

    fun planInfo() {
        planningInfo.start()
        planningInfo.join()
        alllist.addAll(planningInfo.infoList)
        clandardayList.addAll(planningInfo.clandardayList)
        todaylist.addAll(planningInfo.todayList)
    }
}