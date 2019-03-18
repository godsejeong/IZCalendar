package com.jjmin.izcalender

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.widget.Toast
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var list = ArrayList<Any>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecycler.layoutManager = LinearLayoutManager(this)

        val into = LastAdapter(list,BR.item)
            .map<PlanningData, com.jjmin.izcalender.databinding.ItemPlanningBinding>(R.layout.item_planning)
            {
                onClick {
                    Toast.makeText(applicationContext,"Click",Toast.LENGTH_SHORT).show()
                }
            }
            .into(mainRecycler)
    }
}
