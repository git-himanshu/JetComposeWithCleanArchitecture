package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.backContentDescription
import com.example.core.uiwidgets.Constants.screenTitle
import org.junit.Rule
import org.junit.Test

class CustomTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun customTopAppBar_screen_title_is_available() {
        composeTestRule.setContent {
            CustomTopAppBar(title = screenTitle)
        }
        composeTestRule.onNodeWithText(screenTitle).assertExists()
    }

    @Test
    fun customTopAppBar_screen_back_button_is_not_available() {
        composeTestRule.setContent {
            CustomTopAppBar(
                title = screenTitle,
                backContentDescription = backContentDescription
            )
        }
        composeTestRule.onNodeWithText(screenTitle).assertExists()
        composeTestRule.onNodeWithContentDescription(backContentDescription).assertDoesNotExist()
    }

    @Test
    fun customTopAppBar_screen_back_button_is_available() {
        composeTestRule.setContent {
            CustomTopAppBar(
                title = screenTitle,
                backContentDescription = backContentDescription,
                onBack = {}
            )
        }
        composeTestRule.onNodeWithText(screenTitle).assertExists()
        composeTestRule.onNodeWithContentDescription(backContentDescription).assertExists()
    }
}