# Compose Test Strategies

This project is companion code for the article 
[The 4-Layer Defense: A Strategic Approach to Jetpack Compose Testing](https://medium.com/@sjhawar11/the-4-layer-defense-a-strategic-approach-to-jetpack-compose-testing-216db440b724)

The project demonstrates three key verification strategies:
1.  **Instrumentation Testing**: Full-screen tests with faked data using Hilt.
2.  **Local UI Testing**: JVM-based tests for individual components using Robolectric.
3.  **Previews**: Advanced previews for rapid visual feedback on components and screens across different states and device configurations.

## 🚀 Features
*   **Modern UI with Jetpack Compose**: The entire UI is built using Kotlin and Jetpack Compose.
*   **Dependency Injection with Hilt**: Dagger Hilt is integrated for managing dependencies, making the code modular and testable.
*   **Comprehensive Testing**: The project demonstrates local UI tests, full instrumentation tests, and advanced preview setups.

## 🛠️ Tech Stack & Dependencies
This project leverages a modern Android tech stack:
*   **Kotlin**: The primary programming language.
*   **Jetpack Compose**: For building the user interface.
    *   Includes `ui`, `material3`, `material-icons-extended`, and the Compose BOM.
    *   `ui-tooling-preview` and `ui-tooling` for Previews.
*   **Dagger Hilt**: For dependency injection, including Hilt's testing capabilities (`hilt-android-testing`).
*   **Testing**:
    *   **JUnit4**: Core testing framework.
    *   **Robolectric**: For running local JVM-based UI tests.
    *   **Compose Test Rules**: `createComposeRule` and `createAndroidComposeRule` for UI testing.
    *   **Hilt Android Testing**: For injecting dependencies in instrumented tests.

## 🧪 Automated Testing
The project contains two different types of automated tests.

### 1. FeedInstrumentationTest (Full Screen Instrumentation Test)
This is an instrumented test that launches the entire `MainActivity` and tests the feed screen. It uses `Hilt` to replace the real data repository with a `FakeFeedRepository`, allowing for controlled and deterministic testing.

**Location**: `src/androidTest/java/com/sjhawar11/composetest/FeedInstrumentationTest.kt`

**How to run:**
1.  Ensure you have an Android emulator running or a physical device connected.
2.  Open the `FeedInstrumentationTest.kt` file.
3.  Click the green "play" icon next to the `FeedInstrumentationTest` class name.
4.  Alternatively, run the Gradle task: `./gradlew connectedAndroidTest`

### 2. PostItemTest (Local UI Test)
This is a local unit test that verifies the functionality of the standalone `PostItem` composable. It runs on the JVM using Robolectric.

**Location**: `src/test/java/com/sjhawar11/composetest/ui/components/PostItemTest.kt`

**How to run:**
*   Click the green "play" icon next to the test class in Android Studio.
*   Run the Gradle task: `./gradlew testDebugUnitTest`

## 🎨 Visual Verification with Advanced Previews
In addition to automated tests, this project uses advanced Jetpack Compose Preview features for rapid, iterative UI development and visual verification.

### 1. DevicePreviews - Multi-Config Annotation
To avoid repeating the same set of `@Preview` annotations on every composable, we use a custom annotation class. This makes it easy to preview a component across a standard set of device configurations with a single line of code.

**Location**: `src/main/java/com/sjhawar11/composetest/ui/previews/DevicePreviews.kt`

**Usage:**

Instead of writing this:
```kotlin
@Preview(name = "Pixel 5 - Light", device = "id:pixel_5", showBackground = true) 
@Preview(name = "Pixel 5 - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES) 
@Preview(name = "Font Scale 1.5x", fontScale = 1.5f) 
@Composable fun MyComponentPreview() {
    // ... 
}
```


We can simply use our custom annotation:
```kotlin
@DevicePreviews 
@Composable fun MyComponentPreview() { 
    // ...
}
```


### 2. FeedStateProvider - Previewing Different UI States
For complex screens that can exist in multiple states (e.g., loading, error, success), we use a `PreviewParameterProvider`. This class supplies a sequence of different `FeedUiState` objects to our preview, allowing us to see how the UI renders in every possible scenario.

This is invaluable for testing:
*   **Happy Paths**: A list of posts.
*   **Loading States**: A loading indicator is shown.
*   **Error States**: An error message is displayed.
*   **Edge Cases**: Posts with very long text or high numbers to check for layout issues.

**Location**: `src/main/java/com/sjhawar11/composetest/ui/previews/FeedPreviewParameterProvider.kt`

**Usage:**
This provider is used in a preview for a screen-level composable, like `FeedScreen`, to generate multiple previews from a single `@Preview` annotation.

```kotlin
@DevicePreviews 
@Composable fun FeedScreenPreview( 
    @PreviewParameter(FeedStateProvider::class) state: FeedUiState 
) { 
    ComposeTestTheme { 
        FeedScreen(state = state, onLikeClick = {}) 
    } 
}
```

This setup allows developers to visually confirm that every state of the UI looks correct without needing to run the app and manually reproduce each scenario.

## 🏗️ Project Structure
*   **UI Components & Screens**: Located in `src/main/java/com/sjhawar11/composetest/ui/`.
*   **Preview Helpers**: Custom annotations and providers are in `src/main/java/com/sjhawar11/composetest/ui/previews/`.
*   **Models**: Data classes are in `src/main/java/com/sjhawar11/composetest/model/`.
*   **Tests**:
    *   **Local Unit Tests**: `src/test/java/`.
    *   **Instrumented Tests**: `src/androidTest/java/`.
