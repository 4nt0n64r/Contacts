package com.a4nt0n64r.notes.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.a4nt0n64r.contacts.models.SimpleUser
import com.a4nt0n64r.contacts.models.User

@Dao
interface RoomDao {

    @Query("SELECT * FROM users_table")
    fun getAllNotes(): LiveData<List<SimpleUser>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: SimpleUser)

    @Delete
    suspend fun delete(user: SimpleUser)
}