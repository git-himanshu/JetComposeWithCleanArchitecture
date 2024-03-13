package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiWidgets.R
import com.example.core.uiwidgets.Constants.iconContentDescription
import com.example.core.uiwidgets.Constants.infoText
import com.example.core.uiwidgets.Constants.retryBtnLabel
import org.junit.Rule
import org.junit.Test

class NoDataComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun noDataComposable_info_and_retry_button_is_displayed() {
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
    fun noDataComposable_icon_and_retry_button_is_displayed() {
        composeTestRule.setContent {
            NoDataComposable(
                onRetry = {},
                retryButtonLabel = retryBtnLabel,
                iconContentDescription = iconContentDescription,
                noDataDrawable = R.drawable.no_data
            )
        }
        composeTestRule.onNodeWithText(infoText).assertDoesNotExist()
        composeTestRule.onNodeWithText(retryBtnLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(iconContentDescription).assertExists()
    }

    @Test
    fun noDataComposable_icon_info_text_and_retry_button_is_displayed() {
        composeTestRule.setContent {
            NoDataComposable(
                infoText = infoText,
                onRetry = {},
                retryButtonLabel = retryBtnLabel,
                iconContentDescription = iconContentDescription,
                noDataDrawable = R.drawable.no_data
            )
        }
        composeTestRule.onNodeWithText(infoText).assertExists()
        composeTestRule.onNodeWithText(retryBtnLabel).assertExists()
        composeTestRule.onNodeWithContentDescription(iconContentDescription).assertExists()
    }
}