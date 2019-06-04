package com.jjmin.izcalendar.ui.setting

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivitySettingBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    override val layoutResourceId: Int = R.layout.activity_setting

    val theme by lazy {SetTheme()}
    val useCase by lazy { SettingUseCase(this) }
    val viewModel: SettingViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(viewDataBinding.settingToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewDataBinding.vm = viewModel

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode== 100){
                var name = data?.getStringExtra("setThemeName")
                var color = data?.getIntExtra("setThemeColor",0)
                var backgroundcolor = data?.getIntExtra("setThemeBackgroundColor",0)
                SharedPreprecncesUtils.setTheme(name!!,color!!,backgroundcolor!!)
                SetTheme().update()
                toast("테마가 변경되었습니다.")
                this.recreate()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
