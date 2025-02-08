package com.example.swift_wallet_6611935.View

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HistoryScreen(navController: NavController, paddingValues: PaddingValues) {
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

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(10) { index ->
                TransactionItem(
                    title = "Historical Transaction ${index + 1}",
                    amount = "-$${(index + 1) * 25}.00",
                    date = "${index + 1} days ago"
                )
            }
        }

        // Button to navigate to the transaction page
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Add padding if needed
            verticalArrangement = Arrangement.Center, // Center vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            TextButton(
                onClick = {
                    // Navigate to transaction page when clicked
                    navController.navigate("transactionPage") // Make sure the route is correct
                }
            ) {
                Text(text = "Make a transaction")
            }
        }

    }
}

@Composable
fun TransactionItem(title: String, amount: String, date: String) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(date) },
        trailingContent = { Text(amount) }
    )
}
