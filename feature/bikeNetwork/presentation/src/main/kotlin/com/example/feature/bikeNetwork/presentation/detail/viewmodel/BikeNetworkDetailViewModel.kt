package com.example.feature.bikeNetwork.presentation.detail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bikeNetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import com.example.feature.bikeNetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.feature.bikeNetwork.presentation.navigation.BikeNetworkDetails.networkIDArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bikeNetworkUseCase: IBikeNetworkUseCase,
) : ViewModel() {
    private val networkId: String = savedStateHandle.get<String>(networkIDArg)!!

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
            bikeNetworkUseCase.getDetail(networkId).collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        if (it.data == null) {
                            state.value = DetailState.DataNotFound
                        } else {
                            state.value = DetailState.NetworkDetail(it.data!!)
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