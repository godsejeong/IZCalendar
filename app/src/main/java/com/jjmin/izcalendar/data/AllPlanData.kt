package com.jjmin.izcalendar.data

import com.google.gson.annotations.SerializedName

class AllPlanData{
    @SerializedName("day")
    var day : String = ""

    @SerializedName("dow")
    var dow : String = ""

    @SerializedName("title")
    lateinit var title : List<String>

    @SerializedName("subTitle")
    lateinit var subTitle : List<String>

    @SerializedName("time")
    lateinit var time : List<String>

    @SerializedName("link")
    lateinit var link : List<String>
}