package com.jjmin.izcalendar.ui.theme

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.drawToBitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.R
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.TypedValue
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jjmin.izcalendar.data.ThemeItem
import com.jjmin.izcalendar.databinding.ActivityThemeChangeBinding
import com.jjmin.izcalendar.utils.GridViewItemClickListener
import android.widget.Toast
import androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked
import com.jjmin.izcalendar.adapter.ThemeBindAdapter.drawableToBitmap
import com.jjmin.izcalendar.data.TagSpinnerItem
import org.jetbrains.anko.toast


class ThemeViewModel(private val usecase: ThemeUseCase) : ViewModel(){


    var btnbl = ObservableField<Boolean>()

    var OneyoungDrawable = usecase.activity.applicationContext.resources.getDrawable(R.drawable.theme_solid_background)
    var bl = true

    var baselist = ArrayList<ThemeItem>()

    val _themeItems = MutableLiveData<ArrayList<ThemeItem>>()
    val themeItems: LiveData<ArrayList<ThemeItem>> get() = _themeItems


    init {
        btnbl.set(false)
            baselist.apply {
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_youngone),"원영",R.color.colorOneYoung))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_sakura),"사쿠라",R.color.colorSakura))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_yuri),"유리",R.color.colorYuRi))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_yena),"예나",R.color.colorYeNa))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_yujin),"유진",R.color.colorYuJin))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_nako),"나코",R.color.colorNaco))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_eunbi),"은비",R.color.colorEunBi))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_hyewon),"혜원",R.color.colorHyeWon))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_hitomi),"히토미",R.color.colorHitomi))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_chaeone),"채원",R.color.colorChaeWon))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_minju),"민주",R.color.colorMinJu))
            add(ThemeItem(usecase.activity.applicationContext.resources.getDrawable(R.drawable.bg_chaeyeon),"채연",R.color.colorChaeYeon))
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