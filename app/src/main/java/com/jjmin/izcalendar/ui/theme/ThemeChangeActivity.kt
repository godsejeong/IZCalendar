package com.jjmin.izcalendar.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityThemeChangeBinding
import com.jjmin.izcalendar.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ThemeChangeActivity : BaseActivity<ActivityThemeChangeBinding>() {
    override val layoutResourceId: Int = R.layout.activity_theme_change

    val useCase by lazy { ThemeUseCase(this) }
    val viewModel: ThemeViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.vm = viewModel

    }
}
