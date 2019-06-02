package com.jjmin.izcalendar.ui.setting

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivitySettingBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    override val layoutResourceId: Int = R.layout.activity_setting

    val useCase by lazy { SettingUseCase(this) }
    val viewModel: SettingViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Activity.RESULT_OK) {
            if (resultCode == 100){
                toast("테마가 변경되었습니다.")
            }
        }
    }
}
