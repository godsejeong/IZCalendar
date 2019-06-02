package com.jjmin.izcalendar.adapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.data.ListDataInterface
import android.util.Log.e as e1

object BindAdapter {
    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun ListAdapter(view: RecyclerView, items: List<ListDataInterface>, vm: ViewModel) {
        view.adapter?.run {
            Log.e("sadfadsf","Asdfasdfasdfasdfasdfasdfasdfad")
            if (this is ItemListAdapter) {
                this.submitList(items)
            }
        } ?: run {
            Log.e("Asdfadsf","ASDfasdf")
            ItemListAdapter(vm,null).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }
}