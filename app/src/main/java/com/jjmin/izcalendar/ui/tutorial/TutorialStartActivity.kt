package com.jjmin.izcalendar.ui.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityTutorialStartBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TutorialStartActivity : BaseActivity<ActivityTutorialStartBinding>() {
    override val layoutResourceId: Int = R.layout.activity_tutorial_start

    val useCase by lazy { TutorialUseCase(this, null) }
    val viewModel: TutorialViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding?.vm = viewModel
    }
}
