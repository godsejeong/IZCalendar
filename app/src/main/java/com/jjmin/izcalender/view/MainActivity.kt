package com.jjmin.izcalender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import com.github.nitrico.lastadapter.*
import com.jjmin.izcalender.BR
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.model.PlanningModel
import kotlinx.android.synthetic.main.activity_main.*
import com.jjmin.izcalender.databinding.ItemPlanningBinding
import com.jjmin.izcalender.R
import android.support.constraint.ConstraintLayout
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.util.Log
import com.jjmin.izcalender.data.TodayData
import com.jjmin.izcalender.databinding.ItemPlanningTodayBinding
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.widget.AbsListView
import android.view.GestureDetector


class MainActivity : AppCompatActivity() {
    var y = 0
    lateinit var detector: GestureDetector
    var alllist = ArrayList<Any>()
    var todaylist = ArrayList<TodayData>()
    var planningInfo = PlanningModel()
    var set: ConstraintSet = ConstraintSet()
    var scrollBl = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendarLayout.bringToFront()
        planInfo()

        detector = GestureDetector(applicationContext, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent?) {
            }


            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
                Log.e("af", "Af")
                if (e1!!.y > e2!!.y && scrollBl) {
                    slideToTop(todayView)
                    animate(params.topMargin, todayScrollbar.height)
                    scrollBl = false

                } else if (e1!!.y < e2!!.y && !scrollBl) {
                    slideToBottom(todayView)
                    animate(params.topMargin, 0)
                    scrollBl = true
                }
                return true
            }
        })

        todayView.setOnTouchListener { v, event ->
            detector.onTouchEvent(event)
        }

        var gestureDetector = GestureDetector(applicationContext, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return scrollBl
            }
        })

        todayRecycler.setOnTouchListener { v, event ->
            val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
            if (gestureDetector.onTouchEvent(event)){
                val childView = todayRecycler.findChildViewUnder(event.x,event.y)
                val currentPosition = todayRecycler.getChildAdapterPosition(childView!!)
                val currentItemStudent = todaylist[currentPosition]
                Toast.makeText(
                    this@MainActivity,
                    currentItemStudent.title,
                    Toast.LENGTH_SHORT
                ).show()
            }else if (event.action == MotionEvent.ACTION_DOWN) {
                y = event.y.toInt()
            }else if (event.action == MotionEvent.ACTION_UP) {
                if (y < event.y && !scrollBl) {
                    Log.e("Down", "d1")
                    slideToBottom(todayView)
                    animate(params.topMargin, 0)
                    scrollBl = true
                } else if (y > event.y && scrollBl) {
                    Log.e("Up", "u1")
                    slideToTop(todayView)
                    animate(params.topMargin, todayScrollbar.height)
                    scrollBl = false
                }
            }
            return@setOnTouchListener true
        }

        mainRecycler.layoutManager = LinearLayoutManager(this)
        todayRecycler.layoutManager = LinearLayoutManager(this)

        LastAdapter(alllist, BR.item)
            .map<PlanningData, ItemPlanningBinding>(R.layout.item_planning)
            {
                onBind {
                    it.binding.run {
                        item?.let { data ->
                            if (data.time!!.length >= 6) {
                                planTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.72F)
                            }
                        }
                    }
                }
                    .onClick {
                        Toast.makeText(applicationContext,it.binding.item!!.title,Toast.LENGTH_SHORT).show()
                    }
            }
            .into(mainRecycler)

        LastAdapter(todaylist, BR.item)
            .map<TodayData, ItemPlanningTodayBinding>(R.layout.item_planning_today) {

                onBind {
                    it.binding.run {
                        item?.let { data ->
                            if (data.time!!.length >= 6) {
                                todayTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.72F)
                            }
                        }
                    }
                }
            }
            .into(todayRecycler)

    }

    fun animate(start: Int, end: Int) {
        val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
        (1..10).forEach {
            Handler().postDelayed({
                params.topMargin = start - (start - end) * it / 10
                mainRecycler.requestLayout()
            }, it * 10L)
        }
    }

    fun slideToTop(view: View) {
        view.animate()
            .translationY((view.height.toFloat() * -1) + todayScrollbar.height)
            .withLayer()
    }

    fun slideToBottom(view: View) {
        view.visibility = View.VISIBLE
        view.animate()
            .translationY(0f)
            .withLayer()
    }

    fun planInfo() {
        planningInfo.start()
        planningInfo.join()
        alllist.addAll(planningInfo.infoList)

        todaylist.addAll(planningInfo.todayList)
    }
}
