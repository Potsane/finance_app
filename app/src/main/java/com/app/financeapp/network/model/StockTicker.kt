package com.app.financeapp.network.model

data class StockTicker(
    var symbol: String,
    var currentPrice: String,
    var percentageChange: Double
)