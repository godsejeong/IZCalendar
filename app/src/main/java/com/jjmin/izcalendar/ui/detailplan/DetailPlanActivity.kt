package com.jjmin.izcalendar.ui.detailplan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.ext.android.viewModel
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityDetailPlanBinding
import org.koin.core.parameter.parametersOf

class DetailPlanActivity : AppCompatActivity() {

    lateinit var detailviewBinding: ActivityDetailPlanBinding

    val useCase by lazy { DetailUseCase(this) }

    private val viewModel: DetailViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //0월00일 월
        detailviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_plan)
        detailviewBinding.lifecycleOwner = this
        detailviewBinding.vm = viewModel
    }
}
