package com.yoyocoder.checkin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yoyocoder.checkin.model.CheckInEntry

@Database(entities = [CheckInEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun attendeeDao(): CheckInEntryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "check-in-dsc.db"
                ).build()
            }

            return INSTANCE!!
        }
    }
}