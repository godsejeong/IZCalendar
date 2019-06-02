package com.jjmin.izcalendar.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
//import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.CalendarView
import android.widget.Toast
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityMainBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import com.jjmin.izcalendar.ui.setting.SettingActivity
import org.koin.core.parameter.parametersOf
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main

    val useCase by lazy { MainUserCase(this) }
    val viewModel: MainViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        setSupportActionBar(viewDataBinding.mainToolbar)
        viewDataBinding.calendarLayout.bringToFront()

        R.color.colorMain
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("asdfasdf","ASDFasdfasdf")
        viewDataBinding.CustomCalendar.changeList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menuSetting -> {
                var intent = Intent(this,SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
