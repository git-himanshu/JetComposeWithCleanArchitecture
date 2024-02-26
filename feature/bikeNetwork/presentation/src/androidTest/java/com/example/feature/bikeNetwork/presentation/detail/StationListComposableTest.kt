package com.example.feature.bikeNetwork.presentation.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.feature.bikeNetwork.presentation.R
import com.example.feature.bikeNetwork.presentation.detail.ui.StationListComposable
import com.example.feature.bikeNetwork.presentation.detail.ui.testData.stations
import org.junit.Rule
import org.junit.Test

class StationListComposableTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)

    @Test
    fun stationListTest_listItemsVisibility() {

        composeTestRule.setContent {
            StationListComposable(stationList = stations)
        }

        composeTestRule.onNodeWithText(stations.first().name).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.total_slots)) and
                    hasAnySibling(hasText(stations.first().slots.toString()))
        ).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.available_slots)) and
                    hasAnySibling(hasText(stations.first().emptySlot.toString()))
        ).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.free_bikes)) and
                    hasAnySibling(hasText(stations.first().freeBikes.toString()))
        ).assertExists()

        composeTestRule.onNodeWithText(stations.last().name).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.total_slots)) and
                    hasAnySibling(hasText(stations.last().slots.toString()))
        ).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.available_slots)) and
                    hasAnySibling(hasText(stations.last().emptySlot.toString()))
        ).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.free_bikes)) and
                    hasAnySibling(hasText(stations.last().freeBikes.toString()))
        ).assertExists()

    }
}