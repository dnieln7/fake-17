package com.dnieln7.fake17.ui.auth.login

import android.util.Log
import androidx.lifecycle.*
import com.dnieln7.fake17.data.source.user.UserAuthSource
import com.dnieln7.fake17.data.source.user.UserDataSource
import com.dnieln7.fake17.ui.auth.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val userAuthSource: UserAuthSource,
    private val userDataSource: UserDataSource
) : ViewModel() {
    private val _state = MutableLiveData<AuthState>()

    val state: LiveData<AuthState> = _state

    fun login(email: String, password: String) {
        _state.value = AuthState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = userAuthSource.login(email, password)

                userDataSource.save(user)

                withContext(Dispatchers.Main) { _state.value = AuthState.Success }
            } catch (e: Exception) {
                Log.e(LoginViewModel::class.simpleName, "There was an error: ", e)
                withContext(Dispatchers.Main) { _state.value = AuthState.Failure("${e.message}") }
            }
        }
    }

    fun clearState() {
        _state.value = AuthState.Nothing
    }

    class Factory(
        private val userAuthSource: UserAuthSource,
        private val userDataSource: UserDataSource
    ) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(userAuthSource, userDataSource) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}