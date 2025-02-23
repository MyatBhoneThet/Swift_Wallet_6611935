package com.example.swift_wallet_6611935.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView as StyledPlayerView
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.swift_wallet_6611935.ViewModel.AuthViewModel
import kotlinx.coroutines.delay

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView



//@Composable
//fun UserManualPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
//    var isPlaying by remember { mutableStateOf(false) }
//    var currentPosition by remember { mutableStateOf(0L) }
//    var duration by remember { mutableStateOf(0L) }
//
//    val context = LocalContext.current
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaItem.fromUri("https://youtu.be/YFtaS3kTMqM?si=QVB_GwLy8KXUAZPM"))
//            prepare()
//            addListener(object : Player.Listener {
//                override fun onPlaybackStateChanged(state: Int) {
//                    if (state == Player.STATE_READY) {
//                        duration = duration
//                    }
//                }
//            })
//        }
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//    Column(modifier = modifier.fillMaxWidth()) {
//        // Video View
//        AndroidView(
//            factory = { context ->
//                StyledPlayerView(context).apply {
//                    player = exoPlayer
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(16f/9f)
//        )
//
//        // Controls
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Play/Pause Button
//            IconButton(onClick = {
//                if (isPlaying) {
//                    exoPlayer.pause()
//                } else {
//                    exoPlayer.play()
//                }
//                isPlaying = !isPlaying
//            }) {
//                Icon(
//                    imageVector = if (isPlaying) Icons.Default.Close else Icons.Default.PlayArrow,
//                    contentDescription = if (isPlaying) "Pause" else "Play"
//                )
//            }
//
//            // Progress Bar
//            Slider(
//                value = currentPosition.toFloat(),
//                onValueChange = { position ->
//                    exoPlayer.seekTo(position.toLong())
//                },
//                valueRange = 0f..duration.toFloat(),
//                modifier = Modifier.weight(1f)
//            )
//
//            // Duration Text
//            Text(
//                text = "${currentPosition.milliseconds} / ${duration.milliseconds}",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//
//            // Fullscreen Button
//            IconButton(onClick = { /* Handle fullscreen */ }) {
//                Icon(
//                    imageVector = Icons.Default.KeyboardArrowDown,
//                    contentDescription = "Fullscreen"
//                )
//            }
//        }
//
//
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            // Back Button
//            Button(
//                onClick = {
//                    navController.navigate("home") // Navigates back to Home (with Bottom Navigation Bar)
//                },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(text = "Back")
//            }
//
//        }
//    }
//
//
//    // Position Tracker
//    LaunchedEffect(Unit) {
//        while (true) {
//            currentPosition = exoPlayer.currentPosition
//            delay(1000)
//        }
//    }
//}


@Composable
fun UserManualPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Full YouTube WebView
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        loadsImagesAutomatically = true
                    }
                    webChromeClient = WebChromeClient()
                    webViewClient = WebViewClient()
                    loadUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate("home")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Back")
            }
        }
    }
}

