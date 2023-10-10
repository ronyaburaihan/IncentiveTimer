package com.englesoft.incentivetimer.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.englesoft.incentivetimer.data.database.TimerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideRewardDao(database: TimerDatabase) = database.rewardDao()

    @Singleton
    @Provides
    fun provideDatabase(
        app: Application,
        callback: TimerDatabase.Callback
    ): TimerDatabase =
        Room.databaseBuilder(
            app,
            TimerDatabase::class.java,
            "timer_database"
        ).addCallback(callback).build()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope