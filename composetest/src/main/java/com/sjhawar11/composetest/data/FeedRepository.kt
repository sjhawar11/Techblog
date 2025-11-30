package com.sjhawar11.composetest.data

import com.sjhawar11.composetest.model.Post
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
  fun getPosts(): Flow<List<Post>>
}