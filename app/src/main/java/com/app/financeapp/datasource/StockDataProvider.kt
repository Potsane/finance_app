package com.app.financeapp.datasource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.app.financeapp.extension.toTwoDecimalPlaces
import com.app.financeapp.network.model.StockTicker
import kotlinx.coroutines.delay
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

interface StockDataProvider {
    suspend fun addStockTickersData(stockTickerData: LinkedHashMap<String, MutableList<Double>>)
    fun observe(owner: LifecycleOwner, observer: (List<StockTicker>) -> Unit)
}

class StockDataProviderImpl : StockDataProvider {
    private val _stockTickers = MutableLiveData<List<StockTicker>>()
    private lateinit var stockTickerData: LinkedHashMap<String, MutableList<Double>>

    override suspend fun addStockTickersData(stockTickerData: LinkedHashMap<String, MutableList<Double>>) {
        this.stockTickerData = stockTickerData
        postStockTickers()
    }

    override fun observe(owner: LifecycleOwner, observer: (List<StockTicker>) -> Unit) {
        _stockTickers.observe(owner) { observer(it) }
    }

    private suspend fun postStockTickers() {
        val currentList = mutableListOf<StockTicker>()
        stockTickerData.forEach { (key, value) ->
            currentList.add(
                StockTicker(
                    key,
                    value[Random.nextInt(0, value.size)].toTwoDecimalPlaces(),
                    randomizePercentageChange()
                )
            )
        }
        _stockTickers.value = currentList
        delay(1000)
        postStockTickers()
    }

    private fun randomizePercentageChange(): Double {
        return ThreadLocalRandom.current().nextDouble(0.1, 50.00)
    }
}