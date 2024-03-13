package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.btnLabel
import com.example.core.uiwidgets.Constants.errorText
import org.junit.Rule
import org.junit.Test

class ErrorComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun errorComposable_error_text_is_displayed() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText)
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
    }

    @Test
    fun errorComposable_retry_button_is_displayed_with_label_only() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText, retryButtonLabel = btnLabel)
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
        composeTestRule.onNodeWithText(btnLabel).assertDoesNotExist()
    }

    @Test
    fun errorComposable_retry_button_is_displayed_with_click_handler_only() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText, onRetry = {})
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
        composeTestRule.onNodeWithText(btnLabel).assertDoesNotExist()
    }

    @Test
    fun errorComposable_retry_button_is_displayed_with_label_and_click_handler() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText, retryButtonLabel = btnLabel, onRetry = {})
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
        composeTestRule.onNodeWithText(btnLabel).assertExists()
    }
}