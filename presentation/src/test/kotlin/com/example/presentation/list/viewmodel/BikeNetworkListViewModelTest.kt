package com.example.presentation.list.viewmodel

import com.example.common.model.Error
import com.example.common.model.Result
import com.example.domain.usecase.BikeNetworkListUseCase
import com.example.presentation.list.intent.ListIntent
import com.example.presentation.list.state.ListState
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.GENERIC_ERROR
import com.example.testing.testData.SERVER_ERROR
import com.example.testing.testData.createBikeNetworksEntity
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
    lateinit var useCase: BikeNetworkListUseCase

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
                    data = createBikeNetworksEntity(),
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
                    data = createBikeNetworksEntity(networks = emptyList()),
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
                    error = Error(statusCode = 500, statusMessage = SERVER_ERROR),
                    message = GENERIC_ERROR
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



