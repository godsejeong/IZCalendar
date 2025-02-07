package com.jjmin.izcalendar.ui.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jjmin.izcalendar.R
import com.jjmin.izcalendar.databinding.FragmentTutorialThreeBinding
import com.jjmin.izcalendar.databinding.FragmentTutorialTwoBinding
import com.jjmin.izcalendar.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class TutorialTwoFragment : BaseFragment<FragmentTutorialTwoBinding>() {
    override val layoutResourceId: Int = R.layout.fragment_tutorial_two

    val useCase by lazy { TutorialUseCase(null, this) }
    val viewModel: TutorialViewModel by viewModel { parametersOf(useCase) }

    companion object {
        @JvmStatic
        fun newInstance() = TutorialTwoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding?.vm = viewModel
    }
}