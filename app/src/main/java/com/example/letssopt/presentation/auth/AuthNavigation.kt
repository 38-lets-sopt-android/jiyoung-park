package com.example.letssopt.presentation.auth.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.navigation.navigateToHome
import com.example.letssopt.presentation.login.LoginRoute
import com.example.letssopt.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

@Serializable
object AuthGraph : Route

sealed interface AuthRoute : Route {
    @Serializable
    data object Login : AuthRoute

    @Serializable
    data object Register : AuthRoute
}

fun NavController.navigateToRegister() {
    navigate(AuthRoute.Register)
}

fun NavGraphBuilder.authNavGraph(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    navigation<AuthGraph>(
        startDestination = AuthRoute.Login,
    ) {
        composable<AuthRoute.Login> {
            LoginRoute(
                modifier = Modifier.padding(paddingValues),
                navigateToRegister = navController::navigateToRegister,
                navigateToMain = navController::navigateToHome,
            )
        }

        composable<AuthRoute.Register> {
            SignUpRoute(
                modifier = Modifier.padding(paddingValues),
                popBackStack = navController::popBackStack,
            )
        }
    }
}
