package com.app.financeapp.network.model

data class NewsArticlesResult(
    var totalResults: Int,
    var articles: List<NewsArticle>,
)