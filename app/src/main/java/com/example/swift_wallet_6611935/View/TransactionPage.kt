package com.example.swift_wallet_6611935.View

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel

@Composable
fun TransactionPage(navController: NavController, authViewModel: AuthViewModel) {
    var accountNumber by remember { mutableStateOf(TextFieldValue("")) }
    var amount by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Logic to validate input
    fun isFormValid(): Boolean {
        return accountNumber.text.isNotBlank() && amount.isNotBlank() && amount.toDoubleOrNull() != null
    }

    // Handle when Transfer button is clicked
    fun onTransfer() {
        if (isFormValid()) {
            showDialog = true
        } else {
            Toast.makeText(navController.context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(text = "Make a Transaction", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Account Number Input
        OutlinedTextField(
            value = accountNumber,
            onValueChange = { accountNumber = it },
            label = { Text(text = "Account Number") },
            modifier = Modifier.fillMaxWidth(),
            isError = accountNumber.text.isEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Amount Input
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text(text = "Amount") },
            modifier = Modifier.fillMaxWidth(),
            isError = amount.isNotBlank() && amount.toDoubleOrNull() == null
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Buttons Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Back Button
            Button(
                onClick = {
                    navController.navigate("home") // Navigates back to Home (with Bottom Navigation Bar)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Back")
            }


            // Transfer Button
            Button(
                onClick = { onTransfer() },
                modifier = Modifier.weight(1f),
                enabled = isFormValid()
            ) {
                Text(text = "Transfer")
            }
        }
    }

    // Show Transfer Success Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Transaction Status") },
            text = { Text("Transferred Successfully") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        navController.navigate("history") // Navigate to history page after successful transfer
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun TransactionPagePreview() {
    // Preview of the Transaction Page
    TransactionPage(navController = rememberNavController(), authViewModel = AuthViewModel())
}