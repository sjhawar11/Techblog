package com.sjhawar11.composetest.fakes

import com.sjhawar11.composetest.data.FeedRepository
import com.sjhawar11.composetest.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeFeedRepository : FeedRepository {
    // Public var so the Test can modify it before the screen launches
    val postsToReturn = MutableStateFlow<List<Post>>(emptyList())

    override fun getPosts(): Flow<List<Post>> = postsToReturn
}