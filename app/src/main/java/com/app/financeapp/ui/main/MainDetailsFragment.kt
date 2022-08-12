package com.app.financeapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.financeapp.R
import com.app.financeapp.databinding.FragmentMainDetailsBinding
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.ui.adapter.NewArticlesListAdapter
import com.app.financeapp.ui.base.BaseFinanceAppFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainDetailsFragment :
    BaseFinanceAppFragment<MainDetailsViewModel, FragmentMainDetailsBinding>() {

    private var adapter: NewArticlesListAdapter? = null

    override fun createViewModel() = ViewModelProvider(this)[MainDetailsViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_main_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner, Observer(::updateArticles))
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    private fun updateArticles(items: List<NewsArticle>) {
        adapter?.updateItems(items.toMutableList()) ?: run {
            adapter = NewArticlesListAdapter(items.toMutableList())
            binding.newsArticlesView.adapter = adapter
        }
    }
}