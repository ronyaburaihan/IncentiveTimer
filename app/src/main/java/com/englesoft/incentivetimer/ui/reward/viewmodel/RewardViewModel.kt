package com.englesoft.incentivetimer.ui.reward.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.englesoft.incentivetimer.data.database.RewardDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    private val rewardDao: RewardDao
) : ViewModel() {

    val rewards = rewardDao.getAllRewards().asLiveData()
}