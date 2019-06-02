package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.ui.setting.SettingUseCase
import com.jjmin.izcalendar.ui.setting.SettingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SettingModules{
    val SettingModules = module {
        viewModel { (usecase : SettingUseCase) ->
            SettingViewModel(usecase)
        }
    }
}