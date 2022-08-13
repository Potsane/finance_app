package com.app.financeapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.app.financeapp.extension.toSingleEvent
import com.app.financeapp.network.model.StockTicker

class StockDataHolder {
    private val _stockTickers = MutableLiveData<List<StockTicker>>().toSingleEvent()

    private lateinit var stockTickerData: LinkedHashMap<String, MutableLiveData<String>>

    fun addStockTickers(stockTickerData: LinkedHashMap<String, MutableLiveData<String>>) {
        this.stockTickerData = stockTickerData
    }

    fun observe(
        owner: LifecycleOwner,
        observer: (List<StockTicker>) -> Unit
    ) {
        _stockTickers.observe(owner) { stocks ->
            observer(stocks)
        }
    }
}