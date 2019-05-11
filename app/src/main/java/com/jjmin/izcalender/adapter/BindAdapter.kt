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
import java.text.ParsePosition
import android.util.Log.e as e1


object BindAdapter {
    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun detailsetItems(view: RecyclerView, items: ArrayList<DetailPlanItem>, vm: DetailViewModel) {
        view.adapter?.run {
            if (this is ItemListAdapter) {
                this.submitList(items)
                Log.e("test","test1")
            }
        } ?: run {
            ItemListAdapter(vm).apply {
                view.adapter = this
                this.submitList(items)
                Log.e("test","test2")
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["spinnerItem", "viewModel","selection"],requireAll = false)
    fun tagspinnerItems(view: Spinner, items: ArrayList<TagSpinnerItem>, vm: DetailViewModel,position: Int) {
        Log.e("testview","testtest")
        view.adapter.run {
            TagSpinnerAdapter(vm, items).apply {
                view.adapter = this
                this.notifyDataSetChanged()
            }
        }

        view.setSelection(position)

    }
}