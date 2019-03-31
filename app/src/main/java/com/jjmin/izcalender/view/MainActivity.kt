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

        var dayType = Type<ItemPlanningDayBinding>(R.layout.item_planning_day)
        var planType = Type<ItemPlanningBinding>(R.layout.item_planning)
            .onClick {
                Toast.makeText(applicationContext,"Click",Toast.LENGTH_SHORT).show()
            }

        var  inflate = LayoutInflater.from(this)
        var  view = inflate.inflate( R.layout.item_planning,null)

        LastAdapter(list,BR.item)
            .layout{item, position ->
                var  inflate = LayoutInflater.from(this)
                var  view = inflate.inflate( R.layout.item_planning,null)

            }
            .handler(object : TypeHandler {
                override fun getItemType(item: Any, position: Int): BaseType? {
                    return if (item is PlanningData)
                        if (item.time!!.length >= 6) {
                            view.planTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP,10F)
                            Log.e("size", view.planTime.textSize.toString())
                            planType
                        }
                        else
                            planType
                    else
                        null
                }
            })
            .into(mainRecycler)
    }

    fun planInfo() {
        planningInfo.start()
        planningInfo.join()
        list.addAll(planningInfo.infoList)
    }
}
