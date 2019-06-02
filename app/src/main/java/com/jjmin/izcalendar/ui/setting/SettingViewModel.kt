package com.jjmin.izcalendar.ui.setting

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.ui.theme.ThemeChangeActivity

class SettingViewModel(private val useCase: SettingUseCase) : ViewModel() {

    var themeChangeClick = View.OnClickListener{
        var intent = Intent(useCase.activity.applicationContext, ThemeChangeActivity::class.java)
        useCase.activity.startActivityForResult(intent,100)
    }
}