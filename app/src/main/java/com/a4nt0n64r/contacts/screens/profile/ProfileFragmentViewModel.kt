package com.a4nt0n64r.contacts.screens.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.a4nt0n64r.contacts.APP_ACTIVITY
import com.a4nt0n64r.contacts.R
import com.a4nt0n64r.contacts.REPOSITORY
import com.a4nt0n64r.contacts.models.SimpleUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragmentViewModel(app: Application) : AndroidViewModel(app) {

    fun delete(user: SimpleUser) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(user)
        }
    }

    fun navigateback(){
        viewModelScope.launch(Dispatchers.Main) {
            APP_ACTIVITY.navController.navigate(R.id.action_profileFragment_to_mainFragment)
        }
    }

    fun save(user: SimpleUser) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(user)
        }
    }
}
