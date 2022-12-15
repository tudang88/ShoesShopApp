package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName
    private val _authFinishedEvent = MutableLiveData<Boolean>()
    val authFinishedEvent: LiveData<Boolean>
    get() = _authFinishedEvent


    /**
     * The authentication procedure will connect to backend database
     */
    fun authentication(userName: String, password: String) {
        // Todo confirm user and pass word
        if (connectUserDataBase(userName, password)) {
            _userName.value = userName
            _authFinishedEvent.postValue(true)
        } else {
            _authFinishedEvent.postValue(false)
        }

    }

    private fun connectUserDataBase(userName: String, password: String): Boolean {
        // Todo: T.B.D
        if (userName == "" || password == "") {
            return false
        }
        return true
    }
}