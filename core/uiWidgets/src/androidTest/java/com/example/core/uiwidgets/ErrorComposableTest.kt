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
    fun errorComposableTest_errorTextVisibility() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText)
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
    }

    @Test
    fun errorComposableTest_retryButtonVisibility_withLabelOnly() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText, retryButtonLabel = btnLabel)
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
        composeTestRule.onNodeWithText(btnLabel).assertDoesNotExist()
    }

    @Test
    fun errorComposableTest_retryButtonVisibility_withClickHandlerOnly() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText, onRetry = {})
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
        composeTestRule.onNodeWithText(btnLabel).assertDoesNotExist()
    }

    @Test
    fun errorComposableTest_retryButtonVisibility_withLabelAndClickHandler() {
        composeTestRule.setContent {
            ErrorComposable(errorText = errorText, retryButtonLabel = btnLabel, onRetry = {})
        }
        composeTestRule.onNodeWithText(errorText).assertExists()
        composeTestRule.onNodeWithText(btnLabel).assertExists()
    }
}