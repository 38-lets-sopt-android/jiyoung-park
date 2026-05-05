package com.example.letssopt.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

sealed interface HomeRoute : Route {
    @Serializable
    data object Home : HomeRoute
}

fun NavController.navigateToHome() {
    navigate(HomeRoute.Home) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
) {
    composable<HomeRoute.Home> {
        HomeRoute(
            modifier = Modifier.padding(paddingValues)
        )
    }
}
