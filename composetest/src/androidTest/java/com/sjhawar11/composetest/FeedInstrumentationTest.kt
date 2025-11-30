package com.sjhawar11.composetest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sjhawar11.composetest.fakes.FakeFeedRepository
import com.sjhawar11.composetest.model.Post
import javax.inject.Inject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule // ✅ CORRECT


@HiltAndroidTest // 👈 Required for injection
@RunWith(AndroidJUnit4::class)
class FeedInstrumentationTest {

  @get:Rule(order = 0)
  val hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1)
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Inject
  lateinit var fakeRepository: FakeFeedRepository // 👈 Inject the fake

  @Before
  fun setup() {
    hiltRule.inject() // Initialize Hilt

    // 🟢 SET THE DATA HERE
    fakeRepository.postsToReturn.value = listOf(
        Post("1", "TestUser", "Controlled Data", 0, false)
    )
  }

  @Test
  fun testDataAppears() {
    // No waiting needed! The fake is instant.
    composeTestRule.onNodeWithText("TestUser").assertIsDisplayed()
  }

  @Test
  fun testLikeButtonInteraction() {
    // --- STEP A: SET THE SCENE (The "Unit" Style) ---
    // We set the data BEFORE the UI loads.
    // No network calls. Instant. Deterministic.
    fakeRepository.postsToReturn.value = listOf(
      Post("1", "Alice", "UI Test Content", 0, isLiked = false)
    )

    // --- STEP B: INTERACT WITH UI (The "UI" Style) ---
    // Now we use the Compose Rule. This interacts with the ACTUAL screen.

    // 1. Verify the item rendered on screen
    composeTestRule.onNodeWithText("Alice").assertIsDisplayed()

    // 2. Verify the heart is empty (Visual Check)
    composeTestRule.onNodeWithContentDescription("Like post by Alice")
      .assertIsOff()

    // 3. Click the button (Gestures)
    composeTestRule.onNodeWithContentDescription("Like post by Alice")
      .performClick()

    // --- STEP C: VERIFY VISUAL OUTCOME ---
    // Did the icon turn red/filled?
    composeTestRule.onNodeWithContentDescription("Like post by Alice")
      .assertIsOn()
  }
}