package com.jjmin.izcalendar.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.jjmin.izcalendar.data.ListDataInterface
import com.jjmin.izcalendar.data.TodayItem
import com.jjmin.izcalendar.ui.calendar.CalendarUtils
import com.jjmin.izcalendar.ui.detailplan.DetailPlanActivity
import com.jjmin.izcalendar.utils.AnimationUtils

object MainBindAdapter{
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
                    AnimationUtils.animate(view2, params.topMargin, 0)
                } else if (y > event.y) {
                    AnimationUtils.slideToTop(view, scrollbar)
                    AnimationUtils.animate(view2, params.topMargin, scrollbar.height)
                }
            }
            return@setOnTouchListener true
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["setTodayView", "MainView", "ScrollbarSize", "AddTodayList","SetActivity"])
    fun RecyclerviewsetOnTouchListener(
        view: View,
        todayview: View,
        view2: RecyclerView,
        scrollbar: View,
        Todaylsit: ArrayList<TodayItem>,
        activity: Activity
    ) {
        var y = 0

        var gestureDetector = GestureDetector(view.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })

        view.setOnTouchListener { v, event ->
            val params = view2.layoutParams as ConstraintLayout.LayoutParams
            if (gestureDetector.onTouchEvent(event)) {
                try {
                    val childView = view2.findChildViewUnder(event.x, event.y)
                    val currentPosition = view2.getChildAdapterPosition(childView!!)
                    var currentItemStudent : TodayItem
                    currentItemStudent = if(Todaylsit.size == currentPosition)
                        Todaylsit[currentPosition-1]
                    else
                        Todaylsit[currentPosition]


                    var namelist = ArrayList<String>()
                    var subtitleList = ArrayList<String>()

                    //클릭
                    (0 until Todaylsit.size).forEach { position ->
                        Log.e("title", currentItemStudent.title!!)
                        Log.e("subtitle", currentItemStudent.subtitle!!)
                        namelist.add(currentItemStudent.title!!)
                        subtitleList.add(currentItemStudent.subtitle!!)
                    }

                    var intent = Intent(view.context, DetailPlanActivity::class.java)

                    var Today : String
                    Today = CalendarUtils.returnDate(CalendarUtils.today)

                    intent.putExtra("position", Today)
                    intent.putExtra("date", CalendarUtils.today())
                    intent.putExtra("dow", currentItemStudent.dow)
                    intent.putExtra("title", namelist)
                    intent.putExtra("subtitle", subtitleList)

                    activity.startActivity(intent)
                } catch (e: KotlinNullPointerException) {
                    e.printStackTrace()
                }
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                y = event.y.toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (y < event.y) {
                    AnimationUtils.slideToBottom(todayview)
                    AnimationUtils.animate(view2, params.topMargin, 0)
                } else if (y > event.y) {
                    AnimationUtils.slideToTop(todayview, scrollbar)
                    AnimationUtils.animate(view2, params.topMargin, scrollbar.height)
                }
            }
            return@setOnTouchListener true
        }


    }
}