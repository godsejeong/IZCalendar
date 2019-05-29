package com.jjmin.izcalendar.adapter

import android.annotation.SuppressLint
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.data.TagSpinnerItem
import com.jjmin.izcalendar.ui.detailplan.DetailViewModel
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.GridView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.data.ListDataInterface
import android.util.Log.e as e1
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableArrayList
import com.jjmin.izcalendar.ui.calendar.CalendarAdapter
import com.jjmin.izcalendar.ui.calendar.CalendarView
import com.jjmin.izcalendar.ui.calendar.CalendarViewModel
import com.jjmin.izcalendar.ui.calendar.ClandarData
import com.jjmin.izcalendar.utils.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*


object BindAdapter {

    @JvmStatic
    @BindingAdapter(value = ["addList","addPlanList"])
    fun CalendarSetAdapter(view : GridView,Calendarlist: ObservableArrayList<ClandarData>,planList:ArrayList<String>){
        Log.e("Calendarlist", Calendarlist.toString())
        Log.e("planList", planList.toString())
        view.adapter?.run {
            if(this is CalendarAdapter){
                this.notifyDataSetChanged()
            }
        } ?: run{
            CalendarAdapter(Calendarlist,planList).apply {
                view.adapter = this
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setPlan"])
    fun setPlan(view : CalendarView, list : ArrayList<String>){
        Log.e("setplan", list.toString())
        CalendarViewModel().clandardayList = list
    }

    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun ListAdapter(view: RecyclerView, items: List<ListDataInterface>, vm: ViewModel) {
        view.adapter?.run {
            if (this is ItemListAdapter) {
                this.submitList(items)
            }
        } ?: run {
            ItemListAdapter(vm).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["itemText"])
    fun TextSize(view: TextView, text: String) {
        if (text == "Always") {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.69F)
        } else {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17F)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["MainView", "ScrollbarSize"])
    fun LayoutsetOnTouchListener(view: View, view2: View, scrollbar: View) {
        var y = 0
        view.setOnTouchListener { v, event ->
            val params = view2.layoutParams as ConstraintLayout.LayoutParams
            if (event.action == MotionEvent.ACTION_DOWN) {
                y = event.y.toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (y < event.y) {
                    AnimationUtils.slideToBottom(view)
                    AnimationUtils.animate(view2, params.topMargin,0)
                } else if (y > event.y) {
                    AnimationUtils.slideToTop(view, scrollbar)
                    AnimationUtils.animate(view2, params.topMargin, scrollbar.height)
                }
            }
            return@setOnTouchListener true
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setTodayView","MainView","ScrollbarSize"])
    fun RecyclerviewsetOnTouchListener(view: View, todayview: View,view2 : View,scrollbar: View) {
        var y = 0
        view.setOnTouchListener { v, event ->
            val params = view2.layoutParams as ConstraintLayout.LayoutParams
            if (event.action == MotionEvent.ACTION_DOWN) {
                y = event.y.toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (y < event.y) {
                    AnimationUtils.slideToBottom(todayview)
                    AnimationUtils.animate(view2, params.topMargin,0)
                } else if (y > event.y) {
                    AnimationUtils.slideToTop(todayview,scrollbar)
                    AnimationUtils.animate(view2, params.topMargin, scrollbar.height)
                }
            }
            return@setOnTouchListener true
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["RecyclerScroll","ScrollbarSize"])
    fun setonScrollStatechanged(view: RecyclerView,todayview : View,scrollbar: View) {
        view.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val params = view.layoutParams as ConstraintLayout.LayoutParams
                AnimationUtils.slideToTop(todayview,scrollbar)
                AnimationUtils.animate(view,params.topMargin,scrollbar.height)
            }
        })
    }

    @JvmStatic
    @BindingAdapter(value = ["spinnerItem", "viewModel", "selection"], requireAll = false)
    fun tagspinnerItems(view: Spinner, items: ArrayList<TagSpinnerItem>, vm: DetailViewModel, position: Int) {
        Log.e("testview", "testtest")
        view.adapter.run {
            com.jjmin.izcalendar.adapter.TagSpinnerAdapter(vm, items).apply {
                view.adapter = this
                this.notifyDataSetChanged()
            }
        }
        view.setSelection(position)
    }
}