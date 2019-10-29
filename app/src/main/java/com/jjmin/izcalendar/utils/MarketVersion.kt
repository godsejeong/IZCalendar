package com.jjmin.izcalendar.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import java.io.IOException

class MarketVersion(internal var activity: Activity) : AsyncTask<Void, Void, String>() {
    lateinit var marketVersion: String
    lateinit var verSion: String

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void): String? {

        try {
            val doc = Jsoup
                .connect(
                    "https://play.google.com/store/apps/details?id=com.jjmin.izcalendar"
                )
                .get()
            val Version = doc.select("span.htlgb").eq(7)

            for (v in Version) {
                marketVersion = v.text()
                Log.e("text",v.text())
            }
            for (v in Version) {
                if (v.attr("spen") == "htlgb") {

                }
            }
            return marketVersion
        } catch (e: IOException) {
            e.printStackTrace()

            return null
        }

    }

    override fun onPostExecute(result: String) {
        var mDialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        var pi: PackageInfo? = null
        try {
            pi = activity.packageManager.getPackageInfo(activity.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        verSion = pi!!.versionName
        marketVersion = result

        if (verSion != marketVersion) {
            mDialog?.setMessage("최신버전이 업데이트 되었습니다.\n업데이트 후 이용해주세요.")
                .setCancelable(false)
                .setPositiveButton(
                    "업데이트 바로가기"
                ) { dialog, id ->
                    val marketLaunch = Intent(
                        Intent.ACTION_VIEW
                    )
                    marketLaunch.data = Uri
                        .parse("https://play.google.com/store/apps/details?id=com.jjmin.izcalendar")
                    activity.startActivity(marketLaunch)
                    activity.finish()
                }
            val alert = mDialog!!.create()
            alert.setTitle("최신버전 출시")
            alert.show()
        }

        super.onPostExecute(result)
    }
}
