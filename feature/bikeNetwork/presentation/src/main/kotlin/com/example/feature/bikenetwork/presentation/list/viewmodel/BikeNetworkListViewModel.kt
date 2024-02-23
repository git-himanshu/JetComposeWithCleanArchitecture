package com.example.feature.bikenetwork.presentation.list.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import com.example.feature.bikenetwork.presentation.list.intent.ListIntent
import com.example.feature.bikenetwork.presentation.list.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkListViewModel @Inject constructor(
    private val bikeNetworkUseCase: IBikeNetworkUseCase,
) : ViewModel() {

    val userIntent = Channel<ListIntent>(Channel.UNLIMITED)

    val state = mutableStateOf<ListState>(ListState.Loading)

    init {
        handleIntent()
        fetchBikeNetworkList()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect() { intent ->
                when (intent) {
                    is ListIntent.FetchBikeNetworks -> fetchBikeNetworkList()
                }
            }
        }
    }


    private fun fetchBikeNetworkList() {
        viewModelScope.launch {
            state.value = ListState.Loading
            bikeNetworkUseCase.getList().collect {
                when (it.status) {
                    Result.Status.SUCCESS -> {
                        if (it.data?.networks?.isNotEmpty() == true) {
                            state.value = ListState.BikeNetworks(it.data?.networks!!)
                        } else {
                            state.value = ListState.DataNotFound
                        }
                    }

                    Result.Status.ERROR -> {
                        state.value = ListState.Error(it.error?.status_message ?: "")
                    }
                }
            }
        }
    }
}