package com.example.swift_wallet_6611935.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign

// Data Classes
data class StockResult(
    val symbol: String,
    val name: String,
    val currency: String?,
    val stockExchange: String,
    val exchangeShortName: String
)

// API Interface
interface FinancialModelingApiService {
    @GET("api/v3/search-ticker")
    suspend fun searchStocks(
        @Query("query") query: String,
        @Query("limit") limit: Int = 10,
        @Query("exchange") exchange: String = "NASDAQ",
        @Query("apikey") apiKey: String = "FVZgtsw0V2i5BteCYKNod3XslimWGnG9"
    ): List<StockResult>
}

@Composable
fun HomeScreen(paddingValues: PaddingValues, authViewModel: AuthViewModel, modifier: Modifier) {

    // State
    var searchQuery by remember { mutableStateOf("") }
    var stockData by remember { mutableStateOf<List<StockResult>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var lastUpdated by remember { mutableStateOf("") }

    // Create Retrofit instance
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://financialmodelingprep.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService = remember {
        retrofit.create(FinancialModelingApiService::class.java)
    }

    val scope = rememberCoroutineScope()

    // Function to fetch data
    fun searchStocks(query: String) {
        scope.launch {
            isLoading = true
            error = null
            try {
                val response = apiService.searchStocks(
                    query = query,
                    limit = 10,
                    exchange = "NASDAQ"
                )
                stockData = response
                lastUpdated = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                    .format(Date())
            } catch (e: Exception) {
                error = e.message ?: "An error occurred"
                println("Error searching stocks: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    // Initial search
    LaunchedEffect(Unit) {
        searchStocks("AA")  // Default search
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        // Search bar with NASDAQ label
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Stock News Search",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    if (it.length >= 2) {
                        searchStocks(it)
                    }
                },
                label = { Text("Search Ticker") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        // Last updated time
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Last updated: $lastUpdated",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Showing max 10 results",
                style = MaterialTheme.typography.bodySmall
            )
        }

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = error ?: "Unknown error occurred",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(onClick = { searchStocks(searchQuery) }) {
                            Text("Retry")
                        }
                    }
                }
            }
            stockData.isEmpty() && !isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No results found",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(stockData) { stock ->
                        StockCard(stock)
                    }
                }
            }
        }
    }
}

@Composable
private fun StockCard(stock: StockResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stock.symbol,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stock.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                stock.currency?.let {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stock.stockExchange,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}