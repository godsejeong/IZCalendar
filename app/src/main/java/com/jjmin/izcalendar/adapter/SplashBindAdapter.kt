package com.jjmin.izcalendar.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter



object SplashBindAdapter{

    @JvmStatic
    @BindingAdapter(value = ["setImg"])
    fun loadImage(view: ImageView, drawable : Int) {
        Glide.with(view.context).load(drawable).into(view)
    }
}