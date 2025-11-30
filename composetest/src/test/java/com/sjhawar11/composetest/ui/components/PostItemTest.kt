package com.sjhawar11.composetest.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.sjhawar11.composetest.model.Post
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PostItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testOnLikeClick() {
        val post = Post(
            id = "1",
            username = "sjhawar11",
            content = "This is a test post",
            likeCount = 0,
            isLiked = false
        )
        var liked = false

        composeTestRule.setContent {
            PostItem(
                post = post,
                onLikeClick = { liked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Like post by sjhawar11").performClick()

        assert(liked)
    }
}
