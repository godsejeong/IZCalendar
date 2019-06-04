package com.jjmin.izcalendar.ui.setting

import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.ui.theme.ThemeChangeActivity
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SetThemeToolbar
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import org.jetbrains.anko.toast


class SettingViewModel(private val useCase: SettingUseCase) : ViewModel() {
    var _checked = MutableLiveData<Boolean>()
    val checked : LiveData<Boolean> get() = _checked
    var version = "버전정보 : 1.0.0"
    var nowVersion = "현재버전 : 1.0.0"

    init {
        _checked.value = SharedPreprecncesUtils.getDarkTheme()
    }

    var themeChangeClick = View.OnClickListener{
        var intent = Intent(useCase.activity.applicationContext, ThemeChangeActivity::class.java)
        useCase.activity.startActivityForResult(intent,100)
    }

    var rockBackgroundChangeClick = View.OnClickListener{
        useCase.activity.applicationContext.toast("아직 지원하지 않는 기능입니다.")
    }

    var appinfoClick = View.OnClickListener {
        var intent = Intent(useCase.activity.applicationContext,AppInfoDialog::class.java)
        useCase.activity.startActivity(intent)
    }

    fun RockBackgroundCheckedChanged(buttonView : CompoundButton,isChecked : Boolean) {
        useCase.activity.applicationContext.toast("아직 지원하지 않는 기능입니다.")
    }

    fun DarkThemeCheckedChanged(buttonView : CompoundButton,isChecked : Boolean) {

//        Thread(Runnable {
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
            useCase.activity.finish()
            useCase.activity.startActivity(intent)
            useCase.activity.overridePendingTransition(0, 0)

    }
}