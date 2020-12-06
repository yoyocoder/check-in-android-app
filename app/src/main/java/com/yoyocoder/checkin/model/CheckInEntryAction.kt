package com.yoyocoder.checkin.model

sealed class CheckInEntryAction {
    data class Delete(val id: Int) : CheckInEntryAction()
    data class Edit(val id: Int) : CheckInEntryAction()
}