package com.jjmin.izcalendar.adapter

import android.util.Log
import android.view.View
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.data.TagSpinnerItem
import com.jjmin.izcalendar.ui.detailplan.DetailViewModel
import com.jjmin.izcalendar.utils.AnimationUtils

object DetailBindAdapter{
    @JvmStatic
    @BindingAdapter(value = ["RecyclerScroll", "ScrollbarSize"])
    fun setonScrollStatechanged(view: RecyclerView, todayview: View, scrollbar: View) {
        view.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val params = view.layoutParams as ConstraintLayout.LayoutParams
                AnimationUtils.slideToTop(todayview, scrollbar)
                AnimationUtils.animate(view, params.topMargin, scrollbar.height)
            }
        })
    }

    @JvmStatic
    @BindingAdapter(value = ["spinnerItem", "viewModel", "selection"], requireAll = false)
    fun tagspinnerItems(view: Spinner, items: ArrayList<TagSpinnerItem>, vm: DetailViewModel, position: Int) {
        Log.e("testview", "testtest")
        view.adapter.run {
            TagSpinnerAdapter(vm, items).apply {
                view.adapter = this
                this.notifyDataSetChanged()
            }
        }
        view.setSelection(position)
    }
}