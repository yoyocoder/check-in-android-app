package com.yoyocoder.checkin.di

import android.content.Context
import androidx.room.Room
import com.yoyocoder.checkin.db.AppDatabase
import com.yoyocoder.checkin.db.CheckInEntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)

class DataModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "check-in.db"
        ).build()
    }

    @Provides
    fun provideCheckInEntryDao(appDatabase: AppDatabase): CheckInEntryDao =
        appDatabase.checkInEntryDao()
}