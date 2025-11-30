package com.sjhawar11.composetest.ui.previews

import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration

// The Matrix Annotation from your blog
@Preview(name = "Pixel 5 - Light", device = "id:pixel_5", showBackground = true)
@Preview(name = "Pixel 5 - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Font Scale 1.5x", fontScale = 1.5f)
annotation class DevicePreviews