package com.example.letssopt.presentation.purchase.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import kotlinx.serialization.Serializable
import com.example.letssopt.presentation.purchase.PurchaseRoute

sealed interface PurchaseRoute: Route {
    @Serializable
    data object Purchase: PurchaseRoute
}

fun NavGraphBuilder.purchaseNavGraph(
    paddingValues: PaddingValues,
){
    composable<PurchaseRoute.Purchase> {
        PurchaseRoute(
            modifier = Modifier.padding(paddingValues)
        )
    }
}