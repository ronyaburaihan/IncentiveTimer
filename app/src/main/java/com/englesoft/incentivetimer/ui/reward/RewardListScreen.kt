package com.englesoft.incentivetimer.ui.reward

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.englesoft.incentivetimer.FullScreenDestinations
import com.englesoft.incentivetimer.R
import com.englesoft.incentivetimer.data.model.Reward
import com.englesoft.incentivetimer.ui.reward.viewmodel.RewardViewModel
import com.englesoft.incentivetimer.ui.theme.IconKeys
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme

@Composable
fun RewardListScreen(
    navController: NavController,
    rewardViewModel: RewardViewModel = hiltViewModel()
) {
    val rewards by rewardViewModel.rewards.observeAsState(listOf())
    ScreenContent(
        rewards = rewards,
        onAddNewRewardClicked = {
            navController.navigate(FullScreenDestinations.RewardUpdateScreen.route)
        }
    )
}

@Composable
private fun ScreenContent(
    rewards: List<Reward>,
    onAddNewRewardClicked: () -> Unit,
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddNewRewardClicked,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_reward)
                )
            }
        }
    ) { innerPadding ->
        val listState = rememberLazyListState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 8.dp),
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                items(rewards) { reward ->
                    RewardListItem(reward = reward)
                }
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
                    Reward(title = "Reward 1", iconKey = IconKeys.TV, 5),
                    Reward(title = "Reward 2", iconKey = IconKeys.BATH_TUB, 5),
                    Reward(title = "Reward 3", iconKey = IconKeys.CAKE, 5)
                ),
                onAddNewRewardClicked = {}
            )
        }
    }
}