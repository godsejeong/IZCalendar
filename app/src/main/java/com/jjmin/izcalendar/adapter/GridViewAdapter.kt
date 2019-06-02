package com.jjmin.izcalendar.adapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.ThemeItem
import com.jjmin.izcalendar.databinding.ItemThemeBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlinx.android.synthetic.main.item_theme.view.*
import android.graphics.drawable.shapes.OvalShape
import android.util.TypedValue
import android.widget.GridView


class GridViewAdapter(item: ArrayList<ThemeItem>) : BaseAdapter() {

    var item = item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        lateinit var binding: ItemThemeBinding
        if(view == null) {
            val inflater = LayoutInflater.from(parent?.context)
            binding = DataBindingUtil.inflate(inflater, R.layout.item_theme, parent, false)
            view = binding.root
            view?.tag = binding
        }else{
            binding.item = view?.tag as ThemeItem?
        }

        var data = item[position]
        binding.item = data

        return view!!
    }

    override fun getItem(position: Int): Any {
        return item[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return item.size
    }
}