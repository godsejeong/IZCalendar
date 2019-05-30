package com.jjmin.izcalendar.ui.main

import android.os.Bundle
//import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.jjmin.izcalendar.R
import android.os.Handler
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import androidx.constraintlayout.widget.ConstraintLayout
import com.jjmin.izcalendar.ui.calendar.CalendarUtils
import com.jjmin.izcalendar.utils.Utils
import com.jjmin.izcalendar.databinding.ActivityMainBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import kotlinx.android.synthetic.main.calendar_view.view.*
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
