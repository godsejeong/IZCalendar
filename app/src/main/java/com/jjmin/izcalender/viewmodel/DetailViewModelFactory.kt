package com.jjmin.izcalender.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jjmin.izcalender.viewmodel.DetailViewModel
class DetailViewModelFactory(private val position : Int,val date : String,val title : ArrayList<String>,val subtitle : ArrayList<String>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(position,date,title,subtitle) as T
    }
}