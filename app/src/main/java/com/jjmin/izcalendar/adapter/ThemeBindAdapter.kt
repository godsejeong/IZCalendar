package com.jjmin.izcalendar.adapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.ThemeItem


object ThemeBindAdapter {
    @JvmStatic
    @BindingAdapter(value = ["setGridItem","OnClickItem"])
    fun SetGridAdpater(view: GridView, item: ArrayList<ThemeItem>,listener:  AdapterView.OnItemClickListener) {
        view.animation = null
        view.adapter?.run {
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            if (this is GridViewAdapter) {
                notifyDataSetChanged()
            }
        } ?: run {
            GridViewAdapter(item).apply {
                view.adapter = this
            }
        }

        view.onItemClickListener = listener

    }

    @JvmStatic
    @BindingAdapter(value = ["setStroke","setColor"])
    fun SetStroke(view : ImageView,bl : Boolean,color : Int){
            Log.e("checkbl", bl.toString())
        if(bl){
            val layerDrawable = view?.context?.resources?.getDrawable(R.drawable.theme_solid_background) as LayerDrawable
            val gradientDrawable = layerDrawable.findDrawableByLayerId(R.id.themeStroke) as GradientDrawable
            val px =
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f,  view?.context?.resources?.displayMetrics).toInt()

            gradientDrawable.setStroke(px,view?.context?.resources?.getColor(color)!!)
            view.setImageBitmap(drawableToBitmap(layerDrawable,view))
        }else{
            view.setImageBitmap(null)
        }
    }

    fun drawableToBitmap(drawable: Drawable, imageview: ImageView): Bitmap {

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(imageview.width, imageview.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

}