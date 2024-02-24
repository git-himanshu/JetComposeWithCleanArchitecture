package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CaptionedRowComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val caption = "Caption"
    private val value = "Value"

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