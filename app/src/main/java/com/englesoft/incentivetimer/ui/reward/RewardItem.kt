package com.englesoft.incentivetimer.ui.reward

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.englesoft.incentivetimer.data.model.Reward
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardListItem(
    reward: Reward,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        onClick = {

        },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                reward.icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp)
                    .fillMaxWidth()
            )
            Column {
                Text(
                    reward.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "${reward.chanceInPercent}%",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
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
            RewardListItem(
                Reward(
                    "Hello World",
                    Icons.Default.Star,
                    5
                )
            )
        }
    }
}