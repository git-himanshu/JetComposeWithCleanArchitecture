package com.example.presentation.list.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.model.Result
import com.example.domain.usecase.BikeNetworkListUseCase
import com.example.presentation.list.intent.ListIntent
import com.example.presentation.list.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkListViewModel @Inject constructor(
    private val bikeNetworkListUseCase: BikeNetworkListUseCase,
) : ViewModel() {

    val userIntent = Channel<ListIntent>(Channel.UNLIMITED)

    val state = mutableStateOf<ListState>(ListState.Loading)

    init {
        handleIntent()
        fetchBikeNetworkList()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is ListIntent.FetchBikeNetworks -> fetchBikeNetworkList()
                }
            }
        }
    }


    private fun fetchBikeNetworkList() {
        viewModelScope.launch {
            state.value = ListState.Loading
            bikeNetworkListUseCase.getList().collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        val networks = it.data?.networks ?: emptyList()
                        if (networks.isEmpty()) {
                            state.value = ListState.DataNotFound
                        } else {
                            state.value = ListState.BikeNetworks(networks)
                        }
                    }

                    Result.Status.ERROR -> {
                        state.value = ListState.Error(it.error?.statusMessage ?: "")
                    }
                }
            }
        }
    }
}