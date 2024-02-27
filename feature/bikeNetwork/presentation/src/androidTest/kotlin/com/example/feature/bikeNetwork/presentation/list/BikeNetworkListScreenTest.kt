package com.example.feature.bikeNetwork.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.testing.testData.errorText
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.R
import com.example.feature.bikeNetwork.presentation.list.state.ListState
import com.example.feature.bikeNetwork.presentation.list.ui.BikeNetworkListScreen
import com.example.feature.bikeNetwork.presentation.list.viewmodel.BikeNetworkListViewModel
import com.example.testing.testData.networkList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BikeNetworkListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)

    @MockK
    lateinit var useCase: IBikeNetworkUseCase

    private lateinit var viewModel: BikeNetworkListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { useCase.getList() } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = null,
                    error = null,
                    message = null
                )
            )
        }
        viewModel = BikeNetworkListViewModel(useCase)
    }

    @Test
    fun bikeNetworksListScreen_screenTitle_visibility() {
        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.app_name))
    }

    @Test
    fun bikeNetworksListScreen_idle() {
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
    fun bikeNetworksListScreen_networkList_notLoaded() {

        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.DataNotFound
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_noData),
            useUnmergedTree = true
        ).assertExists()
    }

    @Test
    fun bikeNetworksListScreen_networkList_loaded() {

        composeTestRule.setContent {
            BikeNetworkListScreen(onItemClick = {}, viewModel = viewModel)
        }
        viewModel.state.value = ListState.BikeNetworks(
            networks = networkList
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