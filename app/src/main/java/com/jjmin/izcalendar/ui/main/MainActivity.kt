package com.jjmin.izcalendar.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.gms.ads.MobileAds
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityMainBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import com.jjmin.izcalendar.ui.setting.SettingActivity
import com.jjmin.izcalendar.utils.SetTheme
import org.koin.core.parameter.parametersOf
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main

    val useCase by lazy { MainUserCase(this) }
    val viewModel: MainViewModel by viewModel { parametersOf(useCase) }
    val theme by lazy {SetTheme()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)

        viewDataBinding.vm = viewModel
        viewDataBinding.theme = theme

        setSupportActionBar(viewDataBinding.mainToolbar)
        viewDataBinding.calendarLayout.bringToFront()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewDataBinding.CustomCalendar.changeList()
        viewModel.Plan()
        this.recreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menuSetting -> {
                var intent = Intent(this,SettingActivity::class.java)
                startActivityForResult(intent,1000)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
