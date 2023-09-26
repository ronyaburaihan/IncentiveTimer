package com.englesoft.incentivetimer.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Reward(
    val title: String,
    val icon: ImageVector,
    val chanceInPercent: Int
)