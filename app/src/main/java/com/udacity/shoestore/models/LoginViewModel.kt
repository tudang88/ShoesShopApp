package com.udacity.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _userName: String = ""
    val userName: String
        get() = _userName
    private var _password: String = ""
    private val _authFinishedEvent = MutableLiveData<Boolean>()
    val authFinishedEvent: LiveData<Boolean>
        get() = _authFinishedEvent
    private val _loginFailedEvent = MutableLiveData<Boolean>()
    val loginFailedEvent: LiveData<Boolean>
        get() = _loginFailedEvent

    /**
     * this function will be binding from layout
     */
    fun onUserNameEditChange(email: CharSequence, start: Int, before: Int, count: Int) {
        _userName = email.toString()
    }

    /**
     * this function will be binding from layout
     */
    fun onPasswordEditChange(email: CharSequence, start: Int, before: Int, count: Int) {
        _password = email.toString()
    }

    /**
     * The authentication procedure will connect to backend database
     */
    fun authentication() {
        Log.i("LoginViewModel", "call authentication()")
        if (connectUserDataBase(_userName, _password)) {
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
        // Todo: T.B.D
        if (userName == "" || password == "") {
            return false
        }
        return true
    }
}