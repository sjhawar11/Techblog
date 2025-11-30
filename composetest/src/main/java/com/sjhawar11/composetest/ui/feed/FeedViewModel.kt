package com.sjhawar11.composetest.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjhawar11.composetest.data.FeedRepository
import com.sjhawar11.composetest.model.FeedUiState
import com.sjhawar11.composetest.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
  private val repository: FeedRepository // 👈 Inject Interface, not class
) : ViewModel() {

  private val _uiState = MutableStateFlow<FeedUiState>(FeedUiState.Loading)
  val uiState = _uiState.asStateFlow()

  init {
    loadData()
  }

  private fun loadData() {
    viewModelScope.launch {
      _uiState.value = FeedUiState.Loading

      repository.getPosts()
        .collect { posts ->
        _uiState.value = FeedUiState.Success(posts)
        }
    }
  }

  // 🟢 STABLE METHOD REFERENCE TARGET
  // This function signature (Post) -> Unit matches our UI requirement perfectly.
  fun toggleLike(post: Post) {
    _uiState.update { currentState ->
      if (currentState is FeedUiState.Success) {
        val updatedPosts = currentState.posts.map { currentPost ->
          if (currentPost.id == post.id) {
            // Create a NEW copy of the post (Immutability)
            currentPost.copy(
              isLiked = !currentPost.isLiked,
              likeCount = if (currentPost.isLiked) currentPost.likeCount - 1 else currentPost.likeCount + 1
            )
          } else {
            currentPost
          }
        }
        currentState.copy(posts = updatedPosts)
      } else {
        currentState
      }
    }
  }
}