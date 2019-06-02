package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.ui.theme.ThemeUseCase
import com.jjmin.izcalendar.ui.theme.ThemeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ThemeModules{
    val themeModules = module {
        viewModel { (usecase : ThemeUseCase) ->
            ThemeViewModel(usecase)
        }
    }
}