package com.example.feature.bikeNetwork.presentation.list.intent

sealed class ListIntent {
    data object FetchBikeNetworks : ListIntent()
}