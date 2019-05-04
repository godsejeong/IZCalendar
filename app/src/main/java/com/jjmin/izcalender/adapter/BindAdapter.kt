package com.jjmin.izcalender.adapter

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalender.data.DetailPlanItem
import com.jjmin.izcalender.data.TagSpinnerItem
import com.jjmin.izcalender.viewmodel.DetailViewModel
import androidx.appcompat.widget.AppCompatSpinner
import com.jjmin.izcalender.utils.ItemSelectedListener
import android.widget.ArrayAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseBindingAdapter
import android.widget.Toast
import android.R.attr.data
import android.util.Log
import android.util.Log.e as e1


object BindAdapter {
    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun detailsetItems(view: RecyclerView, items: ArrayList<DetailPlanItem>, vm: DetailViewModel) {
        view.adapter?.run {
            if (this is ItemListAdapter) this.submitList(items)
        } ?: run {
            ItemListAdapter(vm).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["spinnerItem", "viewModel"])
    fun tagspinnerItems(view: Spinner, items: ArrayList<TagSpinnerItem>, vm: DetailViewModel) {
        view.adapter.run {
            TagSpinnerAdapter(vm, items).apply {
                view.adapter = this
                this.notifyDataSetChanged()
            }
        }
    }

//    @BindingAdapter(value = ["bind:selectedValue", "bind:selectedValueAttrChanged"], requireAll = false)
//    fun bindSpinnerData(spinner : Spinner,items: ArrayList<TagSpinnerItem>) {
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                var item = parent.getItemAtPosition(position) as TagSpinnerItem
//                item.nowColorName(item.colorname)
//                item.nowColor(item.color)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//
//            }
//        }
//    }

//    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
//    fun captureSelectedValue(pAppCompatSpinner: AppCompatSpinner): String {
//        return pAppCompatSpinner.selectedItem as String
//    }

}