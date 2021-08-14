package com.dnieln7.fake17.ui.auth.signup

import android.util.Log
import androidx.lifecycle.*
import com.dnieln7.fake17.data.source.user.UserAuthSource
import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.ui.auth.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(
    private val userAuthSource: UserAuthSource
) : ViewModel() {
    private val _state = MutableLiveData<AuthState>()

    val state: LiveData<AuthState> = _state

    fun signUp(user: User) {
        _state.value = AuthState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = userAuthSource.signUp(user)

                withContext(Dispatchers.Main) { _state.value = AuthState.Success }
            } catch (e: Exception) {
                Log.e(SignupViewModel::class.simpleName, "There was an error: ", e)
                withContext(Dispatchers.Main) { _state.value = AuthState.Failure("${e.message}") }
            }
        }
    }

    fun clearState() {
        _state.value = AuthState.Nothing
    }

    class Factory(private val userAuthSource: UserAuthSource) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
                return SignupViewModel(userAuthSource) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}