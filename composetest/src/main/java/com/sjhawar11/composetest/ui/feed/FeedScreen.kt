package com.sjhawar11.composetest.ui.feed

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.sjhawar11.composetest.model.FeedUiState
import com.sjhawar11.composetest.model.Post
import com.sjhawar11.composetest.ui.previews.DevicePreviews
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sjhawar11.composetest.ui.previews.FeedStateProvider
import com.sjhawar11.composetest.ui.theme.ComposeTestTheme
import androidx.compose.foundation.lazy.items
import com.sjhawar11.composetest.ui.components.ErrorView
import com.sjhawar11.composetest.ui.components.LoadingView
import com.sjhawar11.composetest.ui.components.PostItem

@Composable
fun FeedScreen(
  viewModel: FeedViewModel = hiltViewModel()
) {
  val state by viewModel.uiState.collectAsStateWithLifecycle()

  // 🔴 THE PERFORMANCE TRAP (Layer 4)
  // If we pass this lambda directly to PostItem, it is "unstable"
  // Every time FeedScreen recomposes, a new lambda instance is created.
  // This forces every PostItem to recompose, even if their data didn't change.

  // val onLikeClick = { post: Post -> viewModel.toggleLike(post) } // <--- BaAD ❌

  // 🟢 THE FIX (Layer 4)
  // Use a Method Reference. This is stable.
  FeedList(state = state, onLikeClick = viewModel::toggleLike) // <--- GOOD ✅
}

@Composable
fun FeedList(
  state: FeedUiState,
  onLikeClick: (Post) -> Unit
) {
  // Layer 1: Consuming the Previews here
  when (state) {
    is FeedUiState.Loading -> LoadingView()
    is FeedUiState.Error -> ErrorView(state.message)
    is FeedUiState.Success -> {
      LazyColumn {
        items(
          items = state.posts,
          key = { it.id } // Crucial for performance!
        ) { post ->
          PostItem(
            post = post,
            onLikeClick = onLikeClick
          )
        }
      }
    }
  }
}

@DevicePreviews
@Composable
private fun FeedScreenPreview(
  @PreviewParameter(FeedStateProvider::class) state: FeedUiState
) {
  ComposeTestTheme {
    FeedList(state = state, onLikeClick = {})
  }
}