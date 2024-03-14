package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.core.uiwidgets.Constants.BACKGROUND_CONTENT_DESCRIPTION
import com.example.core.uiwidgets.Constants.SCREEN_TITLE
import org.junit.Rule
import org.junit.Test

class CustomTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun customTopAppBar_screen_title_is_available() {
        composeTestRule.setContent {
            CustomTopAppBar(title = SCREEN_TITLE)
        }
        composeTestRule.onNodeWithText(SCREEN_TITLE).assertExists()
    }

    @Test
    fun customTopAppBar_screen_back_button_is_not_available() {
        composeTestRule.setContent {
            CustomTopAppBar(
                    title = SCREEN_TITLE,
                    backContentDescription = BACKGROUND_CONTENT_DESCRIPTION
            )
        }
        composeTestRule.onNodeWithText(SCREEN_TITLE).assertExists()
        composeTestRule.onNodeWithContentDescription(BACKGROUND_CONTENT_DESCRIPTION)
                .assertDoesNotExist()
    }

    @Test
    fun customTopAppBar_screen_back_button_is_available() {
        composeTestRule.setContent {
            CustomTopAppBar(
                    title = SCREEN_TITLE,
                    backContentDescription = BACKGROUND_CONTENT_DESCRIPTION,
                    onBack = {}
            )
        }
        composeTestRule.onNodeWithText(SCREEN_TITLE).assertExists()
        composeTestRule.onNodeWithContentDescription(BACKGROUND_CONTENT_DESCRIPTION).assertExists()
    }
}