package com.yoyocoder.checkin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CheckInEntry(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}