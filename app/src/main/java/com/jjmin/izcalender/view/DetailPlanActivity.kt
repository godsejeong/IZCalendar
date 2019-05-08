package com.jjmin.izcalender.view

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.jjmin.izcalender.R
import com.jjmin.izcalender.databinding.ActivityDetailPlanBinding
import com.jjmin.izcalender.viewmodel.DetailViewModel
import com.jjmin.izcalender.viewmodel.DetailViewModelFactory
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.Observer
import com.jjmin.izcalender.data.DetailPlanItem
import com.jjmin.izcalender.utils.SharedPreprecnceUtils
import kotlinx.android.synthetic.main.activity_detail_plan.*
import androidx.recyclerview.widget.SimpleItemAnimator




class DetailPlanActivity : AppCompatActivity() {
    var position: Int? = null
    private val viewModelFactory by lazy { DetailViewModelFactory(position!!,this) }
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java) }

    lateinit var detailviewBinding: ActivityDetailPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        position = intent.getIntExtra("position", 0)
        detailviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_plan)
        detailviewBinding.lifecycleOwner = this
        detailviewBinding.vm = viewModel


//        val itemObserver = Observer<ArrayList<DetailPlanItem>> { newName ->
//
//            (0 until newName.size).forEach {
//                newName[it].tagColor =
//                    if (SharedPreprecnceUtils.getColorTag(position!!) != 0) SharedPreprecnceUtils.getColorTag(position!!)
//                    else R.color.colorMyColor
//            }
//        }

    }
}
