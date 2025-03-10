package com.example.swift_wallet_6611935

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swift_wallet_6611935.View.HistoryScreen
import com.example.swift_wallet_6611935.View.LoginContent
import com.example.swift_wallet_6611935.View.RegisterContent
import com.example.swift_wallet_6611935.View.MainContent
import com.example.swift_wallet_6611935.View.TransactionPage
import com.example.swift_wallet_6611935.View.UserManualPage
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel,  isDarkTheme: Boolean,
                    onThemeToggle: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginContent(modifier,navController,authViewModel)
        }
        composable("signup"){
            RegisterContent(modifier,navController,authViewModel)
        }
        composable("home"){
            MainContent(modifier,navController,authViewModel,isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle)
        }
        composable("UserManual"){
            UserManualPage(modifier,navController,authViewModel)
        }
        composable("history"){
            HistoryScreen(
                navController,
                paddingValues = PaddingValues(),
                isDarkTheme,
                onThemeToggle
            )
        }
        composable("transactionPage") {
            TransactionPage(navController,authViewModel,isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle) // Ensure this composable is valid and doesn't throw exceptions
        }

    })
}