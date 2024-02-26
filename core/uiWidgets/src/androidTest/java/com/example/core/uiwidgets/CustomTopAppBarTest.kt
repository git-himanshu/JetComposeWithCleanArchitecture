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
    fun customTopAppBar_verifyScreenTitle() {
        composeTestRule.setContent {
            CustomTopAppBar(title = screenTitle)
        }
        composeTestRule.onNodeWithText(screenTitle).assertExists()
    }

    @Test
    fun customTopAppBar_verifyScreenBackButton_NotExist() {
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
    fun customTopAppBar_verifyScreenBackButton_Exist() {
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