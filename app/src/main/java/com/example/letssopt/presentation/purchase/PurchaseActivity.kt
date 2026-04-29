package com.example.letssopt.presentation.purchase

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PurchaseRoute(modifier: Modifier = Modifier){
    PurchaseScreen(modifier = modifier)
}

@Composable
private fun PurchaseScreen(
    modifier: Modifier = Modifier,
){
    Text(
        text = "개별 구매",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}