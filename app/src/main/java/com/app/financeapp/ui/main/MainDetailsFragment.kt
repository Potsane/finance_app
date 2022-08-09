package com.app.financeapp.ui.main

import androidx.lifecycle.ViewModelProvider
import com.app.financeapp.R
import com.app.financeapp.databinding.FragmentMainDetailsBinding
import com.app.financeapp.ui.base.BaseFinanceAppFragment

class MainDetailsFragment :
    BaseFinanceAppFragment<MainDetailsViewModel, FragmentMainDetailsBinding>() {

    override fun createViewModel() = ViewModelProvider(this)[MainDetailsViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_main_details

    companion object {
        fun newInstance() = MainDetailsFragment()
    }
}