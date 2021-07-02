package com.a4nt0n64r.contacts.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.a4nt0n64r.contacts.REPOSITORY
import com.a4nt0n64r.contacts.database.remote.ApiService
import com.a4nt0n64r.contacts.models.toSimpleUser
import kotlinx.coroutines.*

class MainFragmentViewModel(app: Application) : AndroidViewModel(app) {

    private val job: Job by lazy { SupervisorJob() }

    var usersLiveData = REPOSITORY.allUsers

    fun refresh() {
        CoroutineScope(Dispatchers.IO + job).launch {
            val apiService = ApiService()
            val usersFromApi =
                apiService.getUsersFromCloud().execute().body()!!.users.map { it.toSimpleUser() }
            for (user in usersLiveData.value!!){
                REPOSITORY.delete(user)
            }
            for (user in usersFromApi) {
                REPOSITORY.insert(user)
            }
        }
    }
}

