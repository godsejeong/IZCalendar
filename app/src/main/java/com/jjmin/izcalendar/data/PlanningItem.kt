package com.jjmin.izcalendar.data

import android.R
import android.app.Activity
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jjmin.izcalendar.ui.main.MainActivity

data class PlanningItem(var title : String?,var subtitle : String?,var time : String?,var day : String?,var dow : String?) : ListDataInterface{
    override fun getitem(): Int {
        return TYPE_MAIN
    }
}