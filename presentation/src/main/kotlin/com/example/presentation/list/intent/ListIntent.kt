package com.example.presentation.list.intent

sealed class ListIntent {
    data object FetchBikeNetworks : ListIntent()
}