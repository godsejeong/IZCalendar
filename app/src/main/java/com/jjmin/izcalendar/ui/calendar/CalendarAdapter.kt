package com.jjmin.izcalendar.ui.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableArrayList
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.utils.SetTheme
import java.util.*


class CalendarAdapter(list: ArrayList<ClandarData>, planList: ArrayList<String>) : BaseAdapter() {
    val list: ArrayList<ClandarData>? = list
    var planList: ArrayList<String> = planList
    var cal = Calendar.getInstance()

    override fun getItem(position: Int): Any {
        return list!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list?.size!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var holder: ViewHolder? = null
        var inflater = parent?.context!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        if (view == null) {
            view = inflater?.inflate(R.layout.item_calendar_gridview, parent, false) as View
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        var item = list!![position]
        holder.dayTv.text = item.day
        item.tagColor?.let {
            Log.e("colorit", it.toString())
            if(it!=0)
                holder.infoIv.backgroundTintList = parent!!.context.resources.getColorStateList(it)
        }

//        var tagimg = holder.infoIv.background as Drawable
//        tagimg.setTint(SetTheme().themecolor)
        holder.infoIv.backgroundTintList = parent.context.resources.getColorStateList(SetTheme().themecolor.value!!)
        //오늘 day 가져옴
        val today = cal.get(Calendar.DATE)
        val sToday = today.toString()
        if (sToday == item.day) { //오늘 day 텍스트 컬러 변경
            Log.e("sToday",sToday)
            holder.dayTv.setTextColor(parent.context.resources.getColorStateList(SetTheme().themecolor.value!!))
            holder.dayTv.setBackgroundResource(R.drawable.bg_calendarview_today)
        }
        Log.e("Adf",planList!!.size.toString())
        run loop@{
            (0 until planList!!.size).forEach {
                if (inputday(planList!![it]) == item.day) {
                    holder.infoIv.visibility = View.VISIBLE
                    return@loop
                } else {
                    holder.infoIv.visibility = View.INVISIBLE
                }
            }
        }
        return view
    }

    fun inputday(str : String) : String{
        var result = str.substring(str.lastIndexOf("/")+1)
        if(result[0] in "0"){
            result = result[1].toString()
        }
        Log.e("result",result)
        return result
    }

    class ViewHolder(view: View) {
        var dayTv = view.findViewById<TextView>(R.id.calendarTv)
        var infoIv = view.findViewById<ImageView>(R.id.calendarNoti)
    }
}