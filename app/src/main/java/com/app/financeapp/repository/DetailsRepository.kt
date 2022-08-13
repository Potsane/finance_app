package com.app.financeapp.repository

import com.app.financeapp.network.FinanceAppApiService
import com.app.financeapp.util.STOCK_TICKERS_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val apiService: FinanceAppApiService) {
    suspend fun getNewsArticles() = apiService.getNewsArticles()

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun getStockInfo(): BufferedReader = withContext(Dispatchers.IO) {
        BufferedReader(InputStreamReader(URL(STOCK_TICKERS_URL).openStream()))
    }
}