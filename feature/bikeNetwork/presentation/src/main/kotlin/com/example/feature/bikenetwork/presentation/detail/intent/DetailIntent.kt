package com.example.feature.bikenetwork.presentation.detail.intent

sealed class DetailIntent {
    data object FetchNetworkDetail : DetailIntent()
}