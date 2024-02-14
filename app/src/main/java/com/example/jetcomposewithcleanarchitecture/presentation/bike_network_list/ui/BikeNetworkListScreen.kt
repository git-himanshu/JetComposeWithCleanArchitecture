package com.example.jetcomposewithcleanarchitecture.presentation.bike_network_list.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetcomposewithcleanarchitecture.R
import com.example.jetcomposewithcleanarchitecture.presentation.bike_network_list.viewmodel.BikeNetworkListViewModel
import com.example.jetcomposewithcleanarchitecture.presentation.composables.ErrorComposable
import com.example.jetcomposewithcleanarchitecture.presentation.composables.LoaderComposable
import com.example.jetcomposewithcleanarchitecture.presentation.util.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeNetworkListScreen(
    modifier: Modifier = Modifier,
    viewModel: BikeNetworkListViewModel = hiltViewModel<BikeNetworkListViewModel>(),
    onItemClick:(networkId:String)->Unit
) {
    val bikeNetworkList = viewModel.bikeNetworkList.collectAsStateWithLifecycle()
    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = R.string.bike_network_screen_name))
                }
            )
        }
    ) { innerPadding ->
        when (uiState.value) {
            UIState.LOADING -> {
                LoaderComposable(modifier = modifier.padding(innerPadding))
            }

            UIState.SUCCESS -> {
                BikeNetworkList(
                    bikeNetworkList = bikeNetworkList.value ?: emptyList(),
                    modifier = Modifier.padding(innerPadding),
                    onItemClick = onItemClick
                )
            }

            UIState.ERROR -> {
                ErrorComposable(
                    errorText = errorMessage.value ?: stringResource(id = R.string.generic_error_message),
                    onRetry = { viewModel.onRetry() },
                    modifier = modifier.padding(innerPadding)
                )
            }
        }
    }
}