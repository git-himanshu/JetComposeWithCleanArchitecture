package com.example.feature.bikeNetwork.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.firstCity
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.firstCountry
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.firstName
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.networks
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.secondCity
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.secondCountry
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.secondName
import com.example.feature.bikeNetwork.presentation.list.ui.BikeNetworkList
import org.junit.Rule
import org.junit.Test

class BikeNetworkListTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)


    @Test
    fun networkListTest_listItemsVisibility() {

        composeTestRule.setContent {
            BikeNetworkList(bikeNetworkList = networks, onItemClick = {})
        }

        composeTestRule.onNodeWithText(firstName).assertExists()
        composeTestRule.onNodeWithText("$firstCity, $firstCountry").assertExists()

        composeTestRule.onNodeWithText(secondName).assertExists()
        composeTestRule.onNodeWithText("$secondCity, $secondCountry").assertExists()

    }

    @Test
    fun networkListTest_listItemsClick() {
        var rowClicked = false
        composeTestRule.setContent {
            BikeNetworkList(bikeNetworkList = networks, onItemClick = {
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