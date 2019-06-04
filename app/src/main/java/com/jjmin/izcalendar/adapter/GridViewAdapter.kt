package com.jjmin.izcalendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.ThemeItem
import com.jjmin.izcalendar.databinding.ItemThemeBinding

class GridViewAdapter(item: ArrayList<ThemeItem>) : BaseAdapter() {
    var item = item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        lateinit var binding: ItemThemeBinding
        if(view == null) {
            val inflater = LayoutInflater.from(parent?.context)
            binding = DataBindingUtil.inflate(inflater, R.layout.item_theme, parent, false)
            view = binding.root
            view?.tag = binding
            var data = item[position]
            binding.item = data
        }else{
            binding = view?.tag as ItemThemeBinding
            var data = item[position]
            binding.item = data
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return item[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return item.size
    }
}