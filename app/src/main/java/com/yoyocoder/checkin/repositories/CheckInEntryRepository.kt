package com.yoyocoder.checkin.repositories

import com.yoyocoder.checkin.db.CheckInEntryDao
import com.yoyocoder.checkin.model.CheckInEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckInEntryRepository @Inject constructor(
    private val checkInEntryDao: CheckInEntryDao
) {

    suspend fun checkInEntries(): List<CheckInEntry> = withContext(Dispatchers.IO) {
        checkInEntryDao.getAllCheckInEntries()
    }

    suspend fun addCheckInEntry(checkInEntry: CheckInEntry) = withContext(Dispatchers.IO) {
        checkInEntryDao.addCheckInEntry(checkInEntry)
    }

    suspend fun deleteCheckInEntryById(id: Int) = withContext(Dispatchers.IO) {
        checkInEntryDao.deleteCheckInEntryById(id)
    }
}
