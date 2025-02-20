package com.example.swift_wallet_6611935.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HistoryScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        // Theme Toggle Button (Top-left corner)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onThemeToggle) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Filled.Brightness7 else Icons.Filled.Brightness4,
                    contentDescription = "Toggle Theme"
                )
            }
        }
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
