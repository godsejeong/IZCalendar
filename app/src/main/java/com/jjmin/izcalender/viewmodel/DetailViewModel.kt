package com.jjmin.izcalender.viewmodel

import com.jjmin.izcalender.R
import com.jjmin.izcalender.data.DetailPlanItem
import com.jjmin.izcalender.data.TagSpinnerItem
import android.widget.AdapterView
import android.view.View
import android.widget.Toast
import android.R.attr.category
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import android.widget.Spinner
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.jjmin.izcalender.utils.SharedPreprecnceUtils
import com.jjmin.izcalender.view.DetailPlanActivity


class DetailViewModel(val position: Int, val owner: LifecycleOwner) : ViewModel() {

    val _detailItems = MutableLiveData<ArrayList<DetailPlanItem>>()
    val detailitems: LiveData<ArrayList<DetailPlanItem>> get() = _detailItems

    val _spinnerItems = MutableLiveData<ArrayList<TagSpinnerItem>>()
    val spinnerItems: LiveData<ArrayList<TagSpinnerItem>> get() = _spinnerItems
    var detailArrayList: ArrayList<DetailPlanItem> = ArrayList<DetailPlanItem>().apply {
        (1..3).forEach {
            add(
                DetailPlanItem(
                    "title$it",
                    "subtitle$it",
                    "date$it",
                    "time$it",
                    if (SharedPreprecnceUtils.getColorTag(position) != 0) SharedPreprecnceUtils.getColorTag(
                        position
                    ) else R.color.colorMyColor
                )
            )
        }
    }

    init {
        _detailItems.value = detailArrayList

        _spinnerItems.value = ArrayList<TagSpinnerItem>().apply {
            add(TagSpinnerItem(R.color.colorTagDisable, "태그없음"))
            add(TagSpinnerItem(R.color.colorTagRed, "Red"))
            add(TagSpinnerItem(R.color.colorTagYellow, "Yellow"))
            add(TagSpinnerItem(R.color.colorTagGreen, "Green"))
            add(TagSpinnerItem(R.color.colorTagBlue, "Blue"))
        }
    }

    fun updateData(): ArrayList<DetailPlanItem> {
        var item = ArrayList<DetailPlanItem>()
        item.apply {
            (1..3).forEach {
                add(
                    DetailPlanItem(
                        "title$it",
                        "subtitle$it",
                        "date$it",
                        "time$it",
                        if (SharedPreprecnceUtils.getColorTag(position) != 0) SharedPreprecnceUtils.getColorTag(position) else R.color.colorMyColor
                    )
                )
            }
        }

        return item
    }

    fun getposion(): Int {
        return SharedPreprecnceUtils.getSpinnerPosition(position)
    }

    fun onLanguageSpinnerItemSelected(parent: AdapterView<*>?, view: View?, position: Int?, id: Long?) {
        Log.e("bind", position.toString())
        var item = parent!!.getItemAtPosition(position!!) as TagSpinnerItem
        Log.e("bindColor", item.color.toString())
        if (position == 0)
            SharedPreprecnceUtils.setTag(this.position, R.color.colorMyColor)
        else
            SharedPreprecnceUtils.setTag(this.position, item.color!!)
        SharedPreprecnceUtils.setSpinnerPostion(this.position, position)

        _detailItems.value = updateData()
        Log.e("test","testvalue")
    }
}