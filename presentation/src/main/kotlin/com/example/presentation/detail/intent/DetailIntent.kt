package com.example.presentation.detail.intent

sealed class DetailIntent {
    data object FetchNetworkDetail : DetailIntent()
}