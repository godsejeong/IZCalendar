package com.jjmin.izcalendar.adapter

import android.app.Activity
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjmin.izcalendar.data.ListDataInterface
import android.util.Log.e as e1

object BindAdapter {
    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun ListAdapter(view: RecyclerView, items: List<ListDataInterface>, vm: ViewModel) {
        view.adapter?.run {
            if (this is ItemListAdapter) {
                this.submitList(items)
            }
        } ?: run {
            ItemListAdapter(vm,null).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }
}