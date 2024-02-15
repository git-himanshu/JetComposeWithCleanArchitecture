package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkDetailEntity
import com.example.jetcomposewithcleanarchitecture.domain.usecase.IBikeNetworkUseCase
import com.example.jetcomposewithcleanarchitecture.model.Result
import com.example.jetcomposewithcleanarchitecture.presentation.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bikeNetworkUseCase: IBikeNetworkUseCase,
) : ViewModel() {
    val networkId: String = savedStateHandle.get<String>("network_id")!!

    private val _bikeNetworkDetail =
        MutableStateFlow<BikeNetworkDetailEntity?>(BikeNetworkDetailEntity(id = ""))
    val bikeNetworkDetail: StateFlow<BikeNetworkDetailEntity?> = _bikeNetworkDetail.asStateFlow()

    private val _uiState = MutableStateFlow(UIState.LOADING)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>("")
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchBikeNetworkList(networkId)
    }

    fun onRetry() {
        fetchBikeNetworkList(networkId)
    }

    private fun fetchBikeNetworkList(networkId: String) {
        viewModelScope.launch {
            bikeNetworkUseCase.getDetail(networkId).collect {
                when (it?.status) {
                    Result.Status.LOADING -> {
                        _uiState.value = UIState.LOADING
                    }

                    Result.Status.SUCCESS -> {
                        _uiState.value = UIState.SUCCESS
                        _bikeNetworkDetail.value = it.data
                    }

                    Result.Status.ERROR -> {
                        _uiState.value = UIState.ERROR
                        _errorMessage.value = it.error?.status_message
                    }

                    else -> {
                        _uiState.value = UIState.ERROR
                        _errorMessage.value = "Something went wrong!!"
                    }
                }
            }
        }
    }
}