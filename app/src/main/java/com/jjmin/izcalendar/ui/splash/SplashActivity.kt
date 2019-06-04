package com.jjmin.izcalendar.ui.splash

import android.os.Bundle
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivitySplashBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layoutResourceId: Int = R.layout.activity_splash
    val useCase by lazy { SplashUseCase(this) }
    val viewModel: SplashViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel
        viewModel.start()
    }
}
