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
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail_plan.*




class DetailPlanActivity : AppCompatActivity(){

    private val viewModelFactory by lazy { DetailViewModelFactory() }
    private val viewModel by lazy { ViewModelProviders.of(this,viewModelFactory).get(DetailViewModel::class.java) }

    lateinit var detailviewBinding : ActivityDetailPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailviewBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail_plan)
        detailviewBinding.lifecycleOwner = this
        detailviewBinding.vm = viewModel

    }
}
