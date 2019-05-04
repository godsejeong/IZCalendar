package com.jjmin.izcalender.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjmin.izcalender.viewmodel.DetailViewModel
class DetailViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel() as T
    }
}