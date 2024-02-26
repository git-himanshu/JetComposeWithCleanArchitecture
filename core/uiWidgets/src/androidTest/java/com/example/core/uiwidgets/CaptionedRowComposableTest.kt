package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.caption
import com.example.core.uiwidgets.Constants.value
import org.junit.Rule
import org.junit.Test

class CaptionedRowComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun captionedRowComposableTest_captionIsVisible() {
        composeTestRule.setContent {
            CaptionedRowComposable(caption = caption, value = value)
        }
        composeTestRule.onNodeWithText(caption).assertExists()
    }

    @Test
    fun captionedRowComposableTest_valueIsVisible() {
        composeTestRule.setContent {
            CaptionedRowComposable(caption = caption, value = value)
        }
        composeTestRule.onNodeWithText(value).assertExists()
    }
}