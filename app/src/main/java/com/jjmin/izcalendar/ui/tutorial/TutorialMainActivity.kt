package com.jjmin.izcalendar.ui.tutorial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityTutorialMainBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import com.jjmin.izcalendar.ui.main.MainActivity
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TutorialMainActivity : BaseActivity<ActivityTutorialMainBinding>() {
    override val layoutResourceId: Int = R.layout.activity_tutorial_main

    val useCase by lazy { TutorialUseCase(this, null) }
    val viewModel: TutorialViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                toast("테마가 설정되었습니다.")
                SharedPreprecncesUtils.setTutorialCheck(true)
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
    }
}
