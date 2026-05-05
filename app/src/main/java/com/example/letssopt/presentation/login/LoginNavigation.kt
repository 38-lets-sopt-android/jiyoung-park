package com.example.letssopt.presentation.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.navigation.navigateToHome
import com.example.letssopt.presentation.signup.navigateToSignUp
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute : Route

fun NavGraphBuilder.loginScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
        composable<LoginRoute> {
            LoginRoute(
                modifier = Modifier.padding(paddingValues),
                navigateToRegister = navController::navigateToSignUp,
                navigateToMain = navController::navigateToHome,
            )
        }
    }

