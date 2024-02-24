package com.example.feature.bikenetwork.presentation.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.feature.bikenetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikenetwork.presentation.detail.state.DetailState
import com.example.feature.bikenetwork.presentation.dispatcherRule.MainDispatcherRule
import com.example.feature.bikenetwork.presentation.fake.FakeBikeNetworkUseCase
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
    private val networkId = "network_id"
    private val networkIdValue = "1"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkDetail_dataLoaded_Success_withData() = runTest() {
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
    fun bikeNetworkDetail_dataLoaded_Success_withoutData() = runTest() {
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
    fun bikeNetworkDetail_dataLoaded_error() = runTest() {
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