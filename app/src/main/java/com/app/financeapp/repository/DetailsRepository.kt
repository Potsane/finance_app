package com.app.financeapp.repository

import com.app.financeapp.network.FinanceAppApiService
import javax.inject.Inject

class DetailsRepository  @Inject constructor(private val apiService: FinanceAppApiService) {
    suspend fun getNewsArticles() = apiService.getNewsArticles()
}