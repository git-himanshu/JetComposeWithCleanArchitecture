package com.example.jetcomposewithcleanarchitecture.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetcomposewithcleanarchitecture.presentation.bike_network_detail.ui.BikeNetworkDetailScreen
import com.example.jetcomposewithcleanarchitecture.presentation.bike_network_list.ui.BikeNetworkListScreen

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
            BikeNetworkListScreen(onItemClick = {
                navController.navigate(BikeNetworkDetails.createRoute(it))
            })
        }
        composable(
            route = BikeNetworkDetails.routeWithArgs,
            arguments = BikeNetworkDetails.arguments,
        ) { navBackStackEntry ->
//            val networkId =
//                navBackStackEntry.arguments?.getString(BikeNetworkDetails.networkIDArg)
            BikeNetworkDetailScreen(
                onBack = { navController.navigateUp() }
            )
        }
    }
}