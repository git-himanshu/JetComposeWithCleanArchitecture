package com.example.feature.bikeNetwork.presentation.list.ui

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
import com.example.feature.bikeNetwork.presentation.list.intent.ListIntent
import com.example.feature.bikeNetwork.presentation.list.state.ListState
import com.example.feature.bikeNetwork.presentation.list.viewmodel.BikeNetworkListViewModel
import kotlinx.coroutines.launch

@Composable
fun BikeNetworkListScreen(
        modifier: Modifier = Modifier,
        viewModel: BikeNetworkListViewModel = hiltViewModel<BikeNetworkListViewModel>(),
        onItemClick: (networkId: String) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.value

    val fetchNetworksIntent: () -> Unit = {
        coroutineScope.launch {
            viewModel.userIntent.send(ListIntent.FetchBikeNetworks)
        }
    }

    Scaffold(
            modifier = modifier,
            topBar = {
                CustomTopAppBar(
                        title = stringResource(id = R.string.bike_network_list_screen_name),
                )
            }
    ) { innerPadding ->
        when (state) {
            is ListState.Idle -> {
                NoDataComposable(
                        retryButtonLabel = stringResource(id = R.string.load_data),
                        onRetry = fetchNetworksIntent,
                        modifier = modifier
                                .padding(innerPadding)
                                .testTag(
                                        stringResource(
                                                id = R.string.testTag_idle
                                        )
                                )
                )
            }

            is ListState.Loading -> {
                LoaderComposable(
                        modifier = modifier
                                .padding(innerPadding)
                                .testTag(
                                        stringResource(
                                                id = R.string.testTag_loader
                                        )
                                ),
                )
            }

            is ListState.BikeNetworks -> {
                BikeNetworkList(
                        bikeNetworkList = state.networks,
                        modifier = Modifier
                                .padding(innerPadding)
                                .testTag(
                                        stringResource(
                                                id = R.string.testTag_networkList
                                        )
                                ),
                        onItemClick = onItemClick
                )
            }

            is ListState.DataNotFound -> {
                NoDataComposable(
                        infoText = stringResource(id = R.string.data_not_found),
                        retryButtonLabel = stringResource(id = R.string.retry),
                        onRetry = fetchNetworksIntent,
                        modifier = modifier
                                .padding(innerPadding)
                                .testTag(
                                        stringResource(
                                                id = R.string.testTag_noData
                                        )
                                ),
                        noDataDrawable = R.drawable.no_data
                )
            }

            is ListState.Error -> {
                ErrorComposable(
                        errorText = state.error,
                        retryButtonLabel = stringResource(id = R.string.retry),
                        onRetry = fetchNetworksIntent,
                        modifier = modifier
                                .padding(innerPadding)
                                .testTag(
                                        stringResource(
                                                id = R.string.testTag_error
                                        )
                                )
                )
            }
        }
    }
}