package com.app.financeapp.network.model

data class NewsArticle(
    var source: ArticleSource,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
)