package com.example.feature.bikeNetwork.presentation.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.R
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.feature.bikeNetwork.presentation.detail.ui.BikeNetworkDetailScreen
import com.example.feature.bikeNetwork.presentation.detail.viewmodel.BikeNetworkDetailViewModel
import com.example.feature.bikeNetwork.presentation.navigation.BikeNetworkDetails.NETWORK_ID_ARG
import com.example.feature.bikeNetwork.presentation.previewData.NETWORK_ID
import com.example.testing.testData.ERROR_TEST
import com.example.testing.testData.networkDetailEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BikeNetworkDetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(ComponentActivity::class.java)

    @MockK
    lateinit var useCase: IBikeNetworkUseCase

    private lateinit var viewModel: BikeNetworkDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { useCase.getDetail(any()) } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = null,
                    error = null,
                    message = null
                )
            )
        }
        viewModel =
            BikeNetworkDetailViewModel(savedStateHandle = SavedStateHandle().apply {
                set(
                    NETWORK_ID_ARG,
                    NETWORK_ID
                )
            }, bikeNetworkUseCase = useCase)
    }

    @Test
    fun bikeNetworksDetailScreen_screen_title_is_displayed() {
        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.bike_network_detail_screen_name))
    }

    @Test
    fun bikeNetworksListScreen_idle_screen_is_displayed() {
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
    fun bikeNetworksListScreen_loader_is_displayed() {
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
    fun bikeNetworksListScreen_network_list_is_not_loaded() {

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
    fun bikeNetworksListScreen_network_list_is_displayed() {

        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.NetworkDetail(networkDetailEntity)

        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_networkDetail),
            useUnmergedTree = true
        )
            .assertExists()
    }

    @Test
    fun bikeNetworksListScreen_error_is_displayed() {

        composeTestRule.setContent {
            BikeNetworkDetailScreen(onBack = {}, viewModel = viewModel)
        }
        viewModel.state.value = DetailState.Error(ERROR_TEST)
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.testTag_error),
            useUnmergedTree = true
        ).assertExists()
    }

}