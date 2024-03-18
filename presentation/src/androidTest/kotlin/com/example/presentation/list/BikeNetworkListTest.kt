package com.example.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.presentation.list.ui.BikeNetworkList
import com.example.testing.testData.NETWORK_CITY
import com.example.testing.testData.NETWORK_COUNTRY
import com.example.testing.testData.NETWORK_NAME
import com.example.testing.testData.SECOND_CITY
import com.example.testing.testData.SECOND_COUNTRY
import com.example.testing.testData.SECOND_NAME
import com.example.testing.testData.createNetworkList
import org.junit.Rule
import org.junit.Test

class BikeNetworkListTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)


    @Test
    fun networkList_list_items_are_visible() {

        composeTestRule.setContent {
            BikeNetworkList(bikeNetworkList = createNetworkList(), onItemClick = {})
        }

        composeTestRule.onNodeWithText(NETWORK_NAME).assertExists()
        composeTestRule.onNodeWithText("$NETWORK_CITY, $NETWORK_COUNTRY").assertExists()

        composeTestRule.onNodeWithText(SECOND_NAME).assertExists()
        composeTestRule.onNodeWithText("$SECOND_CITY, $SECOND_COUNTRY").assertExists()

    }

    @Test
    fun networkList_list_items_are_clickable() {
        var rowClicked = false
        composeTestRule.setContent {
            BikeNetworkList(bikeNetworkList = createNetworkList(), onItemClick = {
                rowClicked = true
            })
        }
        composeTestRule.onNode(hasText(NETWORK_NAME), useUnmergedTree = true).run {
            assertIsDisplayed()
            performClick()
        }
        assert(rowClicked)
    }
}