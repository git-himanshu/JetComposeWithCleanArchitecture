package com.example.feature.bikenetwork.presentation.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import com.example.bikenetwork.domain.entity.BikeNetworkDetailEntity
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
import com.example.feature.bikenetwork.presentation.R
import com.example.feature.bikenetwork.presentation.detail.state.DetailState
import com.example.feature.bikenetwork.presentation.detail.ui.BikeNetworkDetailScreen
import com.example.feature.bikenetwork.presentation.detail.viewmodel.BikeNetworkDetailViewModel
import com.example.feature.bikenetwork.presentation.fake.FakeBikeNetworkUseCase
import org.junit.Rule
import org.junit.Test

class BikeNetworkDetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)

    private val useCase: IBikeNetworkUseCase = FakeBikeNetworkUseCase()
    private val viewModel: BikeNetworkDetailViewModel =
        BikeNetworkDetailViewModel(savedStateHandle = SavedStateHandle().apply {
            set(
                "network_id",
                "1"
            )
        }, bikeNetworkUseCase = useCase)

    @Test
    fun bikeNetworksDetailScreen_screenTitle_Visibility() {
        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.bike_network_detail_screen_name))
    }

    @Test
    fun bikeNetworksListScreen_Idle() {
        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.Idle

        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_idle),
            useUnmergedTree = true
        )
            .assertExists()

    }

    @Test
    fun bikeNetworksListScreen_loader_displayed() {
        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.Loading
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_loader),
            useUnmergedTree = true
        )
            .assertExists()

    }

    @Test
    fun bikeNetworksListScreen_networkList_NotLoaded() {

        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.DataNotFound
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_noData),
            useUnmergedTree = true
        ).assertExists()
    }

    @Test
    fun bikeNetworksListScreen_networkList_Loaded() {

        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.NetworkDetail(BikeNetworkDetailEntity())

        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_networkDetail),
            useUnmergedTree = true
        )
            .assertExists()
    }

    @Test
    fun bikeNetworksListScreen_errorDisplayed() {

        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.Error("Something went wrong")
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_error),
            useUnmergedTree = true
        ).assertExists()
    }

}