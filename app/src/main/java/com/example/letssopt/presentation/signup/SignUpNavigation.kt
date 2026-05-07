package com.example.letssopt.presentation.signup

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object SignUpRoute : Route

fun NavController.navigateToSignUp() {
    navigate(SignUpRoute)
}

fun NavGraphBuilder.signUpScreen(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    composable<SignUpRoute> {
        SignUpRoute(
            modifier = Modifier.padding(paddingValues),
            popBackStack = navController::popBackStack,
        )
    }
}