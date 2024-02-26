package com.example.feature.bikeNetwork.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Error
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.feature.bikeNetwork.presentation.dispatcherRule.MainDispatcherRule
import com.example.feature.bikeNetwork.presentation.testData.genericError
import com.example.feature.bikeNetwork.presentation.testData.networkDetailEntity
import com.example.feature.bikeNetwork.presentation.testData.networkId
import com.example.feature.bikeNetwork.presentation.testData.networkIdValue
import com.example.feature.bikeNetwork.presentation.testData.serverError
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
    fun bikeNetworkDetail_dataLoaded_success_withData() = runTest {
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
                networkId,
                networkIdValue
            )
        }, bikeNetworkUseCase = useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        advanceUntilIdle()
        assert(state is DetailState.NetworkDetail)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetail_dataLoaded_success_withoutData() = runTest {
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
                networkId,
                networkIdValue
            )
        }, bikeNetworkUseCase = useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        advanceUntilIdle()
        assert(state is DetailState.DataNotFound)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetail_dataLoaded_error() = runTest {
        coEvery { useCase.getDetail(any()) } returns flow {
            emit(
                Result(
                    status = Result.Status.ERROR,
                    data = null,
                    error = Error(statusCode = 500, statusMessage = serverError),
                    message = genericError
                )
            )
        }
        viewModel = BikeNetworkDetailViewModel(savedStateHandle = SavedStateHandle().apply {
            set(
                networkId,
                networkIdValue
            )
        }, bikeNetworkUseCase = useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        advanceUntilIdle()
        assert(state is DetailState.Error)
    }
}