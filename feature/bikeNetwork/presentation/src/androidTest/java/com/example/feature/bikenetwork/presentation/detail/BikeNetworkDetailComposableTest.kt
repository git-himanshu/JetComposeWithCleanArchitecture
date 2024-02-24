package com.example.feature.bikenetwork.presentation.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.feature.bikenetwork.presentation.R
import com.example.feature.bikenetwork.presentation.detail.ui.BikeNetworkDetailComposable
import com.example.feature.bikenetwork.presentation.testData.networkEntity
import com.example.feature.bikenetwork.presentation.testData.stations
import org.junit.Rule
import org.junit.Test

class BikeNetworkDetailComposableTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)


    @Test
    fun bikeNetworkDetail_NetworkDetailVisibility() {
        composeTestRule.setContent {
            BikeNetworkDetailComposable(
                bikeNetworkDetail = networkEntity
            )
        }

        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.network_name)) and
                    hasAnySibling(hasText(networkEntity.name))
        ).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.location)) and
                    hasAnySibling(hasText("${networkEntity.city}, ${networkEntity.country}"))
        ).assertExists()
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.all_stations))
        ).assertExists()

    }

    @Test
    fun bikeNetworkDetail_StationsListVisibility() {
        composeTestRule.setContent {
            BikeNetworkDetailComposable(
                bikeNetworkDetail = networkEntity
            )
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