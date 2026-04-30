package com.example.letssopt.presentation.webtoon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WebtoonRoute(modifier: Modifier = Modifier){
    WebtoonScreen(modifier = modifier)
}

@Composable
private fun WebtoonScreen(
    modifier: Modifier = Modifier,
){
    Text(
        text = "웹툰",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}