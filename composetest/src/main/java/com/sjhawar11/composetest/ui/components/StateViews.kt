package com.sjhawar11.composetest.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingView() {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    CircularProgressIndicator()
  }
}

@Composable
fun ErrorView(message: String) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "⚠️ Error",
      style = MaterialTheme.typography.headlineMedium,
      color = MaterialTheme.colorScheme.error
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = message,
      style = MaterialTheme.typography.bodyLarge
    )
    // Add a retry button here in a real app
  }
}