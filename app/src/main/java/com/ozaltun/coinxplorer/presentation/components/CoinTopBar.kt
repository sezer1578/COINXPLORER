package com.ozaltun.coinxplorer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ozaltun.coinxplorer.R

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    icon: ImageVector? = null,
    onclick: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 64.dp)),
        title = {
            title.invoke()
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.primary)
        ),
        actions = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(42.dp)
                        .clickable { onclick() },
                    tint = colorResource(id = R.color.primary)
                )
            }
        },
        navigationIcon = { navigationIcon.invoke() }
    )
}