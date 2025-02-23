

package com.example.swift_wallet_6611935.View

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.swift_wallet_6611935.Localization.StringResources
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel


@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    authViewModel: AuthViewModel,
    isDarkTheme: Boolean,
    onThemeToggle: () -> Unit
) {

    var isThaiLanguage by remember { mutableStateOf(false) }
    val strings = if (isThaiLanguage) StringResources.ProfileScreen.th else StringResources.ProfileScreen.en

    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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

        // Profile Image with Picker
        Card(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context)
                            .data(data = selectedImageUri)
                            .build()
                    ),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                IconButton(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = "Add Photo",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }

//        Icon(
//            imageVector = Icons.Default.Person,
//            contentDescription = "Profile",
//            modifier = Modifier
//                .size(100.dp)
//                .padding(16.dp)
//        )

        Text(
            text = strings["userProfile"]!!,
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(strings["email"]!! + "user@example.com")
                Text(strings["accountType"]!! + "Standard")
                Text(strings["memberSince"]!! + "January 2024")
            }
        }

        TextButton(onClick = {
            navController.navigate("UserManual")
        }) {
            Text(text =strings["watchUserManual"]!!)
        }

        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign out")
        }
    }
}
