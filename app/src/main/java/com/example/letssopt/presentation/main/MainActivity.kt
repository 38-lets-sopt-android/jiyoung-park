package com.example.letssopt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.letssopt.presentation.collection.CollectionRoute
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.home.HomeViewModel
import com.example.letssopt.presentation.home.component.HomeTopBar
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.purchase.PurchaseRoute
import com.example.letssopt.presentation.search.SearchRoute
import com.example.letssopt.presentation.webtoon.WebtoonRoute
import com.example.letssopt.ui.theme.LETSSOPTTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                var currentTab by remember { mutableStateOf(MainTab.HOME) }

                Scaffold(
                    topBar = {
                        HomeTopBar()
                    },
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomBar(
                            currentTab = currentTab,
                            onClick = { currentTab = it },
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                ) { innerPadding ->
                    when (currentTab) {
                        MainTab.HOME -> HomeRoute(
                            viewModel = homeViewModel,
                            modifier = Modifier.padding(innerPadding),
                        )

                        MainTab.PURCHASE -> PurchaseRoute(Modifier.padding(innerPadding))
                        MainTab.WEBTOON -> WebtoonRoute(Modifier.padding(innerPadding))
                        MainTab.SEARCH -> SearchRoute(Modifier.padding(innerPadding))
                        MainTab.COLLECTION -> CollectionRoute(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}