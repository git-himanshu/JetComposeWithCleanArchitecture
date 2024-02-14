package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcomposewithcleanarchitecture.domain.entity.BikeNetworkEntity
import com.example.jetcomposewithcleanarchitecture.domain.usecase.GetBikeNetworkListUseCase
import com.example.jetcomposewithcleanarchitecture.model.Result
import com.example.jetcomposewithcleanarchitecture.presentation.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeNetworkListViewModel @Inject constructor(
    private val getBikeNetworkListUseCase: GetBikeNetworkListUseCase,
) : ViewModel() {
    private val _bikeNetworkList = MutableStateFlow<List<BikeNetworkEntity>?>(emptyList())
    val bikeNetworkList : StateFlow<List<BikeNetworkEntity>?> = _bikeNetworkList.asStateFlow()

    private val _uiState = MutableStateFlow(UIState.LOADING)
    val uiState:StateFlow<UIState> = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>("")
    val errorMessage:StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchBikeNetworkList()
    }

    fun onRetry(){
        fetchBikeNetworkList()
    }

    private fun fetchBikeNetworkList() {
        viewModelScope.launch {
            getBikeNetworkListUseCase.getList().collect {
                when(it?.status){
                    Result.Status.LOADING -> {
                        _uiState.value = UIState.LOADING
                    }
                    Result.Status.SUCCESS -> {
                        _uiState.value = UIState.SUCCESS
                        _bikeNetworkList.value = it.data?.networks
                    }
                    Result.Status.ERROR ->{
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