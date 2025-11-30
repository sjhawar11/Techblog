package com.sjhawar11.composetest.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sjhawar11.composetest.model.Post

@Composable
fun PostItem(
  post: Post,
  onLikeClick: (Post) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Header: User Info
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Person,
          contentDescription = null, // Decorative
          modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = post.username,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Body: Content
      Text(
        text = post.content,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 10, // Prevent infinite height in previews
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Footer: Actions (The Performance "Hot Zone")
      Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
          onClick = { onLikeClick(post) },
          modifier = Modifier.semantics {
            // 1. Name the element for the test finder
            contentDescription = "Like post by ${post.username}"

            // 2. 🟢 REQUIRED FOR assertIsOff() / assertIsOn()
            // This tells the test (and screen readers) the state of the button
            toggleableState = if (post.isLiked) ToggleableState.On else ToggleableState.Off
          }
        ) {
          Icon(
            imageVector = if (post.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null, // The parent IconButton handles the description
            tint = if (post.isLiked) Color.Red else LocalContentColor.current
          )
        }

        Text(
          text = "${post.likeCount}",
          style = MaterialTheme.typography.labelLarge
        )
      }
    }
  }
}