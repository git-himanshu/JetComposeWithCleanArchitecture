package com.example.feature.bikeNetwork.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.bikeNetwork.presentation.detail.ui.BikeNetworkDetailScreen
import com.example.feature.bikeNetwork.presentation.list.ui.BikeNetworkListScreen

@Composable
fun NavigationHost(
        navController: NavHostController,
        modifier: Modifier = Modifier
) {
    NavHost(
            navController = navController,
            startDestination = BikeNetworkList.route,
            modifier = modifier
    ) {
        composable(route = BikeNetworkList.route) {
            BikeNetworkListScreen(
                    onItemClick = {
                        navController.navigate(BikeNetworkDetails.createRoute(it))
                    })
        }
        composable(
                route = BikeNetworkDetails.routeWithArgs,
                arguments = BikeNetworkDetails.arguments,
        ) {
            BikeNetworkDetailScreen(
                    onBack = { navController.navigateUp() }
            )
        }
    }
}