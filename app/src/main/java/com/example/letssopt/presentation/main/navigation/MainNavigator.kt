package com.example.letssopt.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.letssopt.presentation.main.component.MainTab

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            currentDestination?.hasRoute(tab.route::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navController.navigate(tab.route, navOptions)
    }

    @Composable
    fun shouldShowBottomBar() = currentTab != null
}

@Composable
fun rememberLETSSOPTNavigator(navController: NavHostController = rememberNavController()): MainNavigator =
    remember(navController) {
        MainNavigator(navController)
    }
