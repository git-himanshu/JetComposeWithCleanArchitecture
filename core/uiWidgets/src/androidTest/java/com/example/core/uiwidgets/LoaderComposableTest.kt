package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.core.uiwidgets.Constants.testingTag
import org.junit.Rule
import org.junit.Test

class LoaderComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun loaderComposable_is_displayed() {
        composeTestRule.setContent {
            LoaderComposable(testTag = testingTag)
        }
        composeTestRule.onNodeWithTag(testingTag).assertExists()
    }
}