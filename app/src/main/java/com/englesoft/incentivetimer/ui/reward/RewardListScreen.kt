package com.englesoft.incentivetimer.ui.reward

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.englesoft.incentivetimer.R
import com.englesoft.incentivetimer.data.model.Reward
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme
import com.englesoft.incentivetimer.ui.theme.ListBottomPadding

@Composable
fun RewardListScreen() {
    ScreenContent()
}

@Composable
private fun ScreenContent() {
    val dummyRewards = mutableListOf<Reward>()

    repeat(100) { index ->
        dummyRewards += Reward(
            title = "Item $index",
            icon = Icons.Default.Star,
            chanceInPercent = index
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn() {
            items(dummyRewards) { reward ->
                RewardListItem(reward = reward)
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun ScreenContentPreview() {
    IncentiveTimerTheme {
        Surface {
            ScreenContent()
        }
    }
}