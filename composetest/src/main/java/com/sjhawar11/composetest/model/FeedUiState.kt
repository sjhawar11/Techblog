package com.sjhawar11.composetest.model

// The UI State wrapper to handle the "Grid of Death" (Loading, Error, Content)
sealed interface FeedUiState {
  data object Loading : FeedUiState
  data class Error(val message: String) : FeedUiState
  data class Success(val posts: List<Post>) : FeedUiState
}