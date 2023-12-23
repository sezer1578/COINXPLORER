package com.ozaltun.coinxplorer.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.ozaltun.coinxplorer.R

data class Page(
    val title: Int,
    val description: Int,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = R.string.onboarding1_title,
        description = R.string.onboarding1_description,
        image = R.drawable.onboarding1
    ),
    Page(
        title = R.string.onboarding2_title,
        description =  R.string.onboarding2_description,
        image = R.drawable.onboarding2
    ),
    Page(
        title = R.string.onboarding3_title,
        description =  R.string.onboarding3_description,
        image = R.drawable.onboarding3
    )
)
