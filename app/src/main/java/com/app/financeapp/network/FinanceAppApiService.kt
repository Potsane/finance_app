package com.app.financeapp.network

import com.app.financeapp.network.model.NewsArticlesResult
import com.app.financeapp.util.NEWS_FEED_URL
import retrofit2.Response
import retrofit2.http.GET

interface FinanceAppApiService {

    @GET(NEWS_FEED_URL)
//    @GET("http://saurav.tech/NewsAPI/everything/cnn.json")
    suspend fun getNewsArticles(): Response<NewsArticlesResult>
}