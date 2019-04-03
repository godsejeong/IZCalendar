package com.jjmin.izcalender.model

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.data.TodayData
import com.jjmin.izcalender.util.RetrofitServices
import com.jjmin.izcalender.util.Utils
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat
import java.util.*

class PlanningModel : Thread() {
    var infoList = ArrayList<PlanningData>()
    var todayList = ArrayList<TodayData>()
    var res = Utils.postservice.allPlanList()

    override fun run(){
        Log.e("requestCode", res.execute().code().toString())
        var planJson = res.clone().execute().body()!!.plan

        planJson.forEach{
            Log.e("day",it.day)
            if(it.title.size >= 2){
                for (i in 0 until it.title.size){
                    searchToday(it.title[i],it.subTitle[i],timeCheck(it.time[i]),it.day,Dowchange(it.dow))
                }
            }else{
                searchToday(it.title[0],it.subTitle[0],timeCheck(it.time[0]),it.day,Dowchange(it.dow))
            }
        }
    }

    fun searchToday(title : String,subTitle : String, time : String, day : String ,dow : String){
        val mSimpleDateFormat = SimpleDateFormat("MM/dd", Locale.KOREA)
        val date = Date()
        val mDay = mSimpleDateFormat.format(date)
        Log.e("mTime",mDay)
        Log.e("Time",day)
        if(day in mDay){
            Log.e("Ads","a")
            todayList.add(TodayData(title,subTitle,timeCheck(time),"TODAY",dow))
        }else{
            infoList.add(PlanningData(title,subTitle,timeCheck(time),day,dow))
        }
    }

    fun timeCheck(time : String) : String{
        return if(time == "하루종일"){
            "Always"
        }else{
            time
        }
    }

    fun Dowchange(dow : String) : String{
        return when(dow){
            "월요일" ->  "Mon"
            "화요일" ->  "Tue"
            "수요일" ->  "Wed"
            "목요일" ->  "Tue"
            "금요일" ->  "Fri"
            "토요일" ->  "Sat"
            "일요일" ->  "Sun"
            else ->  ""
        }
    }
}