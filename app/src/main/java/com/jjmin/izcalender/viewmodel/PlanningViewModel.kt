package com.jjmin.izcalender.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import com.jjmin.izcalender.data.PlanningItem
import com.jjmin.izcalender.data.TodayItem
import com.jjmin.izcalender.model.PlanningModel
import java.util.ArrayList

class PlanningViewModel : ViewModel{
    var alllist = ObservableArrayList<PlanningItem>()
    var todaylist = ObservableArrayList<TodayItem>()
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