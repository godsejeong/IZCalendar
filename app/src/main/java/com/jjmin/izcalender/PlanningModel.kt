package com.jjmin.izcalender

import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class PlanningModel : AsyncTask<String,Void,ArrayList<PlanningData>>() {

    private var url = "http://m.cafe.daum.net/official-izone/l0C7?boardType=Q"//ㅈ같은 권한 씨발
    private var doc : Document?= null
    var infoList = ArrayList<PlanningData>()


    override fun doInBackground(vararg params: String?): ArrayList<PlanningData>? {
        doc = Jsoup.connect(url).get()
        print(doc)
        var titleTag = doc?.select(".tit_subject")
        var subTag = doc?.select("em")
        var timeTag = doc?.select("span.inner_tit")

        Log.e("titleTag", titleTag!!.size.toString())
        titleTag?.forEach {
            Log.e("data","${titleTag.text()}   ${subTag!!.text()}    ${timeTag!!.text()}")
            infoList.add(PlanningData(titleTag.text(),subTag!!.text(),timeTag!!.text()))
        }

        infoList.forEach {
            Log.e(it.title,it.subtitle + it.time)
        }

        return infoList
    }
}