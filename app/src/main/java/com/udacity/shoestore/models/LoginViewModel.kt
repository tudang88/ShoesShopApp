package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var _userName = MutableLiveData<String>()

    //    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName
    private var _password: String = ""
    private val _authFinishedEvent = MutableLiveData<Boolean>()
    val authFinishedEvent: LiveData<Boolean>
        get() = _authFinishedEvent

    init {
        _userName.value = ""
    }

    /**
     * this function will be binding from layout
     */
    fun onUserNameEditChange(email: CharSequence, start: Int, before: Int, count: Int) {
        _userName.value = email.toString()
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
        // Todo confirm user and pass word
        if (connectUserDataBase(_userName.value ?: "", _password)) {
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