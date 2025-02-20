//package com.example.swift_wallet_6611935
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.activity.viewModels
//import com.example.swift_wallet_6611935.ui.theme.Swift_Wallet_6611935Theme
//import com.example.swift_wallet_6611935.ViewModel.AuthViewModel
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        val authViewModel: AuthViewModel by viewModels()
//
//        setContent {
//            Swift_Wallet_6611935Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MyAppNavigation(
//                        modifier = Modifier.padding(innerPadding),
//                        authViewModel = authViewModel
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Swift_Wallet_6611935Theme {
//        // Add content to preview here if needed
//    }
//}

package com.example.swift_wallet_6611935

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import com.example.swift_wallet_6611935.ui.theme.Swift_Wallet_6611935Theme
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.swift_wallet_6611935.View.ProfileScreen


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.swift_wallet_6611935.View.LoginContent
import com.example.swift_wallet_6611935.View.MainContent
import com.example.swift_wallet_6611935.ui.theme.Swift_Wallet_6611935Theme

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel


class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authViewModel : AuthViewModel by viewModels()

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) } // Theme state

            Swift_Wallet_6611935Theme(darkTheme = isDarkTheme) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(modifier =  Modifier.padding(innerPadding),authViewModel = authViewModel, isDarkTheme = isDarkTheme,
                        onThemeToggle = { isDarkTheme = !isDarkTheme})
                }

            }
        }
    }
}
