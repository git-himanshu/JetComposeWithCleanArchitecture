package com.example.feature.bikeNetwork.presentation.detail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.uiwidgets.CustomTopAppBar
import com.example.core.uiwidgets.ErrorComposable
import com.example.core.uiwidgets.LoaderComposable
import com.example.core.uiwidgets.NoDataComposable
import com.example.feature.bikeNetwork.presentation.R
import com.example.feature.bikeNetwork.presentation.detail.intent.DetailIntent
import com.example.feature.bikeNetwork.presentation.detail.state.DetailState
import com.example.feature.bikeNetwork.presentation.detail.viewmodel.BikeNetworkDetailViewModel
import kotlinx.coroutines.launch

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
                LoaderComposable(
                    modifier = modifier
                        .padding(innerPadding)
                        .testTag(stringResource(id = R.string.testTag_loader))
                )
            }

            is DetailState.NetworkDetail -> {
                BikeNetworkDetailComposable(
                    modifier
                        .padding(innerPadding)
                        .testTag(stringResource(id = R.string.testTag_networkDetail)),
                    bikeNetworkDetail = state.networks
                )
            }

            is DetailState.DataNotFound -> {
                NoDataComposable(
                    infoText = stringResource(id = R.string.data_not_found),
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = fetchNetworksIntent,
                    modifier = modifier
                        .padding(innerPadding)
                        .testTag(stringResource(id = R.string.testTag_noData)),
                    noDataDrawable = R.drawable.no_data
                )
            }

            is DetailState.Error -> {
                ErrorComposable(
                    errorText = state.error,
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = fetchNetworksIntent,
                    modifier = modifier
                        .padding(innerPadding)
                        .testTag(stringResource(id = R.string.testTag_error))
                )
            }

            is DetailState.Idle -> {
                NoDataComposable(
                    infoText = stringResource(id = R.string.generic_error_message),
                    retryButtonLabel = stringResource(id = R.string.retry),
                    onRetry = fetchNetworksIntent,
                    modifier = modifier
                        .padding(innerPadding)
                        .testTag(stringResource(id = R.string.testTag_idle))
                )
            }
        }
    }
}