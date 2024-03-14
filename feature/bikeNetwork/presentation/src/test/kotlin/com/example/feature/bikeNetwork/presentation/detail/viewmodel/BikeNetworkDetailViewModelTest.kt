package com.example.feature.bikeNetwork.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.testing.dispatcherRule.MainDispatcherRule
import com.example.testing.testData.GENERIC_ERROR
import com.example.testing.testData.NETWORK_ID
import com.example.testing.testData.NETWORK_ID_VALUE
import com.example.testing.testData.SERVER_ERROR
import com.example.testing.testData.networkDetailEntity
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

class BikeNetworkDetailViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var useCase: IBikeNetworkUseCase

    private lateinit var viewModel: BikeNetworkDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetailViewModel_bike_network_detail_is_success_with_data() = runTest {
        coEvery { useCase.getDetail(any()) } returns flow {
            emit(
                Result(
                    status = Result.Status.SUCCESS,
                    data = networkDetailEntity,
                    error = null,
                    message = null
                )
            )
        }
        viewModel = BikeNetworkDetailViewModel(savedStateHandle = SavedStateHandle().apply {
            set(
                NETWORK_ID,
                NETWORK_ID_VALUE
            )
        }, bikeNetworkUseCase = useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        advanceUntilIdle()
        assert(state is DetailState.NetworkDetail)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetailViewModel_bike_network_detail_is_success_without_data() = runTest {
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
        viewModel = BikeNetworkDetailViewModel(savedStateHandle = SavedStateHandle().apply {
            set(
                NETWORK_ID,
                NETWORK_ID_VALUE
            )
        }, bikeNetworkUseCase = useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        advanceUntilIdle()
        assert(state is DetailState.DataNotFound)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetailViewModel_error_in_getting_bike_network_detail() = runTest {
        coEvery { useCase.getDetail(any()) } returns flow {
            emit(
                Result(
                    status = Result.Status.ERROR,
                    data = null,
                    error = Error(statusCode = 500, statusMessage = SERVER_ERROR),
                    message = GENERIC_ERROR
                )
            )
        }
        viewModel = BikeNetworkDetailViewModel(savedStateHandle = SavedStateHandle().apply {
            set(
                NETWORK_ID,
                NETWORK_ID_VALUE
            )
        }, bikeNetworkUseCase = useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        advanceUntilIdle()
        assert(state is DetailState.Error)
    }
}