package com.a4nt0n64r.contacts.database

import androidx.lifecycle.LiveData
import com.a4nt0n64r.contacts.models.SimpleUser
import com.a4nt0n64r.contacts.models.User

interface Repository {
    val allUsers: LiveData<List<SimpleUser>>
    suspend fun insert(user: SimpleUser)
    suspend fun delete(user: SimpleUser)
}