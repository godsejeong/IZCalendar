package com.jjmin.izcalendar.ui.setting

import android.content.Intent
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.ui.theme.ThemeChangeActivity
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SetThemeToolbar
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import org.jetbrains.anko.toast
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity






class SettingViewModel(private val useCase: SettingUseCase) : ViewModel() {
    var _checked = MutableLiveData<Boolean>()
    val checked: LiveData<Boolean> get() = _checked

    init {
        _checked.value = SharedPreprecncesUtils.getDarkTheme()
    }

    var themeChangeClick = View.OnClickListener {
        var intent = Intent(useCase.activity.applicationContext, ThemeChangeActivity::class.java)
        useCase.activity.startActivityForResult(intent, 100)
    }

    var rockBackgroundChangeClick = View.OnClickListener {
        useCase.activity.applicationContext.toast("아직 지원하지 않는 기능입니다.")
    }

    var appinfoClick = View.OnClickListener {
        var intent = Intent(useCase.activity.applicationContext, AppInfoDialog::class.java)
        useCase.activity.startActivity(intent)
    }

    var shareClick = View.OnClickListener {
        var text = "빠른일정업데이트!! 자신만의 테마!! 태그기능으로 손쉬운 일정찾기!!\n아이즈원캘린더 IZCalendar앱을 이용해보세요!!\nhttps://play.google.com/store/apps/details?id=com.jjmin.izcalendar"
        var title = "IZCalendar"
        var intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, title)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        var chooser = Intent.createChooser(intent, "IZCalendar 공유하기")
        useCase.activity.startActivity(chooser)
    }

    fun rockBackgroundCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        useCase.activity.applicationContext.toast("아직 지원하지 않는 기능입니다.")
    }

    fun darkThemeCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            SharedPreprecncesUtils.setDarkTheme(true)
            SetTheme().update()

        } else {
            SharedPreprecncesUtils.setDarkTheme(false)
            var name = SharedPreprecncesUtils.getThemeName()
            var color = SharedPreprecncesUtils.getThemeColor()
            SharedPreprecncesUtils.setTheme(name, color, SetThemeToolbar.setBackgroundcolor(name))
            SetTheme().update()
        }


        val intent = useCase.activity.intent
        useCase.activity.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        useCase.activity.finish()
        useCase.activity.overridePendingTransition(0, 0);
        useCase.activity.startActivity(intent)
    }
}