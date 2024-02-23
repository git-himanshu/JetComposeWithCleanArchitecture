package com.example.feature.bikenetwork.presentation.detail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.uiwidgets.CustomTopAppBar
import com.example.core.uiwidgets.ErrorComposable
import com.example.core.uiwidgets.LoaderComposable
import com.example.core.uiwidgets.NoDataComposable
import com.example.feature.bikenetwork.presentation.R
import com.example.feature.bikenetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikenetwork.presentation.detail.state.DetailState
import com.example.feature.bikenetwork.presentation.detail.viewmodel.BikeNetworkDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BikeNetworkDetailScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: BikeNetworkDetailViewModel = hiltViewModel<BikeNetworkDetailViewModel>(),
) {

    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()

    val fetchNetworksIntent: () -> Unit = {
        coroutineScope.launch {
            viewModel.userIntent.send(DetailIntent.FetchNetworkDetail)
        }
    }

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
        when (state) {
            is DetailState.Loading -> {
                LoaderComposable(modifier = modifier.padding(innerPadding))
            }

            is DetailState.NetworkDetail -> {
                BikeNetworkDetailComposable(
                    modifier.padding(innerPadding),
                    bikeNetworkDetail = state.networks
                )
            }

            is DetailState.DataNotFound -> {
                NoDataComposable(
                    infoText = stringResource(id = R.string.data_not_found),
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = fetchNetworksIntent,
                    modifier = modifier.padding(innerPadding),
                    noDataDrawable = R.drawable.no_data
                )
            }

            is DetailState.Error -> {
                ErrorComposable(
                    errorText = state.error,
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = fetchNetworksIntent,
                    modifier = modifier.padding(innerPadding)
                )
            }

            is DetailState.Idle -> {
                ErrorComposable(
                    errorText = stringResource(id = R.string.generic_error_message),
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = fetchNetworksIntent,
                    modifier = modifier.padding(innerPadding)
                )
            }
        }
    }
}