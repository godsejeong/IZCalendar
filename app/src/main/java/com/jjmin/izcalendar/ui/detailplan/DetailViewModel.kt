package com.jjmin.izcalendar.ui.detailplan

import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.data.DetailPlanItem
import com.jjmin.izcalendar.data.TagSpinnerItem
import android.widget.AdapterView
import android.view.View
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.jjmin.izcalendar.ui.base.DisposableViewModel
import com.jjmin.izcalendar.ui.calendar.CalendarView
import com.jjmin.izcalendar.utils.SetTheme
import com.jjmin.izcalendar.utils.SharedPreprecncesUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(var useCase : DetailUseCase,var detailPlanRepository: DetailPlanRepository) : DisposableViewModel() {

    val _detailItems = MutableLiveData<ArrayList<DetailPlanItem>>(arrayListOf())
    val detailitems: LiveData<ArrayList<DetailPlanItem>> get() = _detailItems

    val _spinnerItems = MutableLiveData<ArrayList<TagSpinnerItem>>()
    val spinnerItems: LiveData<ArrayList<TagSpinnerItem>> get() = _spinnerItems

    init {
        Detailplan()
        _spinnerItems.value = ArrayList<TagSpinnerItem>().apply {
            add(TagSpinnerItem(SetTheme().themecolor.value!!, "태그없음"))
            add(TagSpinnerItem(R.color.colorTagRed, "Red"))
            add(TagSpinnerItem(R.color.colorTagYellow, "Yellow"))
            add(TagSpinnerItem(R.color.colorTagGreen, "Green"))
            add(TagSpinnerItem(R.color.colorTagBlue, "Blue"))
        }
    }

    fun Detailplan(){
        detailPlanRepository.DetailLink(useCase.date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _detailItems.value = updateData(it.detailPlan)
            }){
                Log.e("Detialerror",it.message)
            }
            .also { addDisposable(it) }
    }

    fun updateData(detailPlan : List<String>): ArrayList<DetailPlanItem> {
        var date = ArrayList<String>()
        var time = ArrayList<String>()
        var item = ArrayList<DetailPlanItem>()

        (0 until detailPlan.size).forEach {
            val array = detailPlan[it].split("\n")
            date.add(array[0])
            time.add(array[1])
            Log.e(array[0],array[1])
        }

        item.apply {
            (0 until detailPlan.size).forEach {
                add(
                    DetailPlanItem(
                        useCase.title[it],
                        useCase.subtitle[it],
                        date[it],
                        time[it],
                        if (SharedPreprecncesUtils.getColorTag(useCase.position) != 0) SharedPreprecncesUtils.getColorTag(
                            useCase.position
                        ) else SetTheme().themecolor.value!!
                    )
                )
            }
        }
        return item
    }

    fun getposion(): Int {
        return SharedPreprecncesUtils.getSpinnerPosition(useCase.position)
    }

    fun onLanguageSpinnerItemSelected(parent: AdapterView<*>?, view: View?, position: Int?, id: Long?) {
        Log.e("bind", position.toString())
        var item = parent!!.getItemAtPosition(position!!) as TagSpinnerItem
        Log.e("bindColor", item.color.toString())
        if (position == 0) {
            Log.e("ColorPosition",useCase.position)
            SharedPreprecncesUtils.setTag(useCase.position, 0)
        }
        else
            SharedPreprecncesUtils.setTag(useCase.position, item.color!!)

        SharedPreprecncesUtils.setSpinnerPostion(useCase.position, position)
        Detailplan()
        Log.e("list","update")
    }
}