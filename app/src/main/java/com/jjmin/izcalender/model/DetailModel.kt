package com.jjmin.izcalender.model

import android.content.Context
import android.util.Log
import com.jjmin.izcalender.utils.Utils

class DetailModel(date: String) : Thread() {
    var date = ArrayList<String>()
    var time = ArrayList<String>()
    var res = Utils.postservice.DetailLink("$date")
    override fun run() {
        val request = res.clone().execute().body()!!.detailPlan
        (0 until request.size).forEach {
            val array = request[it].split("\n")
            date.add(array[0])
            time.add(array[1])
            Log.e(array[0],array[1])
        }
    }
}


