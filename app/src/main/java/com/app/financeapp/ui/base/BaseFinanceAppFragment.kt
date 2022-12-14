package com.app.financeapp.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.financeapp.BR
import com.app.financeapp.navigation.NavigationCommand

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

        viewModel.uiCommands.observe(viewLifecycleOwner, Observer(::onUiCommands))
        viewModel.navigationCommands.observe(viewLifecycleOwner, Observer(::onNavigate))
    }

    @CallSuper
    protected open fun onUiCommands(command: Any) {
        when (command) {
            is ShowProgress -> showProgressBar(command.show)
            is LaunchExternalPage -> openWebPage(command.url)
        }
    }

    private fun onNavigate(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navigationCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    protected abstract fun createViewModel(): VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @CallSuper
    override fun onPause() {
        super.onPause()
        showProgressBar(false)
    }

    private fun showProgressBar(show: Boolean) {
        val activity = requireActivity()
        if (activity is MainActivity) activity.showProgressBar(show)
    }

    private fun openWebPage(url: String?) {
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browse)
    }
}