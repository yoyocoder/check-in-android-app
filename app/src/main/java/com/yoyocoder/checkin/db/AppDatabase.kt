package com.yoyocoder.checkin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoyocoder.checkin.model.CheckInEntry

@Database(entities = [CheckInEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checkInEntryDao(): CheckInEntryDao
}