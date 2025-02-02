package com.example.swift_wallet_6611935.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Text(
            text = "Transaction History",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )


        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(10) { index ->
                TransactionItem(
                    title = "Historical Transaction ${index + 1}",
                    amount = "-$${(index + 1) * 25}.00",
                    date = "${index + 1} days ago"
                )
            }
        }
    }
}

@Composable
private fun TransactionItem(
    title: String,
    amount: String,
    date: String
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(date) },
        trailingContent = { Text(amount) }
    )
}