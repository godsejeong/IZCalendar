package com.jjmin.izcalendar.adapter

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.jjmin.izcalendar.BuildConfig
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.ListDataInterface



object BindAdapter {
    @JvmStatic
    @BindingAdapter(value = ["listItem", "viewModel"])
    fun ListAdapter(view: RecyclerView, items: List<ListDataInterface>, vm: ViewModel) {
        view.adapter?.run {
            if (this is ItemListAdapter) {
                this.submitList(items)
            }
        } ?: run {
            ItemListAdapter(vm,null).apply {
                view.adapter = this
                this.submitList(items)
            }
        }
    }

    private infix fun String.or(that: String): String = if (BuildConfig.DEBUG) this else that


    @JvmStatic
    @BindingAdapter(value = ["ConnectAdView"])
    fun LoadAdView(view : ConstraintLayout, activity: Activity){

        MobileAds.initialize(activity,activity.getString(R.string.banner_ad_app_id))
        val extras = Bundle()
        extras.putString("max_ad_content_rating", "G")
        val adRequest = AdRequest.Builder()
            .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
            .build()


        AdView(activity).apply {
            adSize = AdSize.SMART_BANNER
            adUnitId = context.getString(R.string.banner_ad_unit_id)
            view.addView(this)
            loadAd(adRequest)
        }

//        AdView(activity).apply {
//            adSize = AdSize.SMART_BANNER
//            adUnitId = context.getString(R.string.banner_ad_unit_id_test) or context.getString(R.string.banner_ad_unit_id)
//            view.addView(this)
//            loadAd(adRequest)
//        }
    }
}

