package com.example.letssopt.presentation.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchRoute(modifier: Modifier = Modifier){
    SearchScreen(modifier = modifier)
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
){
    Text(
        text = "찾기",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}