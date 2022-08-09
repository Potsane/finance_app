package com.app.financeapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.app.financeapp.BR
abstract class BaseFinanceAppFragment<VM : BaseFinanceAppViewModel, VDB : ViewDataBinding> :
    Fragment() {

    protected lateinit var binding: VDB
    protected val viewModel by lazy { createViewModel() }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
    }

    protected abstract fun createViewModel(): VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @CallSuper
    override fun onPause() {
        super.onPause()
        showProgressBar(false)
    }

    protected fun showProgressBar(show: Boolean) {
        val activity = requireActivity()
        if (activity is MainActivity) activity.showProgressBar(show)
    }
}