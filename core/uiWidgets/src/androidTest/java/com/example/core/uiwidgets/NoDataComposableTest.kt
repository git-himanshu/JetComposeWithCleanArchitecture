package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.iconContentDescription
import com.example.core.uiwidgets.Constants.infoText
import com.example.core.uiwidgets.Constants.retryBtnLabel
import org.junit.Rule
import org.junit.Test

class NoDataComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun noDataComposableTest_infoAndRetryButtonVisible() {
        composeTestRule.setContent {
            NoDataComposable(
                infoText = infoText,
                onRetry = {},
                retryButtonLabel = retryBtnLabel,
                iconContentDescription = iconContentDescription,
            )
        }
        composeTestRule.onNodeWithText(infoText).assertExists()
        composeTestRule.onNodeWithText(retryBtnLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(iconContentDescription).assertDoesNotExist()
    }

    @Test
    fun noDataComposableTest_iconAndRetryButtonVisible() {
        composeTestRule.setContent {
            NoDataComposable(
                onRetry = {},
                retryButtonLabel = retryBtnLabel,
                iconContentDescription = iconContentDescription,
                noDataDrawable = androidx.core.R.drawable.ic_call_answer
            )
        }
        composeTestRule.onNodeWithText(infoText).assertDoesNotExist()
        composeTestRule.onNodeWithText(retryBtnLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(iconContentDescription).assertExists()
    }

    @Test
    fun noDataComposableTest_iconInfoTextAndRetryButtonVisible() {
        composeTestRule.setContent {
            NoDataComposable(
                infoText = infoText,
                onRetry = {},
                retryButtonLabel = retryBtnLabel,
                iconContentDescription = iconContentDescription,
                noDataDrawable = androidx.core.R.drawable.ic_call_answer
            )
        }
        composeTestRule.onNodeWithText(infoText).assertExists()
        composeTestRule.onNodeWithText(retryBtnLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(iconContentDescription).assertExists()
    }
}