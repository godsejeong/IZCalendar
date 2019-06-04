package com.jjmin.izcalendar.ui.tutorial

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.ui.theme.ThemeChangeActivity

class TutorialViewModel(val usecase: TutorialUseCase) : ViewModel() {
    var fragmentManager = usecase.activity?.supportFragmentManager

    var themeSelect = View.OnClickListener {
        var intnet = Intent(usecase.fagment?.activity, ThemeChangeActivity::class.java)
        usecase.fagment?.activity?.startActivityForResult(intnet, 100)
    }

    var StartBtn = View.OnClickListener {
        var intnet = Intent(usecase.activity,TutorialMainActivity::class.java)
        usecase.activity?.startActivity(intnet)
    }
}