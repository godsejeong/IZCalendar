package com.jjmin.izcalendar.adapter

import android.app.Activity
import android.provider.Settings.Global.getString
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.ListDataInterface
import com.google.ads.mediation.admob.AdMobAdapter
import android.os.Bundle





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

    @JvmStatic
    @BindingAdapter(value = ["ConnectAdView"])
    fun LoadAdView(view : AdView,activity: Activity){
            MobileAds.initialize(activity,activity.getString(R.string.banner_ad_app_id))
        val extras = Bundle()
        extras.putString("max_ad_content_rating", "G")
        val adRequest = AdRequest.Builder()
            .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
            .build()
            view.loadAd(adRequest)
    }
}