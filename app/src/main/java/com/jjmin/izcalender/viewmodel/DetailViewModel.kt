package com.jjmin.izcalender.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjmin.izcalender.R
import com.jjmin.izcalender.data.DetailPlanItem
import com.jjmin.izcalender.data.TagSpinnerItem
import android.widget.AdapterView
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import android.R.attr.category
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import android.widget.Spinner
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.ObservableField









class DetailViewModel : ViewModel(){
    var nowColor = ObservableField<Int>()
    var nowText  = ObservableField<String>()
    val _detailItems = MutableLiveData<ArrayList<DetailPlanItem>>()
    val detailitems: LiveData<ArrayList<DetailPlanItem>> get() = _detailItems


    val _spinnerItems =  MutableLiveData<ArrayList<TagSpinnerItem>>()
    val spinnerItems : LiveData<ArrayList<TagSpinnerItem>> get() = _spinnerItems
    init {
        _detailItems.value = ArrayList<DetailPlanItem>().apply {
            (1..3).forEach {
                add(DetailPlanItem("title$it", "subtitle$it", "date$it", "time$it"))
            }
        }

        _spinnerItems.value = ArrayList<TagSpinnerItem>().apply {
            add(TagSpinnerItem(R.color.colorTagDisable, "태그없음"))
            add(TagSpinnerItem(R.color.colorTagRed, "Red"))
            add(TagSpinnerItem(R.color.colorTagYellow, "Yellow"))
            add(TagSpinnerItem(R.color.colorTagGreen, "Green"))
            add(TagSpinnerItem(R.color.colorTagBlue, "Blue"))
        }
    }

    fun onLanguageSpinnerItemSelected(parent: AdapterView<*>?, view: View?, position: Int?, id: Long?) {
        //  스피너의 선택 내용이 바뀌면 호출된다
        Log.e("bind", position.toString())
        var item = parent!!.getItemAtPosition(position!!) as TagSpinnerItem
//        nowText.set(item.colorname)
//        nowColor.set(item.color)
    }
}