package com.jjmin.izcalendar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jjmin.izcalendar.ui.tutorial.TutorialOneFragment
import com.jjmin.izcalendar.ui.tutorial.TutorialThreeFragment
import com.jjmin.izcalendar.ui.tutorial.TutorialTwoFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var fragment = Fragment()
    private val MAX_PAGE = 3
    override fun getItem(position: Int): Fragment {
        if (position < 0 || MAX_PAGE <= position)
            return null!!

        when (position) {
            0 -> {
                fragment = TutorialOneFragment.newInstance()
            }

            1 -> {
                fragment = TutorialTwoFragment.newInstance()
            }
            2 -> {
                fragment = TutorialThreeFragment.newInstance()
            }
        }

        return fragment
    }

    override fun getCount(): Int {
        return MAX_PAGE
    }
}