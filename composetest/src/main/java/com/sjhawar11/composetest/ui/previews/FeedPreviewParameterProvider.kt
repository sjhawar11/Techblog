package com.sjhawar11.composetest.ui.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sjhawar11.composetest.model.FeedUiState
import com.sjhawar11.composetest.model.Post

class FeedStateProvider : PreviewParameterProvider<FeedUiState> {
  override val values = sequenceOf(
    // 1. Happy Path
    FeedUiState.Success(
      listOf(
        Post("1", "Alice", "Just launched the app! 🚀", 10, false),
        Post("2", "Bob", "Compose is awesome.", 5, true)
      )
    ),
    // 2. The "Grid of Death": Loading
    FeedUiState.Loading,
    // 3. The "Grid of Death": Error
    FeedUiState.Error("Failed to load feed. Check internet."),
    // 4. Edge Case: Ultra long text + High numbers (Layout breaker)
    FeedUiState.Success(
      listOf(
        Post(
          id = "99",
          username = "UserWithVeryLongNameThatMightClip",
          content = "This is a very long post text that is designed to test how the UI handles multiline text wrapping. ".repeat(5),
          likeCount = 9999999, // Check number formatting
          isLiked = false
        )
      )
    )
  )
}