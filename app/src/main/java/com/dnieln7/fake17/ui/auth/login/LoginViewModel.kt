package com.dnieln7.fake17.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.fake17.data.source.user.UserAuthSource
import com.dnieln7.fake17.data.source.user.UserDataSource
import com.dnieln7.fake17.domain.UserCredentials
import com.dnieln7.fake17.ui.auth.AuthState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel(
    private val userAuthSource: UserAuthSource,
    private val userDataSource: UserDataSource
) : ViewModel() {
    private val _state = MutableLiveData<AuthState>()

    val state: LiveData<AuthState> = _state

    fun login(email: String, password: String) {
        _state.value = AuthState.Loading

        // Tries to login and save the returned user
        userAuthSource.login(UserCredentials(email, password, "estudiante"))
            .flatMap { userDataSource.save(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _state.value = AuthState.Success },
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
     * Creates an instance of [LoginViewModel] with the provided dependencies.
     *
     * @author dnieln7
     */
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