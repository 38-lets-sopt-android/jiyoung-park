package com.example.letssopt.presentation.main.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun MainBottomBar(
    visible: Boolean,
    currentTab: MainTab?,
    onClick: (MainTab)->Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn(animationSpec = tween(300)) + slideIn(animationSpec = tween(300)) {
            IntOffset(0, it.height)
        },
        exit = fadeOut(animationSpec = tween(300)) + slideOut(animationSpec = tween(300)) {
            IntOffset(0, it.height)
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 72.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 18.dp, vertical = 11.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.spacedBy(21.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MainTab.entries.forEach { tab ->
                BottomBarItem(
                    labelRes = tab.labelRes,
                    iconRes = tab.iconRes,
                    selected = tab == currentTab,
                    onClick = { onClick(tab) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    @StringRes labelRes: Int,
    @DrawableRes iconRes: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val label = stringResource(labelRes)
    val color = if (selected) Color.White else Color(0xFF333333)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(role = Role.Tab) { onClick() }
            .padding(top = 4.dp, bottom = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = label,
            tint = color,
        )

        Text(
            text = label,
            color = color,
            fontSize = 12.sp
        )
    }
}

private class MainBottomBarPreviewParameter: PreviewParameterProvider<MainTab>{
    override val values: Sequence<MainTab>
        get() = MainTab.entries.asSequence()
}

@Preview
@Composable
private fun MainBottomBarPreview(
    @PreviewParameter(MainBottomBarPreviewParameter::class) currentTab: MainTab,
) {
    LETSSOPTTheme {
        MainBottomBar(
            visible = true,
            currentTab = currentTab,
            onClick = {},
        )
    }
}