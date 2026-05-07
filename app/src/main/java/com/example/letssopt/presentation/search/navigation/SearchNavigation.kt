package com.example.letssopt.presentation.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.search.SearchRoute
import kotlinx.serialization.Serializable

sealed interface SearchRoute: Route {
    @Serializable
    data object Search: SearchRoute
}

fun NavGraphBuilder.searchNavGraph(
    paddingValues: PaddingValues,
) {
    composable<SearchRoute.Search> {
        SearchRoute(
            modifier = Modifier.padding(paddingValues)
        )
    }
}