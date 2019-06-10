package com.jjmin.izcalendar.utils

import android.os.Handler
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

object AnimationUtils{

    var clickbl : Boolean = true

    fun animate(view: View, start: Int, end: Int) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        (1..10).forEach {
            Handler().postDelayed({
                params.topMargin = start - (start - end) * it / 10
                view.requestLayout()
            }, it * 10L)
        }
    }

    fun slideToTop(view: View,scrollbar : View) {
        view.animate()
            .translationY((view.height.toFloat() * -1) + scrollbar.height)
            .withLayer()
    }

    fun slideToBottom(view: View) {
        view.visibility = View.VISIBLE
        view.animate()
            .translationY(0f)
            .withLayer()
    }
}