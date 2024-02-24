package com.example.feature.bikenetwork.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
import com.example.feature.bikenetwork.presentation.R
import com.example.feature.bikenetwork.presentation.fake.FakeBikeNetworkUseCase
import com.example.feature.bikenetwork.presentation.list.state.ListState
import com.example.feature.bikenetwork.presentation.list.ui.BikeNetworkListScreen
import com.example.feature.bikenetwork.presentation.list.viewmodel.BikeNetworkListViewModel
import com.example.feature.bikenetwork.presentation.testData.errorText
import com.example.feature.bikenetwork.presentation.testData.networks
import org.junit.Rule
import org.junit.Test

class BikeNetworkListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)

    private val useCase: IBikeNetworkUseCase = FakeBikeNetworkUseCase()
    private val viewModel: BikeNetworkListViewModel = BikeNetworkListViewModel(useCase)

    @Test
    fun bikeNetworksListScreen_screenTitle_Visibility() {
        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.app_name))
    }

    @Test
    fun bikeNetworksListScreen_Idle() {
        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.Idle

        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_idle),
            useUnmergedTree = true
        )
            .assertExists()

    }

    @Test
    fun bikeNetworksListScreen_loader_displayed() {
        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.Loading
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_loader),
            useUnmergedTree = true
        )
            .assertExists()

    }

    @Test
    fun bikeNetworksListScreen_networkList_NotLoaded() {

        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.DataNotFound
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_noData),
            useUnmergedTree = true
        ).assertExists()
    }

    @Test
    fun bikeNetworksListScreen_networkList_Loaded() {

        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.BikeNetworks(
            networks = networks
        )

        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_networkList),
            useUnmergedTree = true
        )
            .assertExists()
    }

    @Test
    fun bikeNetworksListScreen_errorDisplayed() {

        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.Error(errorText)
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_error),
            useUnmergedTree = true
        ).assertExists()
    }
}