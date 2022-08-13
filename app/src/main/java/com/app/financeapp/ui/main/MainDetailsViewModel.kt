package com.app.financeapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.network.model.StockTicker
import com.app.financeapp.repository.DetailsRepository
import com.app.financeapp.ui.base.BaseFinanceAppViewModel
import com.app.financeapp.ui.base.ShowProgress
import com.app.financeapp.ui.main.clickliestener.NewsArticleClickListener
import com.app.financeapp.util.readStockTickerFiles
import com.app.financeapp.util.stockTickers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainDetailsViewModel @Inject constructor(
    private val repository: DetailsRepository
) : BaseFinanceAppViewModel(),
    NewsArticleClickListener {

    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> = _articles

    private val _topArticles = MutableLiveData<List<NewsArticle>>()
    val topArticles: LiveData<List<NewsArticle>> = _topArticles

    private val _stocks = MutableLiveData<List<StockTicker>>()
    val stocks: LiveData<List<StockTicker>> = _stocks

    fun onResume() {
        if (_articles.value == null) initScreen()
    }

    override fun onArticleClick(article: NewsArticle) {
    }

    private fun initScreen() {
        viewModelScope.launch {
            postUiCommand(ShowProgress(true))
            getArticles()
            getStockInfo()
            postUiCommand(ShowProgress(false))
            _stocks.value = stockTickers
        }
    }

    private suspend fun getStockInfo() {
        try {
            val reader = repository.getStockInfo()
            val map = readStockTickerFiles(reader)
        } catch (exception: Exception) {
            //navigate(EventsFragmentDirections.toError())
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
            //navigate(EventsFragmentDirections.toError())
        }
    }
}