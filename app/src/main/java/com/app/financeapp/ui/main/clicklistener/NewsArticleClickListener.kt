package com.app.financeapp.ui.main.clicklistener

import com.app.financeapp.network.model.NewsArticle

interface NewsArticleClickListener {
    fun onArticleClick(article: NewsArticle)
}