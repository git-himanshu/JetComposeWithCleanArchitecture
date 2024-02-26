package com.example.feature.bikeNetwork.presentation.detail.intent

sealed class DetailIntent {
    data object FetchNetworkDetail : DetailIntent()
}