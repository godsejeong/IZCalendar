package com.jjmin.izcalendar.utils

import android.view.View
import android.widget.AdapterView

interface GridViewItemClickListener{
    fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int?, id: Long?)
}