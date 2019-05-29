package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.ui.main.MainUserCase
import com.jjmin.izcalendar.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModules{
    var mainModules = module {
        viewModel { (useCase: MainUserCase) ->
            MainViewModel(useCase,get())
        }
    }
}