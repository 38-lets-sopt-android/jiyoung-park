package com.example.letssopt.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.presentation.home.component.HomeTopBar
import com.example.letssopt.presentation.home.component.NewContentSection
import com.example.letssopt.presentation.home.component.PartySection
import com.example.letssopt.presentation.home.component.UpcomimgSection
import com.example.letssopt.presentation.home.component.WhatgorismSection

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
){
    HomeScreen(
        newContents = viewModel.getContent(),
        whatgorismContents = viewModel.getWhatgorisms(),
        upcomingContents = viewModel.getUpcoming(),
        partyContents = viewModel.getWatchaParties(),
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    newContents: List<ContentItem>,
    whatgorismContents: List<ContentItem>,
    upcomingContents: List<ContentItem>,
    partyContents: List<WatchaPartyItem>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { HomeTopBar() },
        containerColor = Color(0xFF141414),
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            NewContentSection(
                contents = newContents,
            )

            Spacer(Modifier.height(24.dp))

            WhatgorismSection(
                contents = whatgorismContents,
            )

            Spacer(Modifier.height(24.dp))

            UpcomimgSection(
                contents = upcomingContents,
            )

            Spacer(Modifier.height(24.dp))

            PartySection(
                contents = partyContents,
            )
        }
    }
}