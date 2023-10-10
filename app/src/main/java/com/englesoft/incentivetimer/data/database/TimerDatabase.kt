package com.englesoft.incentivetimer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.englesoft.incentivetimer.data.model.Reward
import com.englesoft.incentivetimer.di.ApplicationScope
import com.englesoft.incentivetimer.ui.theme.IconKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Reward::class], version = 1)
abstract class TimerDatabase : RoomDatabase() {

    abstract fun rewardDao(): RewardDao

    class Callback @Inject constructor(
        private val database: Provider<TimerDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val rewardDao = database.get().rewardDao()

            applicationScope.launch {
                rewardDao.insertReward(
                    Reward(
                        "1 piece of cake",
                        IconKeys.CAKE,
                        5
                    )
                )
                rewardDao.insertReward(
                    Reward(
                        "Take a bath",
                        IconKeys.BATH_TUB,
                        25
                    )
                )
                rewardDao.insertReward(
                    Reward(
                        "Watch a movie",
                        IconKeys.TV,
                        15
                    )
                )
            }
        }
    }
}