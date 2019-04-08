package com.jjmin.izcalender.view

import android.databinding.DataBindingUtil
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
import android.view.GestureDetector
import android.widget.AdapterView
import com.jjmin.izcalender.databinding.ActivityMainBinding
import com.jjmin.izcalender.utils.SnappingLayoutManager
import com.jjmin.izcalender.utils.Utils
import com.jjmin.izcalender.viewmodel.CalendarViewModel
import com.jjmin.izcalender.viewmodel.PlanningViewModel
import kotlinx.android.synthetic.main.calendar_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance


class MainActivity : AppCompatActivity() {
    var y = 0
    var planModel = PlanningViewModel()
    var scrollBl = true
    var isStartScroll = true
    var isclick = true
    var modle = CalendarViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        calendarLayout.bringToFront()
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.viewmodel = modle
        modle.onCreate()
        planModel.onCreate()
        CustomCalendar.setPlan(planModel.clandardayList)
        var gestureDetector = GestureDetector(applicationContext, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return scrollBl
            }
        })

        todayView.setOnTouchListener { v, event ->
            val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
            if (event.action == MotionEvent.ACTION_DOWN) {
                y = event.y.toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
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

        todayRecycler.setOnTouchListener { v, event ->
            val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
            if (gestureDetector.onTouchEvent(event)) {
                val childView = todayRecycler.findChildViewUnder(event.x, event.y)
                val currentPosition = todayRecycler.getChildAdapterPosition(childView!!)
                val currentItemStudent = planModel.todaylist[currentPosition]
                Toast.makeText(
                    this@MainActivity,
                    currentItemStudent.title,
                    Toast.LENGTH_SHORT
                ).show()
            } else if (event.action == MotionEvent.ACTION_DOWN) {
                y = event.y.toInt()
            } else if (event.action == MotionEvent.ACTION_UP) {
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

        mainRecycler.layoutManager =
            SnappingLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        todayRecycler.layoutManager = LinearLayoutManager(this)

        LastAdapter(planModel.alllist, BR.item)
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
                        Toast.makeText(applicationContext, it.binding.item!!.title, Toast.LENGTH_SHORT).show()
                    }
            }
            .into(mainRecycler)

        LastAdapter(planModel.todaylist, BR.item)
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

        mainRecycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                isStartScroll = true
                val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
                slideToTop(todayView)
                animate(params.topMargin, todayScrollbar.height)
                scrollBl = false
            }
        })

        CustomCalendar.calendarGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                var datePosition = CustomCalendar.list[position].day
                try {
                    if (datePosition.toInt() < 10)
                        datePosition = "0$datePosition"
                    datePosition = "${CustomCalendar.strmon}/$datePosition"
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                    isclick = false
                }
                Log.e("list", datePosition)
                val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams

                if (isclick) {
                    (0 until planModel.alllist.size).forEach {
                        var planlist = planModel.alllist[it]
                        Log.e("all", planlist.day)
                        if (datePosition == planlist.day) {
                            mainRecycler.smoothScrollToPosition(it)
                            return@forEach
                        } else if (datePosition ==  Utils.today()) {
                            slideToBottom(todayView)
                            animate(params.topMargin, 0)
                            scrollBl = true
                        }
                    }
                }
            }
    }

    fun animate(start: Int, end: Int) {
        val params = mainRecycler.layoutParams as ConstraintLayout.LayoutParams
        (1..10).forEach {
            Handler().postDelayed({
                params.topMargin = start - (start - end) * it / 10
                mainRecycler.requestLayout()
            },it * 10L)
        }
    }

    fun slideToTop(view: View) {
        view.animate()
            .translationY((view.height.toFloat() * -1) + todayScrollbar.height)
            .withLayer()
    }

    fun slideToBottom(view: View) {
        if (isStartScroll) {
            mainRecycler.stopScroll()
        }
        view.visibility = View.VISIBLE
        view.animate()
            .translationY(0f)
            .withLayer()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.menuSetting -> {
                Toast.makeText(applicationContext, "Setting", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
