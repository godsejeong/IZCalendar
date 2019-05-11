package com.jjmin.izcalender.view

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
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
import com.jjmin.izcalender.utils.DowChangeUtils


class DetailPlanActivity : AppCompatActivity() {
    var position: Int? = null
    var title : ArrayList<String>? = null
    var date : String? = null
    var dow : String? = null
    var subtitle : ArrayList<String>? = null
    private val viewModelFactory by lazy { DetailViewModelFactory(position!!,date!!,title!!,subtitle!!) }
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java) }

    lateinit var detailviewBinding: ActivityDetailPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        position = intent.getIntExtra("position", 0)
        date = intent.getStringExtra("date")
        dow = intent.getStringExtra("dow")
        date = "${date!!.substring(date!!.lastIndexOf("/")+1)} ${DowChangeUtils.toKr(dow!!)}"
        title = intent.getStringArrayListExtra("title")
        subtitle = intent.getStringArrayListExtra("subtitle")
        Log.e("data","$date")

        detailviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_plan)
        detailviewBinding.lifecycleOwner = this
        detailviewBinding.vm = viewModel
    }
}
