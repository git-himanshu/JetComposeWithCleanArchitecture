package com.example.feature.bikenetwork.presentation.detail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import com.example.feature.bikenetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikenetwork.presentation.detail.state.DetailState
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
    val networkId: String = savedStateHandle.get<String>("network_id")!!

    val userIntent = Channel<DetailIntent>(Channel.UNLIMITED)
    val state = mutableStateOf<DetailState>(DetailState.Loading)

    init {
        handleIntent()
        fetchBikeNetworkDetail(networkId)
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect() { intent ->
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
                        state.value = DetailState.Error(it.error?.status_message ?: "")
                    }
                }
            }
        }
    }
}