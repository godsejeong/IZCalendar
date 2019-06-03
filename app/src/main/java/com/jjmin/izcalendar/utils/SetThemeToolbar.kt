package com.jjmin.izcalendar.utils

import com.jjmin.izcalendar.R

object SetThemeToolbar {

    fun settheme(name: String): Int {
        return when (name) {
            "원영" -> {
                R.style.OneYoungTheme
            }
            "사쿠라" -> {
                R.style.SakuraTheme
            }
            "유리" -> {
                R.style.YuRiTheme
            }
            "예나" -> {
                R.style.YeNaTheme
            }
            "유진" -> {
                R.style.YuJinTheme
            }
            "나코" -> {
                R.style.NacoTheme
            }
            "은비" -> {
                R.style.EunbiTheme
            }
            "혜원" -> {
                R.style.HyeWonTheme
            }
            "히토미" -> {
                R.style.HitomiTheme
            }
            "채원" -> {
                R.style.ChaeWonTheme
            }
            "민주" -> {
                R.style.MinJuTheme
            }
            "채연" -> {
                R.style.ChaeYeonTheme
            }
            else -> {
                R.style.OneYoungTheme
            }
        }
    }

}