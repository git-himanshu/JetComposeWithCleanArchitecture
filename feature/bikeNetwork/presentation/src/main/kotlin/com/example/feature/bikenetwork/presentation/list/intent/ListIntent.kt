package com.example.feature.bikenetwork.presentation.list.intent

sealed class ListIntent {
    data object FetchBikeNetworks : ListIntent()
}