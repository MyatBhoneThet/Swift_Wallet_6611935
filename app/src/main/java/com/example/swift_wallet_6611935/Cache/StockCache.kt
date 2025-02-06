package com.example.swift_wallet_6611935.Cache

import android.content.Context
import android.content.SharedPreferences
import com.example.swift_wallet_6611935.View.StockResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StockCache(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("stock_cache", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveStocks(query: String, stocks: List<StockResult>) {
        val stocksJson = gson.toJson(stocks)
        prefs.edit().putString(query, stocksJson).apply()
    }

    fun getStocks(query: String): List<StockResult>? {
        val stocksJson = prefs.getString(query, null) ?: return null
        val type = object : TypeToken<List<StockResult>>() {}.type
        return gson.fromJson(stocksJson, type)
    }
}
