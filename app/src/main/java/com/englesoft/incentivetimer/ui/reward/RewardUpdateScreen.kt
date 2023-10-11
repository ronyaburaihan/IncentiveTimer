package com.englesoft.incentivetimer.ui.reward

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.englesoft.incentivetimer.R
import com.englesoft.incentivetimer.ui.reward.viewmodel.RewardUpdateViewModel
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme

@Composable
fun RewardUpdateScreen(navController: NavController) {
    val rewardViewModel: RewardUpdateViewModel = hiltViewModel()
    val rewardNameInput by rewardViewModel.rewardNameInput.observeAsState("")
    val chanceInput by rewardViewModel.chanceInPercentInput.observeAsState(10)
    val isEditMode = rewardViewModel.isEditMode

    LaunchedEffect(Unit) {
        rewardViewModel.events.collect { event ->
            when (event) {
                RewardUpdateViewModel.RewardUpdateEvent.RewardInserted -> navController.popBackStack()
            }
        }
    }

    ScreenContent(
        isEditMode = isEditMode,
        rewardNameInput = rewardNameInput,
        onRewardNameInputChanged = rewardViewModel::onRewardNameInputChanged,
        chanceInput = chanceInput,
        onChanceChanged = rewardViewModel::onChanceInPercentInputChanged,
        onCloseClicked = { navController.popBackStack() },
        onSaveClicked = rewardViewModel::onSaveClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    isEditMode: Boolean = false,
    rewardNameInput: String?,
    onRewardNameInputChanged: (input: String) -> Unit,
    chanceInput: Int,
    onChanceChanged: (input: Int) -> Unit,
    onCloseClicked: () -> Unit,
    onSaveClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            val appBarTitle =
                stringResource(if (isEditMode) R.string.update_reward else R.string.add_reward)
            TopAppBar(
                title = {
                    Text(appBarTitle)
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClicked) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.close)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onSaveClicked,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.save_reward)
                )
            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(horizontal = 16.dp)) {
            TextField(
                value = rewardNameInput ?: "",
                singleLine = true,
                label = {
                    Text(text = stringResource(id = R.string.reward_name))
                },
                onValueChange = onRewardNameInputChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(id = R.string.chance) + ": $chanceInput%")
            Slider(
                value = chanceInput.toFloat() / 100,
                onValueChange = { value ->
                    onChanceChanged((value * 100).toInt())
                }
            )
        }
    }
}

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun ScreenContentPreview() {
    IncentiveTimerTheme {
        Surface {
            ScreenContent(
                isEditMode = false,
                rewardNameInput = "Example Reward",
                onRewardNameInputChanged = {},
                chanceInput = 20,
                onChanceChanged = {},
                onCloseClicked = {},
                onSaveClicked = {}
            )
        }
    }
}