package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.BUTTON_LABEL
import com.example.core.uiwidgets.Constants.ERROR_TEXT
import org.junit.Rule
import org.junit.Test

class ErrorComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun errorComposable_error_text_is_displayed() {
        composeTestRule.setContent {
            ErrorComposable(errorText = ERROR_TEXT)
        }
        composeTestRule.onNodeWithText(ERROR_TEXT).assertExists()
    }

    @Test
    fun errorComposable_retry_button_is_displayed_with_label_only() {
        composeTestRule.setContent {
            ErrorComposable(errorText = ERROR_TEXT, retryButtonLabel = BUTTON_LABEL)
        }
        composeTestRule.onNodeWithText(ERROR_TEXT).assertExists()
        composeTestRule.onNodeWithText(BUTTON_LABEL).assertDoesNotExist()
    }

    @Test
    fun errorComposable_retry_button_is_displayed_with_click_handler_only() {
        composeTestRule.setContent {
            ErrorComposable(errorText = ERROR_TEXT, onRetry = {})
        }
        composeTestRule.onNodeWithText(ERROR_TEXT).assertExists()
        composeTestRule.onNodeWithText(BUTTON_LABEL).assertDoesNotExist()
    }

    @Test
    fun errorComposable_retry_button_is_displayed_with_label_and_click_handler() {
        composeTestRule.setContent {
            ErrorComposable(errorText = ERROR_TEXT, retryButtonLabel = BUTTON_LABEL, onRetry = {})
        }
        composeTestRule.onNodeWithText(ERROR_TEXT).assertExists()
        composeTestRule.onNodeWithText(BUTTON_LABEL).assertExists()
    }
}