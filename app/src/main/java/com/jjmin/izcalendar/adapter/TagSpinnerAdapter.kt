package com.jjmin.izcalendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.TagSpinnerItem
import com.jjmin.izcalendar.databinding.ItemTagSpinnerDropdownBinding
import com.jjmin.izcalendar.databinding.ItemTagSpinnerNormalBinding
import com.jjmin.izcalendar.utils.SetTheme

class TagSpinnerAdapter(val vm: ViewModel, var item: ArrayList<TagSpinnerItem>) : BaseAdapter() {
    var items = item

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        var binding: ItemTagSpinnerNormalBinding
        if (view == null) {
            var inflater = LayoutInflater.from(parent!!.context)
            binding = DataBindingUtil.inflate(inflater, R.layout.item_tag_spinner_normal, parent, false)

            view = binding.root
            view.tag = binding
        }else{
            binding = view!!.tag as ItemTagSpinnerNormalBinding
        }
        binding.item = items[position]
        binding.theme = SetTheme()
        return view!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        var binding: ItemTagSpinnerDropdownBinding
        if (view == null) {
            var inflater = LayoutInflater.from(parent!!.context)
            binding = DataBindingUtil.inflate(inflater, R.layout.item_tag_spinner_dropdown, parent, false)

            view = binding.root
            view.tag = binding
        }else{
            binding = view!!.tag as ItemTagSpinnerDropdownBinding
        }
        binding.item = items[position]
        binding.theme = SetTheme()

        return view!!
    }
}