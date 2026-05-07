package com.example.letssopt.presentation.auth.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.login.LoginRoute
import com.example.letssopt.presentation.login.loginScreen
import com.example.letssopt.presentation.signup.signUpScreen
import kotlinx.serialization.Serializable

@Serializable
object AuthGraph : Route

fun NavGraphBuilder.authNavGraph(
    paddingValues: PaddingValues,
    navController: NavController,
) {
    navigation<AuthGraph>(
        startDestination = LoginRoute,
    ) {
        loginScreen(paddingValues,navController)
        signUpScreen(paddingValues, navController)
    }
}
