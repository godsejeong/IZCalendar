package com.jjmin.izcalender.model

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.util.RetrofitServices
import com.jjmin.izcalender.util.Utils
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PlanningModel : Thread() {
    var infoList = ArrayList<PlanningData>()
    var res = Utils.postservice.allPlanList()

    override fun run(){
        Log.e("a", res.execute().code().toString())
        var planJson = res.clone().execute().body()!!.plan

        planJson.forEach{
            infoList.add(PlanningData(null,null,null,it.day,true))
            Log.e("day",it.day)
            if(it.title.size >= 2){
                for (i in 0 until it.title.size){
                    infoList.add(PlanningData(it.title[i],it.subTitle[i],it.time[i],null,false))
                }
            }else{
                infoList.add(PlanningData(it.title[0],it.subTitle[0],it.time[0],null,false))
            }
        }
    }
}