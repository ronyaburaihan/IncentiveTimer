package com.englesoft.incentivetimer.ui.reward

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FireExtinguisher
import androidx.compose.material.icons.filled.MultipleStop
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.englesoft.incentivetimer.data.model.Reward
import com.englesoft.incentivetimer.ui.reward.viewmodel.RewardViewModel
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme

@Composable
fun RewardListScreen(rewardViewModel: RewardViewModel = hiltViewModel()) {
    val dummyRewards by rewardViewModel.rewards.observeAsState(listOf())
    ScreenContent(dummyRewards)
}

@Composable
private fun ScreenContent(dummyRewards: List<Reward>) {

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
            ScreenContent(
                listOf(
                    Reward(title = "Reward 1", icon = Icons.Default.Star, 5),
                    Reward(title = "Reward 2", icon = Icons.Default.FireExtinguisher, 5),
                    Reward(title = "Reward 3", icon = Icons.Default.MultipleStop, 5)
                )
            )
        }
    }
}