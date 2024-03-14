package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiWidgets.R
import com.example.core.uiwidgets.Constants.ICON_CONTENT_DESCRIPTION
import com.example.core.uiwidgets.Constants.INFO_TEXT
import com.example.core.uiwidgets.Constants.RETRY_BUTTON_LABEL
import org.junit.Rule
import org.junit.Test

class NoDataComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun noDataComposable_info_and_retry_button_is_displayed() {
        composeTestRule.setContent {
            NoDataComposable(
                    infoText = INFO_TEXT,
                    onRetry = {},
                    retryButtonLabel = RETRY_BUTTON_LABEL,
                    iconContentDescription = ICON_CONTENT_DESCRIPTION,
            )
        }
        composeTestRule.onNodeWithText(INFO_TEXT).assertExists()
        composeTestRule.onNodeWithText(RETRY_BUTTON_LABEL).assertExists()
        composeTestRule.onNodeWithContentDescription(ICON_CONTENT_DESCRIPTION).assertDoesNotExist()
    }

    @Test
    fun noDataComposable_icon_and_retry_button_is_displayed() {
        composeTestRule.setContent {
            NoDataComposable(
                    onRetry = {},
                    retryButtonLabel = RETRY_BUTTON_LABEL,
                    iconContentDescription = ICON_CONTENT_DESCRIPTION,
                    noDataDrawable = R.drawable.no_data
            )
        }
        composeTestRule.onNodeWithText(INFO_TEXT).assertDoesNotExist()
        composeTestRule.onNodeWithText(RETRY_BUTTON_LABEL).assertExists()
        composeTestRule.onNodeWithContentDescription(ICON_CONTENT_DESCRIPTION).assertExists()
    }

    @Test
    fun noDataComposable_icon_info_text_and_retry_button_is_displayed() {
        composeTestRule.setContent {
            NoDataComposable(
                    infoText = INFO_TEXT,
                    onRetry = {},
                    retryButtonLabel = RETRY_BUTTON_LABEL,
                    iconContentDescription = ICON_CONTENT_DESCRIPTION,
                    noDataDrawable = R.drawable.no_data
            )
        }
        composeTestRule.onNodeWithText(INFO_TEXT).assertExists()
        composeTestRule.onNodeWithText(RETRY_BUTTON_LABEL).assertExists()
        composeTestRule.onNodeWithContentDescription(ICON_CONTENT_DESCRIPTION).assertExists()
    }
}