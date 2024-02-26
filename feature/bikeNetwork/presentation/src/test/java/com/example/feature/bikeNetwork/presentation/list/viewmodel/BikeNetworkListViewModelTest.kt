package com.example.feature.bikeNetwork.presentation.list.viewmodel

import com.example.feature.bikeNetwork.presentation.dispatcherRule.MainDispatcherRule
import com.example.feature.bikeNetwork.presentation.fake.FakeBikeNetworkUseCase
import com.example.feature.bikeNetwork.presentation.list.intent.ListIntent
import com.example.feature.bikeNetwork.presentation.list.state.ListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BikeNetworkListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: BikeNetworkListViewModel


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkList_listLoaded_Success_WithData() = runTest {
        val useCase = FakeBikeNetworkUseCase(FakeBikeNetworkUseCase.ResponseType.SUCCESS_WITH_DATA)
        viewModel = BikeNetworkListViewModel(useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        advanceUntilIdle()
        assert(state is ListState.BikeNetworks)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkList_listLoaded_Success_WithoutData() = runTest {
        val useCase =
            FakeBikeNetworkUseCase(FakeBikeNetworkUseCase.ResponseType.SUCCESS_WITHOUT_DATA)
        viewModel = BikeNetworkListViewModel(useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        advanceUntilIdle()
        assert(state is ListState.DataNotFound)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun bikeNetworkList_listLoaded_Error() = runTest {
        val useCase = FakeBikeNetworkUseCase(FakeBikeNetworkUseCase.ResponseType.ERROR)
        viewModel = BikeNetworkListViewModel(useCase)
        val state = viewModel.state.value
        viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        advanceUntilIdle()
        assert(state is ListState.Error)
    }
}



