package com.englesoft.incentivetimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.englesoft.incentivetimer.ui.reward.RewardListScreen
import com.englesoft.incentivetimer.ui.theme.IncentiveTimerTheme
import com.englesoft.incentivetimer.ui.timer.TimerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IncentiveTimerTheme {
                ScreenContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavDestinations.forEach { bottomNavDestination ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == bottomNavDestination.route } == true,
                        onClick = {
                            navController.navigate(bottomNavDestination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(stringResource(bottomNavDestination.label))
                        },
                        alwaysShowLabel = false,
                        icon = {
                            Icon(bottomNavDestination.icon, contentDescription = null)
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = bottomNavDestinations[0].route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavDestination.Timer.route) {
                TimerScreen()
            }
            composable(BottomNavDestination.Rewards.route) {
                RewardListScreen()
            }
        }
    }
}

val bottomNavDestinations = listOf<BottomNavDestination>(
    BottomNavDestination.Timer,
    BottomNavDestination.Rewards
)

sealed class BottomNavDestination(
    val route: String,
    val icon: ImageVector,
    @StringRes val label: Int,
) {
    object Timer : BottomNavDestination(
        route = "timer",
        icon = Icons.Outlined.Timer,
        label = R.string.timer
    )

    object Rewards : BottomNavDestination(
        route = "rewards",
        icon = Icons.Outlined.List,
        label = R.string.rewards
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IncentiveTimerTheme {
        ScreenContent()
    }
}