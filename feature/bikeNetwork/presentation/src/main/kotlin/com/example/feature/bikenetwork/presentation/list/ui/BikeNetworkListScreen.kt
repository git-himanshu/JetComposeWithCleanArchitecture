package com.example.feature.bikenetwork.presentation.list.ui

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
import com.example.core.uiwidgets.ErrorComposable
import com.example.core.uiwidgets.LoaderComposable
import com.example.core.uiwidgets.NoDataComposable
import com.example.feature.bikenetwork.presentation.R
import com.example.feature.bikenetwork.presentation.list.viewmodel.BikeNetworkListViewModel
import com.example.feature.bikenetwork.presentation.util.UIState

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
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
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
                    bikeNetworkList = bikeNetworkList.value,
                    modifier = Modifier.padding(innerPadding),
                    onItemClick = onItemClick
                )
            }

            UIState.NO_DATA -> {
                NoDataComposable(
                    errorText = errorMessage.value
                        ?: stringResource(id = R.string.data_not_found),
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = { viewModel.onRetry() },
                    modifier = modifier.padding(innerPadding),
                    noDataDrawable = R.drawable.no_data
                )
            }

            UIState.ERROR -> {
                ErrorComposable(
                    errorText = errorMessage.value
                        ?: stringResource(id = R.string.generic_error_message),
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = { viewModel.onRetry() },
                    modifier = modifier.padding(innerPadding)
                )
            }
        }
    }
}