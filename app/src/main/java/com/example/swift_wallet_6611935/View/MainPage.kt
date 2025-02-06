package com.example.swift_wallet_6611935.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.swift_wallet_6611935.ViewModel.AuthState
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel


@Composable
fun MainContent(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    var selectedTab by remember { mutableStateOf(0) }


    val authState = authViewModel.authState.observeAsState()


    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }


    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.List, "News") },
                    label = { Text("History") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.Person, "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> HomeScreen(paddingValues = paddingValues, authViewModel = authViewModel,modifier)
            1 -> HistoryScreen(paddingValues = paddingValues)
            2 -> ProfileScreen(paddingValues = paddingValues,navController, authViewModel = authViewModel)
        }
    }
}












