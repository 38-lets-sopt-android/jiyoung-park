package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(Color(0xFF141414))
            .statusBarsPadding()
            .padding(vertical = 23.dp)
            .padding(end = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp, Alignment.End),
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_main_watch_24),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_main_noti_24),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_main_profile_24),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
private fun HomeTopBarPreview(){
    LETSSOPTTheme{
        HomeTopBar()
    }
}