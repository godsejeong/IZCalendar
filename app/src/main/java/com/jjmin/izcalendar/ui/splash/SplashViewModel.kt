package com.jjmin.izcalendar.ui.splash

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.ui.tutorial.TutorialMainActivity
import com.jjmin.izcalendar.ui.main.MainActivity
import com.jjmin.izcalendar.ui.tutorial.TutorialStartActivity
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils


class SplashViewModel(var usecase : SplashUseCase) : ViewModel(){
    var drawable = R.drawable.splash_loading

    fun start(){
        val handler = Handler()
        handler.postDelayed({
            var intent : Intent = if(SharedPreprecncesUtils.getTutorialCheck()){
                Intent(usecase.activity.application,MainActivity::class.java)
            }else{
                Intent(usecase.activity.application,TutorialStartActivity::class.java)
            }
            usecase.activity.startActivity(intent)
            usecase.activity.finish()
        },4000)
    }

}