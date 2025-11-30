package com.sjhawar11.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sjhawar11.composetest.ui.feed.FeedScreen
import com.sjhawar11.composetest.ui.theme.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // 👈 CRITICAL: This allows hiltViewModel() to work inside FeedScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestTheme {
                FeedScreen()
            }
        }
    }
}