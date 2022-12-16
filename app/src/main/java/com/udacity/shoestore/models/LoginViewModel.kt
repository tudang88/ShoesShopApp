package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var userName: String = ""
    var password: String = ""
    private val _authFinishedEvent = MutableLiveData<Boolean>()
    val authFinishedEvent: LiveData<Boolean>
        get() = _authFinishedEvent
    private val _loginFailedEvent = MutableLiveData<Boolean>()
    val loginFailedEvent: LiveData<Boolean>
        get() = _loginFailedEvent

    /**
     * The authentication procedure will connect to backend database
     */
    fun authentication() {
        Log.i("LoginViewModel", "call authentication()")
        if (connectUserDataBase(userName, password)) {
            _authFinishedEvent.postValue(true)
        } else {
            _loginFailedEvent.postValue(true)
        }

    }

    fun clearLoginEvent() {
        _authFinishedEvent.value = false
        _loginFailedEvent.value = false
    }

    private fun connectUserDataBase(userName: String, password: String): Boolean {
        // dummy implementation
        if (userName == "" || password == "") {
            return false
        }
        return true
    }
}