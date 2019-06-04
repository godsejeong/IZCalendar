package com.jjmin.izcalendar.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.FragmentTutorialOneBinding
import com.jjmin.izcalendar.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TutorialOneFragment : BaseFragment<FragmentTutorialOneBinding>() {
    override val layoutResourceId: Int = R.layout.fragment_tutorial_one

    val useCase by lazy { TutorialUseCase(null, this) }
    val viewModel: TutorialViewModel by viewModel { parametersOf(useCase) }

    companion object {
        @JvmStatic
        fun newInstance() = TutorialOneFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding?.vm = viewModel
    }
}
