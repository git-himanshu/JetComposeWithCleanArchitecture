package com.example.core.uiwidgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class LoaderComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val testingTag = "Loader testing tag."

    @Test
    fun loaderComposableVisibility() {
        composeTestRule.setContent {
            LoaderComposable(testTag = testingTag)
        }
        composeTestRule.onNodeWithTag(testingTag).assertExists()
    }
}