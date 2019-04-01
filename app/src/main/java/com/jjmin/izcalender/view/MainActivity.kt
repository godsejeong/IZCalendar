package com.jjmin.izcalender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import com.github.nitrico.lastadapter.*
import com.jjmin.izcalender.BR
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.model.PlanningModel
import kotlinx.android.synthetic.main.activity_main.*
import com.jjmin.izcalender.databinding.ItemPlanningBinding
import android.view.View.MeasureSpec
import android.util.DisplayMetrics
import com.jjmin.izcalender.R
import android.graphics.Rect


class MainActivity : AppCompatActivity() {
    var list = ArrayList<Any>()
    var planningInfo = PlanningModel()
    var MAXHEIGHT = 90
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Oncreate", "T")
        planInfo()
        todayView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)



//        var detector = GestureDetector(this, object : GestureDetector.OnGestureListener {
//            override fun onShowPress(e: MotionEvent?) {
//            }
//
//            override fun onSingleTapUp(e: MotionEvent?): Boolean {
//                return true
//            }
//
//            override fun onDown(e: MotionEvent?): Boolean {
//                return true
//            }
//
//            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
//
//                return true
//            }
//
//            override fun onLongPress(e: MotionEvent?) {
//            }
//
//
//            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
//                Log.e("onScroll 호출됨", "${pxToDp(todayView.height)},${todayView.height - (distanceY)}")
//
//
//                val params = todayView.layoutParams
////                when {
////                    pxToDp(todayView.height) in 12..MAXHEIGHT -> {
////                        Log.e("todayView", pxToDp(todayView.height).toString())
////                        params.height = (todayView.height - (distanceY).toInt())
////                    }
////
////                    pxToDp(todayView.height) == 11 -> {
////                        params.height = (todayView.height - (distanceY).toInt())
////                    }
////                }
//
//                todayView.layoutParams = params
//                return true
//            }
//        })
//
//        todayScrollbar.setOnTouchListener { v, event ->
//            detector.onTouchEvent(event)
//        }

        mainRecycler.layoutManager = LinearLayoutManager(this)

        LastAdapter(list, BR.item)
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
                onClick {
                    Toast.makeText(applicationContext, "Click", Toast.LENGTH_SHORT).show()
                }
            }
            .into(mainRecycler)
    }

    fun pxToDp(px: Int): Int {
        val displayMetrics = application.resources.displayMetrics
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun planInfo() {
        planningInfo.start()
        planningInfo.join()
        list.addAll(planningInfo.infoList)
//        list.add(PlanningData("title","subTitle","Always","day","dow"))
//        list.add(PlanningData("title","subTitle","03:30","day","dow"))


    }
}
