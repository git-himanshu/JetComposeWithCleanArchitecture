package com.example.feature.bikenetwork.presentation.detail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.uiwidgets.CustomTopAppBar
import com.example.core.uiwidgets.ErrorComposable
import com.example.core.uiwidgets.LoaderComposable
import com.example.core.uiwidgets.NoDataComposable
import com.example.feature.bikenetwork.presentation.R
import com.example.feature.bikenetwork.presentation.detail.viewmodel.BikeNetworkDetailViewModel
import com.example.feature.bikenetwork.presentation.util.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeNetworkDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: BikeNetworkDetailViewModel = hiltViewModel<BikeNetworkDetailViewModel>(),
) {
    val bikeNetworkDetail = viewModel.bikeNetworkDetail.collectAsStateWithLifecycle()
    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                title = stringResource(id = R.string.bike_network_detail_screen_name),
                onBack = onBack,
                backContentDescription = stringResource(id = R.string.back),
            )
        }

    ) { innerPadding ->
        when (uiState.value) {
            UIState.LOADING -> {
                LoaderComposable(modifier = modifier.padding(innerPadding))
            }

            UIState.SUCCESS -> {
                BikeNetworkDetailComposable(
                    modifier.padding(innerPadding),
                    bikeNetworkDetail = bikeNetworkDetail.value
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