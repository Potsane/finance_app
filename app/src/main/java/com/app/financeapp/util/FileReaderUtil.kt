package com.app.financeapp.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader

suspend fun readStockTickerFiles(reader: BufferedReader): LinkedHashMap<String, MutableList<Double>>? =
    withContext(Dispatchers.IO) {
        val stockValuesTable = linkedMapOf<String, MutableList<Double>>()
        try {
            reader.forEachLine { line ->
                val items = line.split(",").toTypedArray()
                if (isNumber(items[1])) {
                    val key = items[0].replace("\"", "")
                    val value = items[1].trim().toDouble()
                    updateMap(stockValuesTable, key, value)
                }
            }
            return@withContext stockValuesTable
        } catch (exception: Exception) {
            return@withContext null
        }
    }

fun updateMap(map: LinkedHashMap<String, MutableList<Double>>, key: String, value: Double) {
    if (map.containsKey(key)) {
        map[key]?.add(value)
    } else {
        map[key] = mutableListOf(value)
    }
}

fun isNumber(s: String?): Boolean {
    val regex = """^(-)?[0-9]{0,}((\.){1}[0-9]{1,}){0,1}$""".toRegex()
    return if (s.isNullOrEmpty()) false
    else regex.matches(s.trim())
}