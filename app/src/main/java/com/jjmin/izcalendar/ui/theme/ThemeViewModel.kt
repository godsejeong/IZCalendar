package com.jjmin.izcalendar.ui.theme

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.R
import android.view.View.OnClickListener
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jjmin.izcalendar.data.model.ThemeItem


class ThemeViewModel(private val usecase: ThemeUseCase) : ViewModel(){
    var btnbl = ObservableField<Boolean>()

    var baselist = ArrayList<ThemeItem>()

    val _themeItems = MutableLiveData<ArrayList<ThemeItem>>()
    val themeItems: LiveData<ArrayList<ThemeItem>> get() = _themeItems

    var finishOnclick = OnClickListener {

        var intent = Intent()
        var position : Int = 0
        (0 until baselist.size).forEach {
            if(baselist[it].bl){
                position = it
                return@forEach
            }
        }
        intent.putExtra("setThemeName",baselist[position].name)
        intent.putExtra("setThemeColor",baselist[position].color)
        intent.putExtra("setThemeBackgroundColor",baselist[position].backgroundcolor)
        usecase.activity.setResult(RESULT_OK,intent)
        usecase.activity.finish()
    }

    init {
        btnbl.set(false)
            baselist.apply {
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_oneyoung),
                    "원영",
                    R.color.colorOneYoung,
                    R.color.colorOneYoungBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_sakura),
                    "사쿠라",
                    R.color.colorSakura,
                    R.color.colorSakuraBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_yuri),
                    "유리",
                    R.color.colorYuRi,
                    R.color.colorYuRiBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_yena),
                    "예나",
                    R.color.colorYeNa,
                    R.color.colorYeNaBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_yujin),
                    "유진",
                    R.color.colorYuJin,
                    R.color.colorYuJinBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_naco),
                    "나코",
                    R.color.colorNaco,
                    R.color.colorNacoBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_eunbi),
                    "은비",
                    R.color.colorEunBi,
                    R.color.colorEunBiBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_hyeone),
                    "혜원",
                    R.color.colorHyeWon,
                    R.color.colorHyeWonBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_hitomi),
                    "히토미",
                    R.color.colorHitomi,
                    R.color.colorHitomiBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_chaewon),
                    "채원",
                    R.color.colorChaeWon,
                    R.color.colorChaeWonBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_minju),
                    "민주",
                    R.color.colorMinJu,
                    R.color.colorMinJuBg
                )
            )
            add(
                ThemeItem(
                    usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_chaeyeon),
                    "채연",
                    R.color.colorChaeYeon,
                    R.color.colorChaeYeonBg
                )
            )
        }
        _themeItems.value = baselist
    }

    fun updatedata(position : Int){
        (0 until baselist.size).forEach {
            baselist[it].bl = false
        }
        baselist[position].bl = true
        _themeItems.value = baselist
    }

   fun OnItemClick(parent: AdapterView<*>?, view: View?, position: Int?, id: Long?){
       Log.e("Click", position.toString())
       updatedata(position!!)
       btnbl.set(true)
   }
}