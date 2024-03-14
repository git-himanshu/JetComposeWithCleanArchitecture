package com.example.feature.bikeNetwork.presentation.navigation

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
    const val NETWORK_ID_ARG = "network_id"
    val routeWithArgs = "$route/{$NETWORK_ID_ARG}"
    val arguments = listOf(
            navArgument(NETWORK_ID_ARG) { type = NavType.StringType }
    )

    fun createRoute(networkId: String) = "$route/${networkId}"
}