package com.yoyocoder.checkin.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoyocoder.checkin.db.AppDatabase
import com.yoyocoder.checkin.model.CheckInEntry
import com.yoyocoder.checkin.model.CheckInEntryAction
import com.yoyocoder.checkin.repositories.CheckInEntryRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CheckInEntryRepository by lazy {
        val appDatabase = AppDatabase.getInstance(application.applicationContext)
        CheckInEntryRepository(appDatabase.attendeeDao())
    }

    private val checkInEntriesMutableLiveData: MutableLiveData<List<CheckInEntry>> =
        MutableLiveData()

    val viewActionLiveData: MutableLiveData<CheckInEntryAction> = MutableLiveData()

    val checkInEntriesLiveData: LiveData<List<CheckInEntry>> get() = checkInEntriesMutableLiveData

    fun getAllCheckInEntries() {
        viewModelScope.launch {
            checkInEntriesMutableLiveData.value = repository.checkInEntries()
        }
    }

    fun addCheckInEntry(checkInEntry: CheckInEntry) {
        viewModelScope.launch {
            repository.addCheckInEntry(checkInEntry)
            checkInEntriesMutableLiveData.value = repository.checkInEntries()
        }
    }

    fun deleteCheckInEntry(checkInEntryId: Int) {
        viewModelScope.launch {
            repository.deleteCheckInEntryById(checkInEntryId)
            checkInEntriesMutableLiveData.value = repository.checkInEntries()
        }
    }
}
