package com.example.feature.bikenetwork.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestinations {
    val route: String
}

object BikeNetworkList : AppDestinations {
    override val route = "BikeNetworkList"
}

object BikeNetworkDetails : AppDestinations {
    override val route = "BikeNetworkDetails"
    const val networkIDArg = "network_id"
    val routeWithArgs = "$route/{$networkIDArg}"
    val arguments = listOf(
        navArgument(networkIDArg) { type = NavType.StringType }
    )
    fun createRoute(networkId: String) = "$route/${networkId}"
}