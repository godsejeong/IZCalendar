package com.jjmin.izcalendar.ui.setting

import android.app.Activity
import android.os.Bundle
import android.view.Window
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.utils.MarketVersion
import kotlinx.android.synthetic.main.activity_app_info_dialog.*

class AppInfoDialog : Activity() {
    val marketVersion = MarketVersion(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_app_info_dialog)

        val pi = this.packageManager.getPackageInfo(this.packageName, 0)

        marketVersion.execute().get()
        dialogVersion.text = "버전정보 : ${marketVersion.marketVersion}"
        dialogNowVersion.text = "현재버전 : ${pi!!.versionName}"
    }
}
