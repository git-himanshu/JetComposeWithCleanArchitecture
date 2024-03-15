package com.example.feature.bikeNetwork.presentation.detail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bikeNetwork.domain.usecase.BikeNetworkDetailUseCase
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.feature.bikeNetwork.presentation.navigation.BikeNetworkDetails.NETWORK_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bikeNetworkDetailUseCase: BikeNetworkDetailUseCase,
) : ViewModel() {
    private val networkId: String = savedStateHandle.get<String>(NETWORK_ID_ARG) ?: ""

    val userIntent = Channel<DetailIntent>(Channel.UNLIMITED)
    val state = mutableStateOf<DetailState>(DetailState.Loading)

    init {
        handleIntent()
        fetchBikeNetworkDetail(networkId)
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is DetailIntent.FetchNetworkDetail -> fetchBikeNetworkDetail(networkId)
                }
            }
        }
    }


    private fun fetchBikeNetworkDetail(networkId: String) {
        viewModelScope.launch {
            state.value = DetailState.Loading
            bikeNetworkDetailUseCase.getDetail(networkId).collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        val data = it.data
                        if (data != null) {
                            state.value = DetailState.NetworkDetail(data)
                        } else {
                            state.value = DetailState.DataNotFound
                        }
                    }

                    Result.Status.ERROR -> {
                        state.value = DetailState.Error(it.error?.statusMessage ?: "")
                    }
                }
            }
        }
    }
}