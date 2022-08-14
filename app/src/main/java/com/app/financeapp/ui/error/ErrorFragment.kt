package com.app.financeapp.ui.error

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.financeapp.R
import com.app.financeapp.databinding.FragmentErrorBinding
import com.app.financeapp.ui.base.BaseFinanceAppFragment

class ErrorFragment : BaseFinanceAppFragment<ErrorViewModel, FragmentErrorBinding>() {

    override fun createViewModel() = ViewModelProvider(this)[ErrorViewModel::class.java]

    override fun getLayoutId() =  R.layout.fragment_error
}