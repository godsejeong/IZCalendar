package com.jjmin.izcalendar.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

object TutorialBindAdapter{
    @JvmStatic
    @BindingAdapter(value = ["SetAdpater"])
    fun setTouchListener(view: ViewPager,fm : FragmentManager?) {
        view.adapter = ViewPagerAdapter(fm!!)
    }
}