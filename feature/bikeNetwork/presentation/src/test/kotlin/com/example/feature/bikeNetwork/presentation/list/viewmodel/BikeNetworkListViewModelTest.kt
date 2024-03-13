package com.example.feature.bikeNetwork.presentation.list.viewmodel

import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.list.intent.ListIntent
import com.example.feature.bikeNetwork.presentation.list.state.ListState
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.bikeNetworkEntity
import com.example.testing.testData.bikeNetworkEntityWithEmptyList
import com.example.testing.testData.genericError
import com.example.testing.testData.serverError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BikeNetworkListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var useCase: IBikeNetworkUseCase

    private lateinit var viewModel: BikeNetworkListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkListViewModel_bike_network_list_is_success_with_data() = runTest {
        coEvery { useCase.getList() } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = bikeNetworkEntity,
                    error = null,
                    message = null
                )
            )
        }
        viewModel = BikeNetworkListViewModel(useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        advanceUntilIdle()
        assert(state is ListState.BikeNetworks)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkListViewModel_bike_network_list_is_success_without_data() = runTest {
        coEvery { useCase.getList() } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = bikeNetworkEntityWithEmptyList,
                    error = null,
                    message = null
                )
            )
        }
        viewModel = BikeNetworkListViewModel(useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        advanceUntilIdle()
        assert(state is ListState.DataNotFound)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkListViewModel_error_in_getting_bike_network_list() = runTest {
        coEvery { useCase.getList() } returns flow {
            emit(
                Result(
                    status = Result.Status.ERROR,
                    data = null,
                    error = Error(statusCode = 500, statusMessage = serverError),
                    message = genericError
                )
            )
        }
        viewModel = BikeNetworkListViewModel(useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        advanceUntilIdle()
        assert(state is ListState.Error)
    }
}



