package com.jjmin.izcalendar.di


import com.jjmin.izcalendar.ui.detailplan.DetailUseCase
import com.jjmin.izcalendar.ui.detailplan.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailModules {
    var detialModule = module {
        viewModel { (useCase: DetailUseCase) ->
            DetailViewModel(useCase,get())
        }
    }
}