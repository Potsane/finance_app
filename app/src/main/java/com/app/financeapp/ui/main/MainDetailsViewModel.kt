package com.app.financeapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.financeapp.network.model.NewsArticle
import com.app.financeapp.repository.DetailsRepository
import com.app.financeapp.ui.base.BaseFinanceAppViewModel
import com.app.financeapp.ui.base.ShowProgress
import com.app.financeapp.ui.main.clickliestener.NewsArticleClickListener
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

    fun onResume() {
        if (_articles.value == null) getArticles()
    }

    override fun onArticleClick(article: NewsArticle) {
    }

    private fun getArticles() {
        viewModelScope.launch {
            postUiCommand(ShowProgress(true))
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
                postUiCommand(ShowProgress(false))
            } catch (exception: Exception) {
                println(exception)
                //navigate(EventsFragmentDirections.toError())
            }
        }
    }

}