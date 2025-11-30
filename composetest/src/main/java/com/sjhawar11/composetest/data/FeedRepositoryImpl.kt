package com.sjhawar11.composetest.data

import com.sjhawar11.composetest.model.Post
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FeedRepositoryImpl @Inject constructor() : FeedRepository {
  override  fun getPosts(): Flow<List<Post>> = flow {
    // 1. Simulate Network Latency
    // The ViewModel will stay in "Loading" state while this hangs
    delay(2000)

    // 2. Generate Real-ish Data
    val data = List(20) { index ->
      Post(
        id = index.toString(),
        username = "User $index",
        content = "Real network content...",
        likeCount = 0,
        isLiked = false
      )
    }

    // 3. Emit the data to the stream
    emit(data)
  }
}