package com.example.feature.bikeNetwork.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.feature.bikeNetwork.presentation.list.ui.BikeNetworkList
import com.example.testing.testData.firstCity
import com.example.testing.testData.firstCountry
import com.example.testing.testData.firstName
import com.example.testing.testData.networkList
import com.example.testing.testData.secondCity
import com.example.testing.testData.secondCountry
import com.example.testing.testData.secondName
import org.junit.Rule
import org.junit.Test

class BikeNetworkListTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)


    @Test
    fun networkList_list_items_are_visible() {

        composeTestRule.setContent {
            BikeNetworkList(bikeNetworkList = networkList, onItemClick = {})
        }

        composeTestRule.onNodeWithText(firstName).assertExists()
        composeTestRule.onNodeWithText("$firstCity, $firstCountry").assertExists()

        composeTestRule.onNodeWithText(secondName).assertExists()
        composeTestRule.onNodeWithText("$secondCity, $secondCountry").assertExists()

    }

    @Test
    fun networkList_list_items_are_clickable() {
        var rowClicked = false
        composeTestRule.setContent {
            BikeNetworkList(bikeNetworkList = networkList, onItemClick = {
                rowClicked = true
            })
        }
        composeTestRule.onNode(hasText(firstName), useUnmergedTree = true).run {
            assertIsDisplayed()
            performClick()
        }
        assert(rowClicked)
    }
}