package com.dnieln7.fake17.ui.auth.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.fake17.data.source.user.UserAuthSource
import com.dnieln7.fake17.domain.User
import com.dnieln7.fake17.ui.auth.AuthState
import com.dnieln7.fake17.ui.auth.login.LoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SignupViewModel(
    private val userAuthSource: UserAuthSource
) : ViewModel() {
    private val _state = MutableLiveData<AuthState>()

    val state: LiveData<AuthState> = _state

    fun signUp(user: User) {
        _state.value = AuthState.Loading

        userAuthSource.signUp(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _state.value = AuthState.Success
                },
                {
                    Log.e(LoginViewModel::class.simpleName, "There was an error: ", it)
                    _state.value = AuthState.Failure("${it.message}")
                }
            )
    }

    fun clearState() {
        _state.value = AuthState.Nothing
    }

    /**
     * Creates an instance of [SignupViewModel] with the provided dependencies.
     *
     * @author dnieln7
     */
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