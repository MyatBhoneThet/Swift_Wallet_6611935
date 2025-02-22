package com.example.swift_wallet_6611935.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swift_wallet_6611935.Localization.StringResources

@Composable
fun HistoryScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {

    var isThaiLanguage by remember { mutableStateOf(false) }
    val strings = if (isThaiLanguage) StringResources.HistoryScreen.th else StringResources.HistoryScreen.en

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
            Spacer(modifier = Modifier.width(220.dp))
            // Language Toggle
            TextButton(
                onClick = { isThaiLanguage = !isThaiLanguage }
            ) {
                Text(strings["toggleLanguage"]!!)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E3A8A), shape = RoundedCornerShape(16.dp))
                .padding(vertical = 8.dp) // Padding for spacing
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        navController.navigate("TransactionPage")
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.White) // White text color
                ) {
                    Text(text = strings["makeTransaction"]!!)
                }
            }
        }



        Text(
            text = strings["transactionHistory"]!!,
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
            TextButton(
                onClick = {
                    navController.navigate("TransactionPage")
                },
                colors = ButtonDefaults.textButtonColors(contentColor = Color.White) // White text color
            ) {
                Text(text = "Make a transaction")

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
