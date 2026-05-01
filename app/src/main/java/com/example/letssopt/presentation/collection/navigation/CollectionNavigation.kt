package com.example.letssopt.presentation.collection.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.collection.CollectionRoute
import kotlinx.serialization.Serializable

sealed interface CollectionRoute : Route {
    @Serializable
    data object Archive : CollectionRoute
}

fun NavGraphBuilder.archiveNavGraph(
    paddingValues: PaddingValues,
) {
    composable<CollectionRoute.Archive> {
        CollectionRoute(
            modifier = Modifier.padding(paddingValues)
        )
    }
}