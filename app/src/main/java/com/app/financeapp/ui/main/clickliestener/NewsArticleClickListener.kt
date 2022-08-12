package com.app.financeapp.ui.main.clickliestener

import com.app.financeapp.network.model.NewsArticle

interface NewsArticleClickListener {
    fun onArticleClick(article: NewsArticle)
}