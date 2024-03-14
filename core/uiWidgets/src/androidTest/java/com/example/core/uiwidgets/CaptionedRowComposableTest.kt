package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.CAPTION
import com.example.core.uiwidgets.Constants.VALUE
import org.junit.Rule
import org.junit.Test

class CaptionedRowComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun captionedRowComposable_caption_is_displayed() {
        composeTestRule.setContent {
            CaptionedRowComposable(caption = CAPTION, value = VALUE)
        }
        composeTestRule.onNodeWithText(CAPTION).assertExists()
    }

    @Test
    fun captionedRowComposable_value_is_displayed() {
        composeTestRule.setContent {
            CaptionedRowComposable(caption = CAPTION, value = VALUE)
        }
        composeTestRule.onNodeWithText(VALUE).assertExists()
    }
}