package com.example.feature.bikeNetwork.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.feature.bikeNetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.feature.bikeNetwork.presentation.dispatcherRule.MainDispatcherRule
import com.example.feature.bikeNetwork.presentation.fake.FakeBikeNetworkUseCase
import com.example.feature.bikeNetwork.presentation.testData.networkId
import com.example.feature.bikeNetwork.presentation.testData.networkIdValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BikeNetworkDetailViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: BikeNetworkDetailViewModel


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetail_dataLoaded_Success_withData() = runTest {
        val useCase = FakeBikeNetworkUseCase(FakeBikeNetworkUseCase.ResponseType.SUCCESS_WITH_DATA)
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
    fun bikeNetworkDetail_dataLoaded_Success_withoutData() = runTest {
        val useCase =
            FakeBikeNetworkUseCase(FakeBikeNetworkUseCase.ResponseType.SUCCESS_WITHOUT_DATA)
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
        val useCase = FakeBikeNetworkUseCase(FakeBikeNetworkUseCase.ResponseType.ERROR)
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