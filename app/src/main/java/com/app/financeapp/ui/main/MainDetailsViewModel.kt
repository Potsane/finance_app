package com.app.financeapp.ui.main

import androidx.lifecycle.*
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.network.model.StockTicker
import com.app.financeapp.repository.DetailsRepository
import com.app.financeapp.ui.base.BaseFinanceAppViewModel
import com.app.financeapp.ui.base.ShowProgress
import com.app.financeapp.ui.main.clickliestener.NewsArticleClickListener
import com.app.financeapp.datasource.StockDataProvider
import com.app.financeapp.util.readStockTickerFiles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainDetailsViewModel @Inject constructor(
    private val repository: DetailsRepository,
    private val stockDataProvider: StockDataProvider
) : BaseFinanceAppViewModel(),
    NewsArticleClickListener,
    DefaultLifecycleObserver {

    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> = _articles

    private val _topArticles = MutableLiveData<List<NewsArticle>>()
    val topArticles: LiveData<List<NewsArticle>> = _topArticles

    private val _stocks = MutableLiveData<List<StockTicker>>()
    val stocks: LiveData<List<StockTicker>> = _stocks

    override fun onResume(owner: LifecycleOwner) {
        stockDataProvider.observe(owner) { stocks ->
            if (_articles.value != null) _stocks.value = stocks
        }
        if (_stocks.value == null) {
            viewModelScope.launch { getStockInfo() }
        }
        if (_articles.value == null) initArticles()
    }

    override fun onArticleClick(article: NewsArticle) {
    }

    private fun initArticles() {
        viewModelScope.launch {
            postUiCommand(ShowProgress(true))
            getArticles()
            postUiCommand(ShowProgress(false))
        }
    }

    private suspend fun getStockInfo() {
        try {
            val reader = repository.getStockInfo()
            val map = readStockTickerFiles(reader)
            map?.let {
                stockDataProvider.addStockTickersData(map)
            }
        } catch (exception: Exception) {
        }
    }

    private suspend fun getArticles() {
        try {
            repository.getNewsArticles().let { response ->
                if (response.isSuccessful) {
                    val result = response.body()?.articles
                    result?.let {
                        _topArticles.value = it.slice(0..5)
                        _articles.value = it.slice(6 until it.size)
                    }
                }
            }
        } catch (exception: Exception) {
        }
    }
}