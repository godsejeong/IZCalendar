package com.jjmin.izcalender.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.jjmin.izcalender.R


class CalendarAdapter(context: Context, list: ArrayList<String>) : BaseAdapter() {
    val list: ArrayList<String>? = list
    var context = context

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
        var inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        if (view == null) {
            view = inflater?.inflate(R.layout.item_calendar_gridview, parent, false) as View
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }


        holder.dayTv.text = getItem(position).toString()
        holder.infoIv.visibility = View.INVISIBLE
        return view
    }

    class ViewHolder(view : View) {
         var dayTv = view.findViewById<TextView>(R.id.calendarTv)
         var infoIv  = view.findViewById<ImageView>(R.id.calendarNoti)
    }
}