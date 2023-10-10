package com.englesoft.incentivetimer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("rewards")
data class Reward(
    val title: String,
    val iconKey: String,
    val chanceInPercent: Int,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)