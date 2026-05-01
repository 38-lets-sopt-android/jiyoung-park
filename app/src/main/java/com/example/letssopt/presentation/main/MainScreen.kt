package com.example.letssopt.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.component.HomeTopBar
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.main.navigation.MainNavHost
import com.example.letssopt.presentation.main.navigation.MainNavigator

@Composable
fun MainScreen(
    startDestination: Route,
    navigator: MainNavigator,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),

        topBar = {
            if (navigator.shouldShowTopBar()) {
                HomeTopBar()
            }
        },

        bottomBar = {
            MainBottomBar(
                visible = navigator.shouldShowBottomBar(),
                currentTab = navigator.currentTab,
                onClick = { tab -> navigator.navigate(tab) },
            )
        }
    ) { innerPadding ->
        MainNavHost(
            navigator = navigator,
            paddingValues = innerPadding,
            startDestination = startDestination,
        )
    }
}