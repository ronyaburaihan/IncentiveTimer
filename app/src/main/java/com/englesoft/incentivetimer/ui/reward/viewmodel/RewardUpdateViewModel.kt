package com.englesoft.incentivetimer.ui.reward.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.englesoft.incentivetimer.data.database.RewardDao
import com.englesoft.incentivetimer.data.model.Reward
import com.englesoft.incentivetimer.ui.theme.IconKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardUpdateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val rewardDao: RewardDao
) : ViewModel() {
    private val rewardId = savedStateHandle.get<Long>(ARG_REWARD_ID)
    val isEditMode = rewardId != null

    private val rewardNameInputLiveData = savedStateHandle.getLiveData<String>("rewardNameLiveData")
    val rewardNameInput: LiveData<String> get() = rewardNameInputLiveData

    private val chanceInPercentInputLiveData =
        savedStateHandle.getLiveData<Int>("chanceInPercentInputLiveData")
    val chanceInPercentInput: LiveData<Int> get() = chanceInPercentInputLiveData

    private val eventChannel = Channel<RewardUpdateEvent>()
    val events = eventChannel.receiveAsFlow()

    sealed class RewardUpdateEvent {
        object RewardInserted : RewardUpdateEvent()
    }

    fun onRewardNameInputChanged(input: String) {
        rewardNameInputLiveData.value = input
    }

    fun onChanceInPercentInputChanged(input: Int) {
        chanceInPercentInputLiveData.value = input
    }

    fun onSaveClicked() {
        val rewardName = rewardNameInput.value
        val chancePercentage = chanceInPercentInput.value

        viewModelScope.launch {
            if (!rewardName.isNullOrEmpty() && chancePercentage != null) {
                insertReward(Reward(rewardName, IconKeys.CAKE, chancePercentage))
            } else {
                // TODO: Show error
            }
        }
    }

    private suspend fun insertReward(reward: Reward) {
        rewardDao.insertReward(reward)
        eventChannel.send(RewardUpdateEvent.RewardInserted)
    }

    private fun updateReward(reward: Reward) {

    }
}

const val ARG_REWARD_ID = "ARG_REWARD_ID"