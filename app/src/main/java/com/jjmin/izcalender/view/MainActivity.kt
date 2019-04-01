package com.jjmin.izcalender.view

import android.arch.lifecycle.Transformations.map
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.github.nitrico.lastadapter.*
import com.github.nitrico.lastadapter.BR.item
import com.jjmin.izcalender.BR
import com.jjmin.izcalender.data.PlanningData
import com.jjmin.izcalender.model.PlanningModel
import com.jjmin.izcalender.util.Utils
import kotlinx.android.synthetic.main.activity_main.*
import com.jjmin.izcalender.R
import com.jjmin.izcalender.databinding.ItemPlanningBinding
import com.jjmin.izcalender.databinding.ItemPlanningDayBinding
import kotlinx.android.synthetic.main.item_planning.view.*
import org.jetbrains.annotations.NotNull


class MainActivity : AppCompatActivity() {
    var list = ArrayList<Any>()
    var planningInfo = PlanningModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Oncreate", "T")
        planInfo()


        mainRecycler.layoutManager = LinearLayoutManager(this)

        LastAdapter(list,BR.item)
            .map<PlanningData,ItemPlanningBinding>(R.layout.item_planning)
            {
                onBind {
                    it.binding.run {
                        item?.let { data ->
                            if(data.time!!.length >= 6){
                                planTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13.72F)
                            }
                        }
                    }
                }
                onClick {
                    Toast.makeText(applicationContext,"Click",Toast.LENGTH_SHORT).show()
                }
            }
            .into(mainRecycler)
    }

    fun planInfo() {
        planningInfo.start()
        planningInfo.join()
        list.addAll(planningInfo.infoList)
//        list.add(PlanningData("title","subTitle","Always","day","dow"))
//        list.add(PlanningData("title","subTitle","03:30","day","dow"))


    }
}
