package com.yoyocoder.checkin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yoyocoder.checkin.model.CheckInEntry

@Dao
interface CheckInEntryDao {

    @Query("SELECT * FROM CheckInEntry")
    fun getAllCheckInEntries(): List<CheckInEntry>

    @Insert
    fun addCheckInEntry(checkInEntry: CheckInEntry)

    @Delete
    fun deleteCheckInEntry(checkInEntry: CheckInEntry)

    @Query("DELETE FROM CheckInEntry WHERE id = :id")
    fun deleteCheckInEntryById(id: Int)
}