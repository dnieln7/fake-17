package com.dnieln7.fake17.ui.auth

sealed class AuthState {
    object Nothing : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class Failure(val error: String) : AuthState()
}
