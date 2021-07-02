package com.a4nt0n64r.notes.database.room

import androidx.lifecycle.LiveData
import com.a4nt0n64r.contacts.database.Repository
import com.a4nt0n64r.contacts.models.SimpleUser

class AppRoomRepository(private val roomDao: RoomDao) : Repository {

    override val allUsers: LiveData<List<SimpleUser>>
        get() = roomDao.getAllNotes()

    override suspend fun insert(user: SimpleUser) {
        roomDao.insert(user)
    }

    override suspend fun delete(user: SimpleUser) {
        roomDao.delete(user)
    }
}