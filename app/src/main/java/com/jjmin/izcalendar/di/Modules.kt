package com.jjmin.izcalendar.di

import com.jjmin.izcalendar.ui.detailplan.DetailUseCase
import com.jjmin.izcalendar.ui.detailplan.DetailViewModel
import com.jjmin.izcalendar.ui.main.MainUserCase
import com.jjmin.izcalendar.ui.main.MainViewModel
import com.jjmin.izcalendar.ui.setting.SettingUseCase
import com.jjmin.izcalendar.ui.setting.SettingViewModel
import com.jjmin.izcalendar.ui.splash.SplashUseCase
import com.jjmin.izcalendar.ui.splash.SplashViewModel
import com.jjmin.izcalendar.ui.theme.ThemeUseCase
import com.jjmin.izcalendar.ui.theme.ThemeViewModel
import com.jjmin.izcalendar.ui.tutorial.TutorialUseCase
import com.jjmin.izcalendar.ui.tutorial.TutorialViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules{

    val themeModules = module {
        viewModel { (usecase : ThemeUseCase) ->
            ThemeViewModel(usecase)
        }
    }

    val splashModules = module {
        viewModel { (usecase : SplashUseCase) ->
            SplashViewModel(usecase)
        }
    }

    val tutorialModules = module {
        viewModel { (usecase : TutorialUseCase) ->
            TutorialViewModel(usecase)
        }
    }

    val SettingModules = module {
        viewModel { (usecase : SettingUseCase) ->
            SettingViewModel(usecase)
        }
    }

    var mainModules = module {
        viewModel { (useCase: MainUserCase) ->
            MainViewModel(useCase,get())
        }
    }

    var detialModule = module {
        viewModel { (useCase: DetailUseCase) ->
            DetailViewModel(useCase,get())
        }
    }
}