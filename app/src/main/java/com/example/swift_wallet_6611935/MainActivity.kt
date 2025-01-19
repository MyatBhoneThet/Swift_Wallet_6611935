package com.example.swift_wallet_6611935

import android.os.Bundle
import androidx.activity.ComponentActivity
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Swift_Wallet_6611935Theme {
                MainContent()


            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Swift_Wallet_6611935Theme {

    }
}