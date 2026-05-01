package com.example.letssopt.presentation.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.collection.navigation.CollectionNavGraph
import com.example.letssopt.presentation.home.navigation.homeNavGraph
import com.example.letssopt.presentation.purchase.navigation.purchaseNavGraph
import com.example.letssopt.presentation.search.navigation.searchNavGraph
import com.example.letssopt.presentation.webtoon.navigation.webtoonNavGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    paddingValues: PaddingValues,
    startDestination: Route,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize(),
        enterTransition = { fadeIn(tween(160)) },
        exitTransition = { fadeOut(tween(160)) },
        popEnterTransition = { fadeIn(tween(160)) },
        popExitTransition = { fadeOut(tween(160)) },
    ) {
        homeNavGraph(
            paddingValues = paddingValues,
        )
        purchaseNavGraph(
            paddingValues = paddingValues,
        )
        webtoonNavGraph(
            paddingValues = paddingValues,
        )
        searchNavGraph(
            paddingValues = paddingValues,
        )
        CollectionNavGraph(
            paddingValues = paddingValues,
        )
    }
}
