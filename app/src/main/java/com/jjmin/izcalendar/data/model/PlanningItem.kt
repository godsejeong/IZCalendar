package com.jjmin.izcalendar.data.model

data class PlanningItem(var title : String?,var subtitle : String?,var time : String?,var day : String?,var dow : String?,var color : Int?) :
    ListDataInterface {
    override fun getitem(): Int {
        return TYPE_MAIN
    }
}