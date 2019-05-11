package com.jjmin.izcalender.viewmodel

import com.jjmin.izcalender.R
import com.jjmin.izcalender.data.DetailPlanItem
import com.jjmin.izcalender.data.TagSpinnerItem
import android.widget.AdapterView
import android.view.View
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.jjmin.izcalender.model.DetailModel
import com.jjmin.izcalender.utils.SharedPreprecnceUtils

class DetailViewModel(val position: Int, val date: String, val title: ArrayList<String>, val subtitle: ArrayList<String>) : ViewModel() {

    val model = DetailModel(date)
    val _detailItems = MutableLiveData<ArrayList<DetailPlanItem>>()
    val detailitems: LiveData<ArrayList<DetailPlanItem>> get() = _detailItems

    var startbl = false

    val _spinnerItems = MutableLiveData<ArrayList<TagSpinnerItem>>()
    val spinnerItems: LiveData<ArrayList<TagSpinnerItem>> get() = _spinnerItems

    init {
        model.start()
        model.join()

        _detailItems.value =
            ArrayList<DetailPlanItem>().apply {
                (0 until model.date.size).forEach {
                    add(
                        DetailPlanItem(
                            title[it],
                            subtitle[it],
                            model.date[it],
                            model.time[it],
                            if (SharedPreprecnceUtils.getColorTag(position) != 0) SharedPreprecnceUtils.getColorTag(
                                position
                            ) else R.color.colorMyColor
                        )
                    )
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

    fun updateData(): ArrayList<DetailPlanItem> {
        var item = ArrayList<DetailPlanItem>()
        item.apply {
            (0 until model.date.size).forEach {
                add(
                    DetailPlanItem(
                        title[it],
                        subtitle[it],
                        model.date[it],
                        model.time[it],
                        if (SharedPreprecnceUtils.getColorTag(position) != 0) SharedPreprecnceUtils.getColorTag(
                            position
                        ) else R.color.colorMyColor
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
        Log.e("list","update")
    }
}