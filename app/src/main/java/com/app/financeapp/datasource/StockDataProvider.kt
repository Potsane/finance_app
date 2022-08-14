package com.app.financeapp.datasource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.app.financeapp.network.model.StockTicker
import kotlinx.coroutines.delay
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
                    roundTheNumber(value[Random.nextInt(0, value.size)]),
                    "10.0"
                )
            )
        }
        _stockTickers.value = currentList
        delay(100)
        postStockTickers()
    }

    private fun roundTheNumber(number: Double) = "%.2f".format(number)
}