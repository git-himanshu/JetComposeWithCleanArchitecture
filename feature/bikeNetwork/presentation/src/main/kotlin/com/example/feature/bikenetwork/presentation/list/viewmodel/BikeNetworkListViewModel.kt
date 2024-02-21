package com.example.feature.bikenetwork.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bikenetwork.domain.entity.BikeNetworkEntity
import com.example.bikenetwork.domain.usecase.IBikeNetworkUseCase
import com.example.common.model.Result
import com.example.feature.bikenetwork.presentation.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkListViewModel @Inject constructor(
    private val bikeNetworkUseCase: IBikeNetworkUseCase,
) : ViewModel() {
    private val _bikeNetworkList = MutableStateFlow<List<BikeNetworkEntity>>(emptyList())
    val bikeNetworkList: StateFlow<List<BikeNetworkEntity>> = _bikeNetworkList.asStateFlow()

    private val _uiState = MutableStateFlow(UIState.LOADING)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>("")
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchBikeNetworkList()
    }

    fun onRetry() {
        fetchBikeNetworkList()
    }

    private fun fetchBikeNetworkList() {
        viewModelScope.launch {
            bikeNetworkUseCase.getList().collect {
                when (it?.status) {
                    Result.Status.LOADING -> {
                        _uiState.value = UIState.LOADING
                    }

                    Result.Status.SUCCESS -> {
                        if (it.data?.networks?.isNotEmpty() == true) {
                            _uiState.value = UIState.SUCCESS
                            _bikeNetworkList.value = it.data?.networks!!
                        } else {
                            _uiState.value = UIState.NO_DATA
                        }
                    }

                    Result.Status.ERROR -> {
                        _uiState.value = UIState.ERROR
                        _errorMessage.value = it.error?.status_message
                    }

                    else -> {
                        _uiState.value = UIState.ERROR
                        _errorMessage.value = null
                    }
                }
            }
        }
    }
}