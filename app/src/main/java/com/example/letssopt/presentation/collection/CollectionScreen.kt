package com.example.letssopt.presentation.collection

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CollectionRoute(modifier: Modifier = Modifier){
    CollectionScreen(modifier = modifier)
}

@Composable
private fun CollectionScreen(
    modifier: Modifier = Modifier,
){
    Text(
        text = "보관함",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}