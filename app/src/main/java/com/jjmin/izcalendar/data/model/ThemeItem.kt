package com.jjmin.izcalendar.data.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView

data class ThemeItem(var img : Drawable,var name : String,var color : Int,var backgroundcolor : Int){
    var bl = false
}