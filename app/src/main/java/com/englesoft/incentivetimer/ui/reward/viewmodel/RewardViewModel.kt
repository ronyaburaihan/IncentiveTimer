package com.englesoft.incentivetimer.ui.reward.viewmodel

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.englesoft.incentivetimer.data.model.Reward
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor() : ViewModel() {

    private val rewardsLiveData = MutableLiveData<List<Reward>>()
    val rewards: LiveData<List<Reward>> = rewardsLiveData

    init {
        val dummyRewards = mutableListOf<Reward>()
        repeat(100) { index ->
            dummyRewards += Reward(
                title = "Item $index",
                icon = Icons.Default.Star,
                chanceInPercent = index
            )
        }
        rewardsLiveData.value = dummyRewards
    }
}