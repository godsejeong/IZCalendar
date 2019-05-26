package com.jjmin.izcalendar.ui.detailplan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.ext.android.viewModel
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.ActivityDetailPlanBinding
import org.koin.core.parameter.parametersOf

class DetailPlanActivity : AppCompatActivity() {

//    override val layoutResourceId: Int = R.layout.activity_detail_plan

    //    var position: Int? = null
//    var title: ArrayList<String>? = null
//    var date: String? = null
//    var dow: String? = null
//    var subtitle: ArrayList<String>? = null
    //    private val viewModelFactory by lazy {
//        DetailViewModelFactory(
//            position!!,
//            date!!,
//            title!!,
//            subtitle!!
//        )
//    }
    lateinit var detailviewBinding: ActivityDetailPlanBinding

    val useCase by lazy { DetailUseCase(this) }

    private val viewModel: DetailViewModel by viewModel { parametersOf(useCase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        position = intent.getIntExtra("position", 0)
//        date = intent.getStringExtra("date")
//        dow = intent.getStringExtra("dow")
//        date = "${date!!.substring(date!!.lastIndexOf("/") + 1)} ${DowChangeUtils.toKr(dow!!)}"
//        title = intent.getStringArrayListExtra("title")
//        subtitle = intent.getStringArrayListExtra("subtitle")
//        Log.e("data", "$date")
        detailviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_plan)
        detailviewBinding.lifecycleOwner = this
        detailviewBinding.vm = viewModel
    }
}
