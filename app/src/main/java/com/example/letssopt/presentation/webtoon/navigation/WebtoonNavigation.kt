package com.example.letssopt.presentation.webtoon.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.webtoon.WebtoonRoute
import kotlinx.serialization.Serializable

sealed interface WebtoonRoute: Route {
    @Serializable
    data object Webtoon: WebtoonRoute
}

fun NavGraphBuilder.webtoonNavGraph(
    paddingValues: PaddingValues,
) {
    composable<WebtoonRoute.Webtoon> {
        WebtoonRoute(
            modifier = Modifier.padding(paddingValues)
        )
    }
}
